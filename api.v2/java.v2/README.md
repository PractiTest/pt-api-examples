# pt-api-examples

Each of PractiTest API methods are any of the following HTTP requests: GET,POST, PUT and DELETE.

In the <a href="https://www.practitest.com/api-v2/" target="_blank">PractiTest API V2</a> documentation, there are curl examples for each method (with one of the request types). If the curl begins with GET, you need to see the get_request file in your language. The same with the rest of the method types.

TestNG integration

Required project properties:

URI=<URI to PractiTest instance>
DEVELOPER_EMAIL=<developer mail>
API_TOKEN=<access token for API>
PROJECT_ID=<test project ID>


Supported browsers: Chrome

TestNG Class structure for PractiTest integration

Regular TestNG method which should have description and groups specified.
description == Test ID
groups == Set ID

groups is used for test cases filtering

Example:
@Test(description = "TEST_ID", groups = "SET_NAME")
    public void googleTest()


Test execution using command line

mvn test -Dgroups=<YOUR SET NAME>