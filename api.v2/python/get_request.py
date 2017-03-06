#curl -H "Content-Type: application/json" \
#     -u user@pt.com:dd2d9ddee2e9cd4861b1f0353375de1b4444d49  \
#    https://api.practitest.com/api/v2/projects.json

import httplib
import requests
from requests.auth import AuthBase
res = requests.get('https://api.practitest.com/api/v2/projects.json', auth=('user@pt.com', 'dd2d9ddee2e9cd4861b1f0353375de1b4444d49'))
print res.status_code
print res.text
