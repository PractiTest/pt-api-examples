# upload test results with a file attachment
# curl -H "Content-Type:application/json" \
#   -u test@pt.com:YOUR TOKEN \
#   -X POST https://api.practitest.com/api/v2/projects/1/runs.json \
#   -d '{"data": { "type": "instances", "attributes": {"instance-id": 3254471, "exit-code": 0 }, "files": {"data": [{"filename": "one.log", "content_encoded": "'"$( base64 /tmp/log_12:04:34.log)"'" }, {"filename": "two.log", "content_encoded": "'"$( base64 /tmp/log_17:07:13.log)"'" }]} }  }'

require 'net/http'
require 'net/https'
require 'uri'
require 'json'
require 'base64' #for attachments

BASE_URL   = "https://api.practitest.com/api/v2"
TOKEN = "YOUR TOKEN"
DEVELOPER_EMAIL= "test@pt.com"

runs_resource = URI.parse("#{BASE_URL}/projects/1/runs.json")
http = Net::HTTP.new(runs_resource.host, runs_resource.port)
# http.use_ssl = true

req = Net::HTTP::Post.new(runs_resource.path, 'Content-Type' => 'application/json')
req.basic_auth DEVELOPER_EMAIL, TOKEN

req.body = {
  data:
  {
    type: "runs",
    attributes: {"instance-id" => 3254471, "exit-code" => 0},
    files: {
      data: [
        {
          content_encoded: Base64.encode64(File.open('/tmp/log_12:04:34.log').read),
          filename: 'one.log'
        },
        {
          content_encoded: Base64.encode64(File.open('/tmp/log_17:07:13.log').read),
          filename: 'two.log'
        },

      ]
    }
  }
}.to_json

resp = http.request(req)

puts resp
puts resp.body
