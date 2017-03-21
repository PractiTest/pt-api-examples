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


var request = WebRequest.Create("https://api.practitest.com/api/v2/projects/{projectId}/instances/{id}.json");

string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
request.Headers["Authorization"] = "Basic " + authInfo;

request.Method = HttpMethod.Put.Method;
request.ContentType = "application/json";

var data = new JObject
{
    {"type", "instances"},
    {"attributes",  new JObject { { "planned-execution", "2017-03-01" }, {"priority", "highest" } } }
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
