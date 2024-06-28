# 姓名：刘爱婧
# 开发时间：2024/6/17 14:41
import requests

class DoRequest:
    def __init__(self, path, method, data):
        self.__path = path
        self.__method = method
        self.__data = data

    def do_request(self):
        if self.__method == 'GET':
            return self.__do_get()
        elif self.__method == 'POST':
            return self.__do_post()
        else:
            return None

    def __do_get(self):
        url = self.__path + self.__data
        try:
            response = requests.get(url)

            result = response.content.decode('utf-8')
            return result
        except Exception as e:
            print(e)
            return None

    def __do_post(self):
        try:
            response = requests.get(url=self.__path, data=self.__data)
            result = response.content.decode('utf-8')
            return result
        except Exception as e:
            print(e)
            return None