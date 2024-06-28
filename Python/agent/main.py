# coding=utf-8

from system.system_info import SystemInfo
from work.receive_cmd import ReceiveCmd
from work.heart_check import HeartCheck
from mq.rabbit_mq import RabbitMQ
from work.trans_log import TransLog

if __name__ == '__main__':
    print("客户端启动………………")

    # 实例化系统信息类
    system_info = SystemInfo()

    print("开始探测………………")
    # 获取系统信息
    sys_info = system_info.get_info()
    print(sys_info)
    print("探测结束………………")

    print("开始发送消息………………")
    rabbit_mq = RabbitMQ()
    rabbit_mq.produce_sysinfo(sys_info)
    print("发送消息结束………………")

#     心跳检测
    print("开始心跳检测………………")
    heart_check = HeartCheck(rabbit_mq, system_info.mac)
    heart_check.start()

    rabbit_mq2 = RabbitMQ()
    receive_cmd = ReceiveCmd(rabbit_mq2, system_info.mac)
    receive_cmd.start()

    print('开始同步日志信息')
    rabbit_mq = RabbitMQ()
    trans_log = TransLog(rabbit_mq, system_info.mac)
    trans_log.start()




