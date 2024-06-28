# import subprocess
# # 定义PowerShell命令
# ps_command = 'powershell -ExecutionPolicy bypass -File ./ps/windows.ps1'
# # 使用subprocess.run来运行PowerShell命令
# result = subprocess.run(['powershell', '-Command', ps_command], stdout=subprocess.PIPE, stderr=subprocess.PIPE,text=True)
# # 打印输出和错误信息
# print(result.stdout) # 输出信息
# print(result.stderr) # 错误信息

res_path = "baseLine.log"
base_line_res_list = []
with open(res_path, 'r', encoding="utf-8") as file:
    for line in file:
        # print(line.strip())
        if "异常项" in line.strip() or "合格项" in line.strip():
            base_line_res_list.append(line.strip())