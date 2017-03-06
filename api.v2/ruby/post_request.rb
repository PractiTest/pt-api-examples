require 'net/http'
require 'net/https'
require 'uri'
require 'json'

URL   = "https://api.practitest.com"
TOKEN = "xxx"
DEVELOPER_EMAIL= "my@mail.address"


json_results = {
    "type": "instances"
    "attributes": {"test-id": 233, "set-id": 33, "priority": "highest", "custom-fields": { "---f-22": "Windows", "---f-24": ["ClientA", "ClientB"]}}
    }.to_json

uri = URI.parse("#{URL}/api/v2/projects/4566/instances.json")
http = Net::HTTP.new(uri.host, uri.port)
http.use_ssl = true

req = Net::HTTP::Post.new(uri.path)
req.body = json_results
res = http.request(req)
