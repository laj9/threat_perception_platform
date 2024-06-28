from impacket.examples.utils import parse_target
from impacket.smbconnection import SMBConnection


class DiscoveryAccountWeakPwd:
    """
    使用smb协议进行测试，判断账号是否存在弱口令
    """

    def __init__(self, u_name, u_pwd):
        self.__u_name = u_name
        self.__u_pwd = u_pwd

    def discovery_account_weak_pwd(self):
        # [[domain/]username[:password]@]<targetName or address>
        target = f'{self.__u_name}:{self.__u_pwd}@127.0.0.1'
        print(target)
        domain, username, password, address = parse_target(target)
        target_ip = address
        domain = ''
        lmhash = ''
        nthash = ''
        try:
            smbClient = SMBConnection(address, target_ip, sess_port=int(445))
            smbClient.login(username, password, domain, lmhash, nthash)
            return True
        except Exception as e:
            return False
