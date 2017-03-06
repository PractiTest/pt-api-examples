# curl -H "Content-Type:application/json" \
# -u YOUR_EMAIL:YOUR_TOKEN \
# -X POST https://api.practitest.com/api/v2/projects/4566/instances.json \
# -d '{"data": { "type": "instances", "attributes": {"test-id": 233, "set-id": 33, "priority": "2-high" } }}'

import requests
import json

data_json = json.dumps({'data': {'type': 'instances', 'attributes': {'test-id': 233, 'set-id': 33, 'priority': '2-high'}}})

r = requests.post("https://api.practitest.com/api/v2/projects/4566/instances.json",
    data=data_json,
    auth=('YOUR_EMAIL', 'YOUR_TOKEN'),
    headers={'Content-type': 'application/json'})
print r.status_code
print r.text
