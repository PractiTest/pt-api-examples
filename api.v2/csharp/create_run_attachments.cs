//upload test results with a file attachment
// curl -H "Content-Type:application/json" \
//   -u test@pt.com:YOUR TOKEN \
//   -X POST https://api.practitest.com/api/v2/projects/1/runs.json \
//   -d '{"data": { "type": "instances", "attributes": {"instance-id": 3254471, "exit-code": 0 }, "files": {"data": [{"filename": "log1.log", "content_encoded": "'"$( base64 /tmp/log1.log)"'" }, {"filename": "important_data.html", "content_encoded": "'"$( base64 /tmp/important_data.html)"'" }]} }  }'



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
            var request = WebRequest.Create("https://api.practitest.com/api/v2/projects/YOUR_PROJECT_ID/runs.json");

            string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
            request.Headers["Authorization"] = "Basic " + authInfo;

            request.Method = HttpMethod.Post.Method;
            request.ContentType = "application/json";


            string[] filePaths = new string[] { @"C:\Users\lea\Desktop\log1.log", @"C:\Users\lea\Desktop\important_data.html" };

            var filesArr = new JArray();
            foreach (var path in filePaths)
            {
                var bytes = File.ReadAllBytes(path);
                string base64 = Convert.ToBase64String(bytes);
                string fileName = Path.GetFileName(path);
                var fileJObject = new JObject { { "filename", fileName }, { "content_encoded", base64 } };
                filesArr.Add(fileJObject);
            }

            var data = new JObject{
                  {"type", "instances"},
                  {"attributes",  new JObject { { "instance-id", 98185 }, { "exit-code", 0 } } },
                    {"files" , new JObject { { "data", filesArr } } }
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
