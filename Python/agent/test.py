from impacket.examples.utils import parse_target
from impacket.smbconnection import SMBConnection

def smb_login(u_name, u_pwd):
    # [[domain/]username[:password]@]<targetName or address>
    # target = 'test:123124@127.0.0.1'
    target = f'{u_name}:{u_pwd}@127.0.0.1'
    domain, username, password, address = parse_target(target)
    target_ip = address
    domain = ''
    lmhash = ''
    nthash = ''
    try:
        smbClient = SMBConnection(address, target_ip, sess_port=int(445))
        smbClient.login(username, password, lmhash, nthash)
        print("登录成功！")

        res = {
            'mac' : '12312312311',
            'u_name' : u_name,
            'u_pwd' : u_pwd,
        }
        print(res)
    except Exception as e:
        print(e)
        print("登录失败!")

if __name__ == "__main__":

    password_list = [
        "123456",
    ]
    u_name = 'test'

    for password in password_list:
        smb_login(u_name, password)

    smb_login()