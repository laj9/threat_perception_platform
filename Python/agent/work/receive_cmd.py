# 姓名：刘爱婧
# 开发时间：2024/6/12 9:30
import threading

class ReceiveCmd(threading.Thread):
    
    def __init__(self, mq, mac):
        super().__init__()
        self.__mq = mq
        self.__mac = mac

    def run(self):
        queue = 'agent_' + self.__mac.replace(':', '') + '_queue'
        self.__mq.consume_queue(queue)