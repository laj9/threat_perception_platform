import math
import socket
import subprocess
import uuid
import platform
import json
import psutil

class SystemInfo:
    """
    获取系统信息（主机名、主机IP、MAC地址、OS名字、OS版本、架构）
    """
    def __init__(self):
        self.__hostname = ''
        self.__ip = ''
        self.__mac = ''
        self.__os_name = ''
        self.__os_version = ''
        self.__os_type = ''
        self.__cpu_type = ''
        self.__ram = ''
        self.__sys_info = {}

    @property
    def mac(self):
        return self.__mac

    def __get_hostname(self):
        """
        获取主机名
        :return:
        """
        self.__hostname = socket.gethostname()
        self.__sys_info['hostname'] = self.__hostname

    def __get_ip(self):
        """
        获取IP地址
        :return:
        """
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.connect(("114.114.114.114", 80))
        ip_address = s.getsockname()[0]
        s.close()
        self.__ip = ip_address
        self.__sys_info['ip'] = self.__ip

    def __get_mac(self):
        """
        获取MAC地址: 这个是逻辑MAC，但也是唯一的，只是做一个标识
        :return:
        """
        mac = ':'.join(("%012X" % uuid.getnode())[i:i + 2] for i in range(0, 12, 2))
        self.__mac = mac
        self.__sys_info['mac'] = self.__mac

    def __get_os_info(self):
        """
        获取操作系统相关信息
        :return:
        """
        self.__os_type = platform.system()
        self.__os_name = self.__get_win_os_name()
        self.__os_version = platform.version()
        self.__os_bit = platform.architecture()[0]
        self.__sys_info['os_type'] = self.__os_type
        self.__sys_info['os_name'] = self.__os_name
        self.__sys_info['os_version'] = self.__os_version
        self.__sys_info['os_bit'] = self.__os_bit

    def __get_win_os_name(self):
        """
        获取win的实际操作系统版本
        :return:
        """
        # 执行wmic命令获取操作系统名称
        process = subprocess.Popen(['wmic', 'os', 'get', 'Caption'],
                                   stdout=subprocess.PIPE)
        output, _ = process.communicate()
        # 解析输出结果，获取操作系统名称
        return output.strip().decode('GBK').split('\n')[1]

    def __get_cpu_ram_info(self):
        """
        获取cpu,ram相关信息
        :return:
        """
        self.__cpu_type = self.__get_win_cpu_name()
        self.__ram = math.ceil(psutil.virtual_memory().total / 1024 / 1024 / 1024)
        self.__sys_info['cpu_type'] = self.__cpu_type
        self.__sys_info['ram'] = self.__ram

    def __get_win_cpu_name(self):
        """
        获取win cpu 的名字
        :return:
        """
        process = subprocess.Popen(['wmic', 'cpu', 'get', 'name'],
                                   stdout=subprocess.PIPE)
        output, _ = process.communicate()
        return output.strip().decode('utf-8').split('\n')[1]

    def get_info(self):
        self.__get_hostname()
        self.__get_ip()
        self.__get_mac()
        self.__get_os_info()
        self.__get_cpu_ram_info()
        info = {
            'hostname': self.__hostname,
            'ip': self.__ip,
            'mac': self.__mac,
            'os_name': self.__os_name,
            'os_version': self.__os_version,
            'os_type': self.__os_type,
            'cpu': self.__cpu_type,
            'ram': self.__ram
        }
        return json.dumps(info)

