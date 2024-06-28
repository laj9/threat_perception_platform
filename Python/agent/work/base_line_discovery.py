import json
import re
import threading
import subprocess


class BaseLineDiscovery(threading.Thread):
    def __init__(self, mq, mac):
        super().__init__()
        # rabbitmq,用于发送消息
        self.__mq = mq
        # mac地址，标记主机
        self.__mac = mac

    def run(self):
        self.__get_base_line_data()

    def __get_base_line_data(self):
        # 执行powershell基线检测脚本
        print("开始执行基线检测！")

        # 定义PowerShell命令
        ps_command = 'powershell -ExecutionPolicy bypass -File ./ps/windows.ps1'
        # 使用subprocess.run来运行PowerShell命令
        result = subprocess.run(['powershell', '-Command', ps_command],
                                stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

        # 对基线检测结果进行处理
        base_line_res_list = []
        for line in result.stdout.splitlines():
            # 去除换行符
            line = line.strip()
            # 只取检测结果（带异常项和合格项的行）
            if "异常项" in line or "合格项" in line:

                # 主要有两种输出结果，根据格式分别处理
                # Registry::restrictanonymous = [异常项]|0|1|系统网络基配核查-关闭默认共享盘策略-【不符合】等级保护标准.
                # [异常项]|HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Explorer中DisableAutoplay不存在该项|1|禁止全部驱动器自动播放

                if "=" in line:
                    # 第一步：根据[切分，获取主要部分(前后两部分)
                    parts = line.split('[')

                    # 第二步：进一步处理第一部分，去除" = "
                    name = parts[0].replace(' = ', '')

                    # 第三步：进一步切分第二部分，根据']'切分获取结论和描述
                    details = parts[1].split(']')

                    # 输出结果
                    policy_name = name  # 提取策略名称
                    qualification = details[0]  # 提取“合格项”和“异常项”
                    description_parts = details[1]  # 提取描述
                else:
                    # 第一步：根据]切分，获取主要部分
                    parts = line.split(']')

                    # 第二步：提取策略名称
                    policy_name = 'Registry::' + parts[1].split('中')[1].split('不存在')[0]

                    # 第三步：提取结论和描述
                    qualification = parts[0].replace('[', '')
                    description_parts = parts[1]
                res = {
                    "mac": self.__mac,
                    "policy_name": policy_name,
                    "qualification": qualification,
                    "description": description_parts
                }
                base_line_res_list.append(res)

        # 转为JSON格式
        data = json.dumps(base_line_res_list)

        # 发送消息
        self.__mq.produce_base_line_data(data)

        print("基线检测结束！")
