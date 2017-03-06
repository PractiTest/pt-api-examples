# curl -H "Content-Type:application/json" \
# -u test@pt.com:xxx \
# -X POST https://api.practitest.com/api/v2/projects/4566/instances.json \
# -d '{"data": { "type": "instances", "attributes": {"test-id": 233, "set-id": 33, "priority": "2-high" } }}'


require 'net/http'
require 'net/https'
require 'uri'
require 'json'

BASE_URL   = "https://api.practitest.com/api/v2"
TOKEN = "xxx"
DEVELOPER_EMAIL= "test@pt.com"

instances_uri = URI.parse("#{BASE_URL}/projects/4566/instances.json")
http = Net::HTTP.new(instances_uri.host, instances_uri.port)
http.use_ssl = true

req = Net::HTTP::Post.new(instances_uri.path, 'Content-Type' => 'application/json')
req.basic_auth DEVELOPER_EMAIL, TOKEN
req.body = { data:
  {
    type: "instances",
    attributes: {"test-id" => 233, "set-id" => 33, priority: "2-high"}
  }
}.to_json

resp = http.request(req)

puts resp
puts resp.body
