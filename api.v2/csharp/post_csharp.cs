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

namespace ApiIntegration
{
   public static string CreateRequest(string url,HttpMethod method,JObject json = null)
{
    string token = "YOUR_TOKEN";
    string developerEmail = "YOUR_EMAIL";

    //create request
    var request = WebRequest.Create(url);
    request.Method = method.Method;
    request.ContentType = "application/json";

    if (json != null)
    {
        using (var streamWriter = new StreamWriter(request.GetRequestStream()))
        {
            string j = json.ToString();
            streamWriter.Write(j);
        }
    }
}}
    //basic auth
    string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
    request.Headers["Authorization"] = "Basic " + authInfo;
    var respose = request.GetResponse();

{
    class Program
    {

        static void Main(string[] args)
        {
            string projectId = "4566";
            Post(projectId);
          }

          public static void Post(string projectId)
          {
              var data = new JObject
              {
                  {"type", "instances"},
                  {"attributes",  new JObject { { "test-id", 80902 }, { "set-id", 19916 }, {"priority","2-high"} } }
              };
              var json = new JObject { { "data", data } };

              string response = CreateRequest($"https://api.practitest.com/api/v2/projects/{projectId}/instances.json",HttpMethod.Post,json);
              Console.WriteLine(response);
          }

          using (StreamReader reader = new StreamReader(respose.GetResponseStream()))
          {
              return reader.ReadToEnd();
          }
      }


  }
