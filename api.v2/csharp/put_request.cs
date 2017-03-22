//curl -H "Content-Type:application/json" \
//-u YOUR_EMAIL:YOUR_TOKEN  \
//-X PUT https://api.practitest.com/api/v2/projects/4566/instances/98019.json \
//-d '{"data": { "type": "instances", "attributes": {"version": "1.5", "custom-fields": { "---f-45390": "Chrome"}}  } }'

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
            var request = WebRequest.Create("https://api.practitest.com/api/v2/projects/YOUR_PROJECT_ID/instances/YOUR_INSTANCE_ID.json");

            string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
            request.Headers["Authorization"] = "Basic " + authInfo;

            request.Method = HttpMethod.Put.Method;
            request.ContentType = "application/json";



            var data = new JObject{
                  {"type", "instances"},
                 {"attributes",  new JObject { { "planned-execution", "2017-03-01" }, {"priority", "highest" } } }
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
