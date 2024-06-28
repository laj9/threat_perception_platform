import json
import threading
import time

from tools.discovery_system_log import DiscoverySystemLog


class TransLog(threading.Thread):
    def __init__(self, rabbitmq, mac):
        super().__init__()
        self.__rabbitmq = rabbitmq
        self.__mac = mac
        self.__security_event_path = r'C:\Windows\system32\winevt\Logs\Security.evtx'
        self.__system_event_path = r'C:\Windows\system32\winevt\Logs\System.evtx'

    def run(self):
        self.__log_discovery()

    def __log_discovery(self):

        """
        日志发现
        :return:
        """

        """
        常见的安全事件ID：
        4624 - 成功登录事件，表示用户成功登录系统。
        4625 - 登录失败事件，表示用户尝试但未能成功登录系统。
        4634 - 注销事件，表示用户注销系统。
        4647 - 用户注销事件，表示用户通过重新启动或关闭计算机来注销系统。
        4720 - 创建用户事件，表示新用户帐户已创建。
        4722 - 启用用户帐户事件，表示禁用的用户帐户已被启用
        4723 - 更改用户密码事件，表示用户密码已更改。
        4724 - 创建安全组事件，表示新安全组已创建。
        4728 - 成功授权事件，表示用户获得了指定对象的权限。
        4738 - 设置用户密码事件，表示用户密码已更改或重置。
        常见的系统事件ID：
        1074 - 通过这个事件ID查看计算机的开机、关机、重启的时间以及原因和注释。
        6005 - 表示计算机日志服务已启动，如果出现了事件ID为6005，则表示这天正常启动了系统。
        6006 - 系统关闭
        6008 - 非正常关机
        6009 - 系统已经重新启动
        6013 - 系统已经重新启动，原因是操作系统版本升级
        7036 - 服务状态更改
        7040 - 启动或停止监视者通知
        7045 - 安装服务
        日志清除事件：
        1102 - 这个事件ID记录所有审计日志清除事件，当有日志被清除时，出现此事件ID。
        """

        print("开始日志发现！")
        # 查询全部的日志
        security_log_id = [4624, 4625, 4634, 4647, 4720, 4722, 4723, 4724, 4728, 4738, 1102]
        # security_log_id = [4720]
        system_log_id = [1074, 6005, 6006, 6008, 6009, 6013, 7036, 7040, 7045]
        security_list = []
        system_list = []
        # 查询安全日志记录
        for id in security_log_id:
            discoverySystemLog = DiscoverySystemLog(self.__security_event_path, id, self.__mac)
            res = discoverySystemLog.get_log_info()
            for i in res:
                security_list.append(i)
        # 查询系统日志记录
        # for id in system_log_id:
        #     discoverySystemLog = DiscoverySystemLog(self.__security_event_path, id, self.__mac)
        #     res = discoverySystemLog.get_log_info()
        #     for i in res:
        #         security_list.append(i)

        # list = security_list + system_list
        list = security_list
        print("日志发现结束！")

        # 转换格式为json并发送数据
        # 转换成JSON数据
        data = json.dumps(list)
        print(data)
        # 发送到队列
        self.__rabbitmq.produce_logs_data(data)
