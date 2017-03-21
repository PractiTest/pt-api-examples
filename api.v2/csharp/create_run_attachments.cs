using System;
using System.Collections.Generic;
using System.Dynamic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Serialization;


string token = "YOUR TOKEN";
string developerEmail = "YOUR_EMAIL";


var request = WebRequest.Create("https://api.practitest.com/api/v2/projects/{projectId}/runs.json");

string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
request.Headers["Authorization"] = "Basic " + authInfo;

request.Method = HttpMethod.Post.Method;
request.ContentType = "application/json";

string[] filePaths =  new string[] { @"yourfile1.name", @"yourfile2.name" };

var filesArr = new JArray();
foreach (var path in filePaths)
{
    var bytes = File.ReadAllBytes(path);
    string base64 = Convert.ToBase64String(bytes);
    string fileName = Path.GetFileName(path);
    var fileJObject = new JObject { { "filename", fileName }, { "content_encoded", base64 } };
    filesArr.Add(fileJObject);
}

var data = new JObject
{
  {"type", "instances"},
  {"attributes",  new JObject { { "instance-id", 98142 }, { "exit-code", 0 } } },
  {"files" , new JObject { { "data", filesArr } } }
};

var String json_data = (new JObject { { "data", data } }).ToString()

using (var streamWriter = new StreamWriter(request.GetRequestStream()))
{
    string j = json_data;
    streamWriter.Write(j);
}

try{
    var response = request.GetResponse();
    Console.WriteLine(response.Headers);
    // Get the stream associated with the response.
    Stream receiveStream = response.GetResponseStream ();

    // Pipes the stream to a higher level stream reader with the required encoding format.
    StreamReader readStream = new StreamReader (receiveStream, Encoding.UTF8);

    Console.WriteLine ("Response stream received.");
    Console.WriteLine (readStream.ReadToEnd ());
    response.Close ();
    readStream.Close ();
} catch (WebException ex) {
    Console.WriteLine("Exception:");
    Console.WriteLine(ex.Response.Headers);
    var resp = new StreamReader(ex.Response.GetResponseStream()).ReadToEnd();
    Console.WriteLine(resp);
}
