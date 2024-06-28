# coding=utf-8
# Author: HSJ
# 2024/6/20 14:03

from evtx import PyEvtxParser
import re
import html
from xml.dom import minidom

def get_log_info(event_path, event_id_to_filter=None):
    """
    过滤自己想要的日志
    :param event_path: 日志的路径
    :param event_id_to_filter: 过滤的日志ID, 如果为None，则返回所有日志
    :return:
    """
    # 创建一个EvtxParser对象来读取文件
    parser = PyEvtxParser(event_path)
    pattern = re.compile(r'<EventID>(\d+)</EventID>')
    # 给一个容器，装最终的事件
    event_list = []
    # 遍历日志条目，筛选出特定事件ID的日志
    for record in parser.records():
        # print(record)
        xml_data = record['data']
        res = re.findall(pattern, xml_data)
        event_id = int(res[0])
        if event_id_to_filter is not None and event_id == event_id_to_filter:
            r = {}
            r['event_id'] = event_id
            r['timestamp'] = record['timestamp']
            xml_doc = minidom.parseString(xml_data)
            data = xml_doc.getElementsByTagName('Data')
            for d in data:
                try:
                    name = d.getAttribute('Name')
                    value = html.unescape(d.childNodes[0].data)
                    r[name] = value
                except Exception as e:
                    pass
            event_list.append(r)
        elif event_id_to_filter is None:
            # 返回所有的日志
            r = {}
            r['event_id'] = event_id
            r['timestamp'] = record['timestamp']
            xml_doc = minidom.parseString(xml_data)
            data = xml_doc.getElementsByTagName('Data')
            for d in data:
                try:
                    name = d.getAttribute('Name')
                    value = html.unescape(d.childNodes[0].data)
                    r[name] = value
                except Exception as e:
                    pass
            event_list.append(r)
        return event_list

if __name__ == '__main__':
    # 指定EVTX文件路径
    path = r"C:\Windows\system32\winevt\Logs\Security.evtx"
    event_list = get_log_info(path, 4726)
    for event in event_list:
        print(event)