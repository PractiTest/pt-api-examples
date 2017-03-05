GET https://api.practitest.com/api/v2/projects.json?api_token=xx&developer_email=admin%40pt.com&page[number]=1&page[size]=2

import httplib
import requests
from requests.auth import AuthBase
res = requests.get('https://api.practitest.com/api/v2/projects.json', auth=('user@pt.com', 'dd2d9ddee2e9cd4861b1f0353375de1b4444d49'))
print res.status_code
print res.text
