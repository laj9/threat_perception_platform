# 网卡是否处于混杂模式检测
import subprocess
import re


class InterfacePromiscuousModeDetect():
    def __init__(self):
        pass

    def __check_promiscuous_mode(self, interface_name):
        # Windows系统检查混杂模式的命令
        command = f'netsh interface show interface name="{interface_name}"'
        try:
            # 执行命令并获取输出
            output = subprocess.check_output(command, shell=True, encoding='GBK')

            # 使用正则表达式查找状态行
            pattern = r'Status\s*:\s*(\w+)'
            match = re.search(pattern, output, re.IGNORECASE)
            if match:
                status = match.group(1).strip().lower()
                # 检查状态是否为"enabled"
                if status == 'enabled':
                    return True
                else:
                    return False
            else:
                return False
        except subprocess.CalledProcessError as e:
            print(f'Error: {e}')
            return False

    def __get_all_interfaces(self):
        try:
            # 使用wmic命令获取所有网络接口的信息
            command = 'wmic nic get NetConnectionID /value'
            output = subprocess.check_output(command, shell=True, encoding='GBK')

            # 解析输出，获取所有接口名称
            interface_names = []
            lines = output.strip().split('\n')
            for line in lines:
                if line.startswith('NetConnectionID='):
                    interface_names.append(line.split('=')[1].strip())

            return interface_names
        except subprocess.CalledProcessError as e:
            print(f'Error: {e}')
            return []

    def detect(self):
        # 获取所有网卡名称
        interface_names = self.__get_all_interfaces()

        mixed_mode_interfaces = []
        Non_mixed_mode_interfaces = []

        # 遍历所有网卡，检查混杂模式
        for interface_name in interface_names:
            if interface_name != '':
                is_promiscuous_mode = self.__check_promiscuous_mode(interface_name)
                if is_promiscuous_mode:
                    mixed_mode_interfaces.append(interface_name)
                else:
                    Non_mixed_mode_interfaces.append(interface_name)

        # 检测结果
        if len(mixed_mode_interfaces) > 0:
            print(f'以下网卡处于混杂模式：{mixed_mode_interfaces}')
            return 1, f'以下网卡处于混杂模式：{mixed_mode_interfaces}'
        else:
            print('所有网卡均未处于混杂模式')
            return 0, '所有网卡均未处于混杂模式'
