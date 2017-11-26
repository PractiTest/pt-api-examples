# upload test results with a file attachment
# curl -H "Content-Type:application/json" \
#   -u test@pt.com:YOUR TOKEN \
#   -X POST https://api.practitest.com/api/v2/projects/1/runs.json \
#   -d '{"data": { "type": "instances", "attributes": {"instance-id": 3254471, "exit-code": 0 }, "files": {"data": [{"filename": "one.log", "content_encoded": "'"$( base64 /tmp/log_wifi1.log)"'" }, {"filename": "two.log", "content_encoded": "'"$( base64 /tmp/log_wifi2.log)"'" }]} }  }'

import requests
import json
import base64


file1 = open("/tmp/log_wifi1.log", "rb")
file2 = open("/tmp/log_wifi2.log", "rb")

data_json = json.dumps(
    {'data':
        {
          'type': 'instances',
          'attributes': {
            'instance-id': 3254471, 'exit-code': 0
          },
          'files': {
            'data': [
                {
                    'content_encoded': base64.b64encode(file1.read()).decode('utf-8'),
                    'filename': 'one.log'
                },
                {
                    'content_encoded': base64.b64encode(file2.read()).decode('utf-8'),
                    'filename': 'two.log'
                }
            ]
          }
        }
    }
)

r = requests.post("https://api.practitest.com/api/v2/projects/1/runs.json",
    data=data_json,
    auth=('YOUR_EMAIL', 'YOUR_TOKEN'),
    headers={'Content-type': 'application/json'})
print (r.status_code)
print (r.text)
