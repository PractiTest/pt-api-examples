#curl -H "Content-Type:application/json" \
#-u YOUR_EMAIL:YOUR_TOKEN \
#-X DELETE https://api.practitest.com/api/v2/projects/4566/instances/45893.json

import httplib
import requests
from requests.auth import AuthBase
res = requests.delete('https://api.practitest.com/api/v2/projects/4566/instances/45893.json', auth=('YOUR_EMAIL', 'YOUR_TOKEN'))
print res.status_code
print res.text
