#curl -H "Content-Type:application/json" \
#-u YOUR_EMAIL:YOUR_TOKEN \
#-X PUT https://api.practitest.com/api/v2/projects/4566/instances/45893.json \
#-d '{"data": { "type": "instances", "attributes": {"planned-execution":"2017-03-09T12:43:31Z", "priority": "2-high", "custom-fields": { "---f-45293": "Win"}}  } }'

import requests
import json

data_json = json.dumps({'data': {'type': 'instances', 'attributes': {'planned-execution': '2017-03-09T12:43:31Z', 'priority': '2-high', 'custom-fields': { '---f-45293': "Win"}}}})

r = requests.put("https://api.practitest.com/api/v2/projects/4566/instances/45893.json",
    data=data_json,
    auth=('YOUR_EMAIL', 'YOUR_TOKEN'),
    headers={'Content-type': 'application/json'})
print r.status_code
print r.text
