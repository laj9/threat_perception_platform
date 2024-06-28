# 系统时间是否一致测试
import ntplib
from time import ctime
from future.backports.datetime import timezone, timedelta
from datetime import datetime
from zoneinfo import ZoneInfo


class LocalTimeConsistencyTest():
    def __init__(self):
        pass

    def __get_server_time(self):
        ntp_server = 'pool.ntp.org'
        c = ntplib.NTPClient()
        response = c.request(ntp_server)
        # 将NTP时间戳转换为datetime对象
        ntptime = datetime.fromtimestamp(response.tx_time, timezone.utc)

        # 如果需要，可以将UTC时间转换为本地时间
        local_time = ntptime.astimezone()

        return local_time

    def __get_local_time(self):
        # 获取当前时间并设置为指定时区（例如，北京时间）
        beijing_tz = ZoneInfo('Asia/Shanghai')
        return datetime.now(beijing_tz)

    def compare_times(self):
        server_time = self.__get_server_time()
        local_time = self.__get_local_time()
        print("服务器时间：" + str(server_time))
        print("本地时间：" + str(local_time))
        if server_time:
            time_diff = abs(server_time - local_time)
            max_allowed_diff = timedelta(seconds=5)  # 定义一个可以接受的最大时间差，
            if time_diff <= max_allowed_diff:
                print("系统时间一致,差值为:" + str(time_diff))
                return 0, "系统时间一致,差值为:" + str(time_diff)
            else:
                print("系统时间不一致,差值为:" + str(time_diff))
                return 1, "系统时间不一致,差值为:" + str(time_diff)
