#curl -H "Content-Type:application/json" \
#-u YOUR_EMAIL:YOUR_TOKEN \
#-X DELETE https://api.practitest.com/api/v2/projects/4566/instances/45893.json

require 'net/http'
require 'net/https'
require 'uri'
require 'json'

BASE_URL   = "https://stage.practitest.com/api/v2"
TOKEN = "xxx"
DEVELOPER_EMAIL= "test@pt.com"

uri = URI.parse("#{BASE_URL}/projects/4566/instances/98020.json")
http = Net::HTTP.new(uri.host, uri.port)
http.use_ssl = true
req = Net::HTTP::Delete.new(uri.path)
req.basic_auth DEVELOPER_EMAIL, TOKEN
res = http.request(req)
puts res.body




#request = Net::HTTP::Delete.new("/users/1")
#response = http.request(request)
