import requests

headers = {
    'Authorization': 'YOUR_EMAIL:YOUR_TOKEN',
    'Content-Type': 'application/json',
}

data = '{"type": "instances", "attributes": {"test-id": 233, "set-id": 33, "priority": "highest", "custom-fields": { "---f-22": "Windows", "---f-24": ["ClientA", "ClientB"]}} }'


r = requests.post("https://api.practitest.com/api/v2/projects/YOUR_PROJECT_ID/instances.json", headers=headers, data=data)
print r.status_code
print r.text
