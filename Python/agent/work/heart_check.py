# coding = utf-8
# 每隔三秒检测一次心跳，相当于定时器
# 心跳数据必须包含：mac
# python一直维持一个心跳探测，不能影响其他业务——线程池：由一个线程池来处理心跳，其他业务由其他线程池来处理
import datetime
import time
import threading
import json


class HeartCheck(threading.Thread):
    def __init__(self, mq, mac):
        super().__init__()
        # mq对象
        self.__mq = mq
        # 心跳数据
        self.__mac = mac

    def run(self):
        # 线程体，3秒执行一次
        while True:
            # 获取心跳数据
            # 获取当前时间
            current_time = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            heart_data = {"mac": self.__mac, "status": 1, "update_time": current_time}
            heart_data = json.dumps(heart_data)

            # 发送到mq
            self.__mq.produce_status(heart_data)

            # 休眠3秒
            time.sleep(3)
