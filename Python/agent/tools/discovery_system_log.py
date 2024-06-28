from evtx import PyEvtxParser
import re
import html
from xml.dom import minidom
from tools.add_log import add_log_name_and_desc


class DiscoverySystemLog:

    def __init__(self, event_path, __event_id_to_filter, mac):
        # 文件位置
        self.__event_path = event_path
        # 日志ID
        self.__event_id_to_filter = __event_id_to_filter
        # 主机的mac
        self.__mac = mac

    def get_log_info(self):
        """
        过滤自己想要的日志
        :param event_path: 日志的路径
        :param event_id_to_filter: 过滤的日志ID
        :return:
        """
        # 创建一个EvtxParser对象来读取文件
        parser = PyEvtxParser(self.__event_path)
        pattern = re.compile(r'<EventID.*?(\d+)</EventID>')
        # 给一个容器，装最终的事件
        event_list = []
        # 遍历日志条目，筛选出特定事件ID的日志
        for record in parser.records():
            # print(record)
            xml_data = record['data']
            res = re.findall(pattern, xml_data)
            event_id = int(res[0])
            if self.__event_id_to_filter is not None and event_id == self.__event_id_to_filter:
                r = {}
                r['event_id'] = event_id
                r['timestampStr'] = record['timestamp']
                r['mac'] = self.__mac
                # 调用函数获取事件名称和描述
                add_log_name_and_desc(r, event_id)
                extra_info = {}
                xml_doc = minidom.parseString(xml_data)
                data = xml_doc.getElementsByTagName('Data')
                for d in data:
                    try:
                        name = d.getAttribute('Name')
                        value = html.unescape(d.childNodes[0].data)
                        extra_info[name] = value
                    except Exception as e:
                        pass
                r['extra_info'] = extra_info
                event_list.append(r)
        return event_list
