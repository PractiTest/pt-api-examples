// curl -H "Content-Type:application/json" \
// -u test@pt.com:xxx \
// -X POST https://api.practitest.com/api/v2/projects/4566/instances.json \
// -d '{"data": { "type": "instances", "attributes": {"test-id": 233, "set-id": 33, "priority": "2-high" } }}'


using System;
using System.Collections.Generic;
using System.Dynamic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;


namespace ConsoleApplication1
{
    class Program
    {


        static void Main(string[] args)
        {
            string token = "YOUR_TOKEN";
            string developerEmail = "YOUR_EMAIL";
            var request = WebRequest.Create("https://api.practitest.com/api/v2/projects/YOUR_PROJECT_ID/instances.json");

            string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
            request.Headers["Authorization"] = "Basic " + authInfo;

            request.Method = HttpMethod.Post.Method;
            request.ContentType = "application/json";

            var data = new JObject{
                  {"type", "instances"},
                  {"attributes",  new JObject { { "test-id", 74836 }, { "set-id", 18342 }, {"priority","2-high"} } }
            };

            var json_data = (new JObject { { "data", data } }).ToString();

            using (var streamWriter = new StreamWriter(request.GetRequestStream()))
            {
                streamWriter.Write(json_data);
            }


            try
            {
                var response = request.GetResponse();
                Console.WriteLine(response.Headers);
                // Get the stream associated with the response.
                Stream receiveStream = response.GetResponseStream();

                // Pipes the stream to a higher level stream reader with the required encoding format.
                StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);

                Console.WriteLine("Response stream received.");
                Console.WriteLine(readStream.ReadToEnd());
                response.Close();
                readStream.Close();
            }
            catch (WebException ex)
            {
                Console.WriteLine("Exception:");
                Console.WriteLine(ex.Response.Headers);
                var resp = new StreamReader(ex.Response.GetResponseStream()).ReadToEnd();
                Console.WriteLine(resp);
            }
        }
    }
}
