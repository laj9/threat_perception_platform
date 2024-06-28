# 探测主机的路由转发功能是否开启
import subprocess

class RoutingForwardDetect:
    def __init__(self):
        pass

    def __check_routing_enabled(self):
        try:
            # 执行 netsh 命令来获取路由转发的状态
            result = subprocess.run(['netsh', 'interface', 'ipv4', 'show', 'interface'], capture_output=True, text=True,
                                    timeout=10)

            # 检查输出中是否包含 "Forwarding" 字段
            output_lines = result.stdout.splitlines()
            for line in output_lines:
                if "Forwarding" in line:
                    status = line.split()[-1].strip()
                    return status.lower() == "enabled"

            # 如果未找到 "Forwarding" 字段，默认返回 False
            return False

        except Exception as e:
            print(f"Error checking routing status: {e}")
            return False

    # 测试函数
    def run(self):
        routing_enabled = self.__check_routing_enabled()
        if routing_enabled:
            print("Routing is enabled.")
            return 1, "Routing is enabled."
        else:
            print("Routing is not enabled or could not be determined.")
            return 0, "Routing is not enabled or could not be determined."
