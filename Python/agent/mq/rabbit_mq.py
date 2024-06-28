# mq/rabbit_mq.py
import json
import pika
from work.assets_detect import AssetsDetect
from work.threat_discovery import ThreatDiscovery
from work.base_line_discovery import BaseLineDiscovery

class RabbitMQ(object):
    def __init__(self):
        self.__host = ''
        self.__port = 4567
        self.__user = ''
        self.__password = ''
        self.__virtual_host = 'my_vhost'
        self.__connection = None
        self.__channel = None

        # 初始化连接
        self.__get_connection()

    def __get_connection(self):
        """
        获取连接对象
        :return:
        """
        # 获取认证对象
        credentials = pika.PlainCredentials(self.__user, self.__password)
        # 获取连接
        self.__connection = pika.BlockingConnection(pika.ConnectionParameters
                                                    (host=self.__host, port=self.__port,
                                                     virtual_host=self.__virtual_host,
                                                     credentials=credentials))
        # 获取通道
        self.__channel = self.__connection.channel()

    def __my_producter(self, exchange, routing_key, data):
        """
        生产者
        :param routing_key: 路由键
        :param exchange: 交换机
        :param data: 数据
        :return:
        """
        self.__channel.basic_publish(exchange=exchange, routing_key=routing_key,
                                     body=data)

    def produce_sysinfo(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'sysinfo'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_status(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'status'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_account_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'account'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)


    def produce_hotfix_data(self, data):
        """
        生产补丁探测数据
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'hotfix'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_vul_data(self, data):
        """
        生产补丁探测数据
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'vulnerability'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_weak_pwd_data(self, data):
        """
        负责调用私有方法发送数据到rabbitmq消息队列中
        发送弱口令探测信息
        :param message: 要发送的消息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'weakPwd'
        # 发送消息
        self.__my_producter(exchange, routing_key, data)

    def produce_service_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'service'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_process_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'process'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_application_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'application'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_risk_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'risk'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_system_risk_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'system_risk'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_logs_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'my_log'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def produce_base_line_data(self, data):
        """
        生产系统信息
        :return:
        """
        exchange = 'sysinfo_exchange'
        routing_key = 'base_line'
        # 发送数据
        self.__my_producter(exchange, routing_key, data)

    def consume_queue(self, queue_name):
        """
        消费队列数据
        :param queue_name:
        :return:
        """

        # 消费队列
        self.__channel.basic_consume(queue=queue_name,
                                     on_message_callback=self.__process_message, auto_ack=True)
        # 开始监听
        self.__channel.start_consuming()

    def __process_message(self, ch, method, properties, message):
        """
        处理消息
        :param ch:
        :param properties:
        :param message:
        :return:
        """

        # JSON字符串转换成字典
        data = json.loads(message)
        # print(data)
        # 判断类型
        if data['type'] == 'assets':
            # 资产探测
            assetsDetect = AssetsDetect(self, data)
            assetsDetect.start()

        if data['type'] == 'threat':
            threatDiscovery = ThreatDiscovery(self, data)
            threatDiscovery.start()

        if data['type'] == 'baseLine':
            baseLineCheck = BaseLineDiscovery(self, data['mac'])
            baseLineCheck.start()