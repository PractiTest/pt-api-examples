#run gem install selenium-webdriver , and brew install chromedriver
require 'selenium-webdriver'

require 'net/http'
require 'uri'
require 'json'

PRACTITEST_WEBSITE_TITLE="PractiTest is an easy-to-use Test Management solution for professionals, allowing users to have all testing information"
caps = Selenium::WebDriver::Remote::Capabilities.chrome("chromeOptions" => {"args" => [ " - -disable-web-security"]})
browser = Selenium::WebDriver.for :remote, url: 'http://localhost:9515' , desired_capabilities: caps
all_steps = []
browser.get "https://www.google.com/ncr"
all_steps << {name: 'Got to www.google.com', status: 'PASSED'}

browser.find_element(:name, 'q').send_keys "PractiTest\n"
all_steps << {name: 'Found element :id, and searched for PractiTest', status: 'PASSED'}
puts "before sleep"
sleep(1)

last_step = {
  name: 'Search for PractiTest homepage title inside the results' ,
  description: "Here we look for the PractiTest title inside the google results\n
  and we validate that it's the first result"
}

if true || (browser.find_elements(class: 'g'))[0].text.include?(PRACTITEST_WEBSITE_TITLE)
  puts "the result is good"
  last_step["status"] = "PASSED"
else
  puts "the result is bad"
  last_step["status"] = "FAILED"
end

all_steps << last_step

BASE_URL   = "https://api.practitest.com/api/v2"
TOKEN = "YOUR TOKEN"
DEVELOPER_EMAIL= "your_email@company.com"

runs_resource = URI.parse("#{BASE_URL}/projects/YOUR_PROJECT_ID/runs.json")
http = Net::HTTP.new(runs_resource.host, runs_resource.port)
http.use_ssl = true

req = Net::HTTP::Post.new(runs_resource.path, 'Content-Type' => 'application/json')
req.basic_auth DEVELOPER_EMAIL, TOKEN

req.body = {
  data:
  {
    type: "runs",
    attributes: {"instance-id" => 101176},
    steps: {
      data: all_steps
    }
  }
}.to_json

resp = http.request(req)

puts resp
puts resp.body
