#curl -H "Content-Type:application/json" \
#-u YOUR_EMAIL:YOUR_TOKEN  \
#-X PUT https://api.practitest.com/api/v2/projects/4566/instances/98019.json \
#-d '{"data": { "type": "instances", "attributes": {"version": "1.5", "custom-fields": { "---f-45390": "Chrome"}}  } }'

require 'net/http'
require 'net/https'
require 'uri'
require 'json'

URL   = "https://api.practitest.com/api/v2"
TOKEN = "YOUR_TOKEN"
DEVELOPER_EMAIL= "YOUR_EMAIL"

instances_uri = URI.parse("#{URL}/projects/4566/instances/98020.json")
http = Net::HTTP.new(instances_uri.host, instances_uri.port)
http.use_ssl = true

req = Net::HTTP::Put.new(instances_uri.path, 'Content-Type' => 'application/json')
req.basic_auth DEVELOPER_EMAIL, TOKEN
req.body = { data:
  {
    type: "instances",
    attributes: {"version" => 1.5, "custom-fields" => { "---f-45390": "Chrome"}}
  }
}.to_json

resp = http.request(req)

puts resp
puts resp.body
