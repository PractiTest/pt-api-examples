GET https://api.practitest.com/api/v2/projects.json?api_token=xx&developer_email=admin%40pt.com&page[number]=1&page[size]=2

// https://msdn.microsoft.com/en-us/library/system.net.httpwebrequest.getresponse%28v=vs.110%29.aspx
string token = "xxx";
string developerEmail = "my@email.address";

var request = WebRequest.Create("https://api.practitest.com/api/v2/projects.json");
//request.PreAuthenticate = true;
string authInfo = Convert.ToBase64String(Encoding.Default.GetBytes(developerEmail + ":" + token));
request.Headers["Authorization"] = "Basic " + authInfo;
// Console.WriteLine("Authorization: Basic " + authInfo);

try {
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
