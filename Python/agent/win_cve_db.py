# 姓名：刘爱婧
# 开发时间：2024/6/14 9:42
# coding=utf-8
# 2024/6/6 22:08
import requests
import datetime
import json
import calendar
import re
import pymysql

YEAR = str(datetime.datetime.now().year)
TODAY = datetime.datetime.now().strftime('%Y-%m-%d')
MONTH_IN_SHORT_EN = calendar.month_abbr[datetime.datetime.now().month]
# THIS_MONTH_ID = YEAR + "-" + MONTH_IN_SHORT_EN
THIS_MONTH_ID = YEAR + "-May"

print(THIS_MONTH_ID)
print(YEAR)

base_url = "https://api.msrc.microsoft.com/"
# windows api key
api_key = ""

def get_cvrf_json():
    db = pymysql.connect(host="localhost", port=3306, user="root",password="1007", db="threat_perception")
    cur = db.cursor()
    url = f"{base_url}cvrf/{THIS_MONTH_ID}?api-Version={YEAR}"
    headers = {'api-key': api_key, 'Accept': 'application/json'}
    response = requests.get(url, headers=headers)
    data = json.loads(response.content)
    for each_product in data["ProductTree"]["FullProductName"]:
        productid = each_product['ProductID']
        product_name = each_product['Value']

        search_productid_sql = f"SELECT * FROM win_product_name WHERE product_id='{productid}'"
        cur.execute(search_productid_sql)
        if cur.rowcount == 0:
            insert_product_sql = f"INSERT INTO win_product_name VALUES(null,'{productid}','{product_name}','{TODAY}')"
            cur.execute(insert_product_sql)
            db.commit()
    print('----------------------------------------------------------------')
    for each_cve in data["Vulnerability"]:
        cve = each_cve["CVE"]
        if re.search('ADV', cve):
            pass
        else:
            cve = each_cve["CVE"]
            kblist = []
            scorelist = []
            product_id_list = str(each_cve["ProductStatuses"][0]
            ["ProductID"]).replace("'", "")
            product_id_list = product_id_list.replace("[", "")
            product_id_list = product_id_list.replace("]", "")
            product_id_list = product_id_list.replace(" ", "")
            for each_kb in each_cve["Remediations"]:
                try:
                    kb_num = each_kb["Description"]["Value"]
                    if re.search('Click to Run', kb_num) or re.search('Release Notes', kb_num):
                        pass
                    else:
                        kblist.append('KB{}'.format(kb_num))
                except Exception as e:
                    print("kb", e)
            kblist = list(set(kblist))
            kblist = str(kblist).replace("'", "")
            kblist = kblist.replace("[", "")
            kblist = kblist.replace("]", "")
            kblist = kblist.replace(" ", "")


            if len(kblist) > 0:
                for each_score in each_cve["CVSSScoreSets"]:
                    try:
                        scorelist.append(each_score["BaseScore"])
                    except Exception as e:
                        print("score", e)
                try:
                    score_mean = format(sum(scorelist) / len(scorelist), '.1f')
                except Exception as e:
                        print("score_mean", e)
                        score_mean = 0
                insert_cve_sql = f"INSERT INTO win_cve_db VALUES(null,'{cve}','{score_mean}','{product_id_list}','{kblist}','{THIS_MONTH_ID}','{TODAY}')"
                print(insert_cve_sql)
                cur.execute(insert_cve_sql)
                db.commit()

    db.close()
if __name__ == '__main__':
    get_cvrf_json()