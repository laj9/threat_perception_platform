# 姓名：刘爱婧
# 开发时间：2024/6/12 9:45
import json

import pythoncom
import wmi
from tools.do_request import DoRequest
from tools.discovery_weak_pwd import DiscoveryAccountWeakPwd
from tools.local_time_consistency_test import LocalTimeConsistencyTest
from tools.routing_forward_detect import RoutingForwardDetect
from tools.interface_promiscuous_mode_detect import InterfacePromiscuousModeDetect
import threading


class ThreatDiscovery(threading.Thread):
    def __init__(self, rabbitmq, data):
        super().__init__()
        self.__rabbitmq = rabbitmq
        self.__data = data

    def run(self):
        self.__discovery()

    def __discovery(self):
        print("开始风险探测………………")
        #先获取是否探测风险的标志
        hotfix = self.__data["hotfix"]
        vulnerability = self.__data["vulnerability"]
        weakPassword = self.__data["weakPassword"]
        application = self.__data["application"]
        system = self.__data["system"]

        if hotfix == 1:
            # 账号探测
            self.__hotfix_discovery()
        if vulnerability == 1:
            self.__vulnerability_discovery()

        if weakPassword == 1:
            self.__weak_password_discovery()

        if application == 1:
            self.__application_risk_discovery()

        if system == 1:
            self.__system_risk_discovery()

    def __hotfix_discovery(self):
        """
        补丁发现
        :return:
        """
        print("开始补丁安全发现......!")
        # 初始化
        pythoncom.CoInitialize()
        # 创建WMI客户端
        c = wmi.WMI()
        # 获取补丁信息
        hotfixes = c.query("SELECT HotFixID FROM Win32_QuickFixEngineering")
        # 组装补丁ID
        hotfix_list = []
        for hotfix in hotfixes:
            data = {
                'mac': self.__data['mac'],
                'hotfixId': hotfix.HotFixID
            }
            hotfix_list.append(data)
        # 去初始化
        pythoncom.CoUninitialize()
        # 转换成JSON数据
        data = json.dumps(hotfix_list)
        # 发送到队列
        self.__rabbitmq.produce_hotfix_data(data)
        print("补丁安全发现结束")

    def __vulnerability_discovery(self):
        print("开始漏洞安全发现......!")
        # 获取漏洞信息
        vulDbs = self.__data['vulnerabilities']
        vuls = []
        # 遍历进行探测
        for vulDb in vulDbs:
            # 获取请求路径
            path = vulDb['vulPath']
            # 获取请求方法
            method = vulDb['vulRequestType']
            # 获取payload
            payload = vulDb['payload']
            # 获取漏洞验证标志
            flag = vulDb['vulFlag']

            url = f"http://127.0.0.1{path}"
            # 发送请求
            doRequest = DoRequest(url, method, payload)
            # 获取响应
            response = doRequest.do_request()

            # 进行判断
            if flag in response:
                vul = {
                    # 标记主机
                    'mac': self.__data['mac'],
                    # 标记漏洞
                    'vulnerabilityId': vulDb['id']
                }
                vuls.append(vul)
        # 转换成JSON数据
        data = json.dumps(vuls)
        # 发送到队列
        self.__rabbitmq.produce_vul_data(data)
        print("漏洞安全发现结束")

    def __weak_password_discovery(self):
        print("开始弱口令安全发现......!")
        # 获取弱口令信息
        # 打开弱口令文件
        weak_pwd_list = []
        with open('resource/weak_pwd_top1000.txt', 'r', encoding='utf-8') as file:
            # 读取所有行并存储在一个列表中
            lines = file.readlines()
        # 遍历文件内容
        for line in lines:
            # 去除每行末尾的换行符
            weak_pwd_list.append(line.strip())

        # 获取账号信息
        accounts = self.__data['accounts']
        account_name = []
        for account in accounts:
            account_name.append(account['name'])
        # 移除无用的账号
        for account in accounts:
            if account['status'] == "Degraded":
                account_name.remove(account['name'])
        print(account_name)

        # 遍历进行探测
        rets = []
        print(rets.__class__)

        for account in account_name:
            # 遍历弱口令
            for weak_pwd in weak_pwd_list:
                discoveryAccountWeakPwd = DiscoveryAccountWeakPwd(account, weak_pwd)
                # 如果为真，说明存在弱口令
                if discoveryAccountWeakPwd.discovery_weak_pwd():
                    # 存在弱口令，进行拼接
                    data = {
                        'mac': self.__data['mac'],
                        'accountName': account,
                        'weakPwd': weak_pwd
                    }
                    print(rets.__class__)
                    rets.append(data)
                    print(data)

        # 转换成JSON数据
        datas = json.dumps(rets)
        print(rets)
        print(datas)
        # 发送到队列
        self.__rabbitmq.produce_weak_pwd_data(datas)
        print("弱口令安全发现结束")

    def __app_vulnerability_discovery(self):
        print("开始应用漏洞安全发现......!")
        # 获取漏洞信息
        vulDbs = self.__data['appVulnerabilities']
        vuls = []
        # 遍历进行探测
        for vulDb in vulDbs:
            # 获取请求路径
            path = vulDb['appVulPath']
            # 获取请求方法
            method = vulDb['requestType']
            # 获取payload
            payload = vulDb['payload']
            # 获取漏洞验证标志
            flag = vulDb['appVulFlag']

            url = f"http://127.0.0.1{path}"
            # 发送请求
            doRequest = DoRequest(url, method, payload)
            # 获取响应
            response = doRequest.do_request()

            # 进行判断
            if flag in response:
                vul = {
                    # 标记主机
                    'mac': self.__data['mac'],
                    # 标记漏洞
                    'appVulnerabilityId': vulDb['id']
                }
                vuls.append(vul)
        # 转换成JSON数据
        data = json.dumps(vuls)
        # 发送到队列
        self.__rabbitmq.produce_app_vul_data(data)
        print("应用漏洞安全发现结束")

    def __system_vulnerability_discovery(self):
        print("开始系统漏洞安全发现......!")
        vuls = []

        # 本地系统时间校验
        status, extra_info = LocalTimeConsistencyTest().compare_times()
        vul = {
            'mac': self.__data['mac'],
            'systemVulName': '主机时间校验',
            'systemVulDesc': '确保本系统中时间设置是准确的',
            'status': status,
            'extraInfo': extra_info
        }
        vuls.append(vul)

        # 路由转发功能是否开启
        status, extra_info = RoutingForwardDetect().run()
        vul = {
            'mac': self.__data['mac'],
            'systemVulName': '路由转发功能是否开启',
            'systemVulDesc': '路由转发功能开启可能被利用进行流量重定向、中间人攻击或数据窃听，从而使得敏感信息暴露或网络服务中断',
            'status': status,
            'extraInfo': extra_info
        }
        vuls.append(vul)

        # 网卡是否处于混杂模式
        status, extra_info = InterfacePromiscuousModeDetect().detect()
        vul = {
            'mac': self.__data['mac'],
            'systemVulName': '网卡是否处于混杂模式',
            'systemVulDesc': '混杂模式下，网卡会接收所有数据包，而不管目的地址是否匹配，这可能造成信息泄露或网络服务中断',
            'status': status,
            'extraInfo': extra_info
        }
        vuls.append(vul)

        # 转换成JSON数据
        data = json.dumps(vuls)
        # 发送到队列
        self.__rabbitmq.produce_system_vul_data(data)
        print("系统漏洞安全发现结束")

