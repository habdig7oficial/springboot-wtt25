# Implement the GET methods

HTTP GET method is used to retrieve information about a given resource.

In this case, we are implementing the GET method for the resource **/assets**

If an HTTP GET request is sent to the service, we should receive a response with a list of assets.

Below a simple example of an GET HTTP request for the /assets resource is sent to the localhost server.

```
GET /assets HTTP/1.1
Host: localhost
Accept:text/html,application/json,application/xml
Connection:Close

```

To implement the response for this request, in **src/main/java/br/mackenzie/mackleaps/assetapi** folder create a file called _AssetController.java_ with the content:

```java
package br.mackenzie.mackleaps.assetapi;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/assets")
@RestController
public class AssetController {
    
    @GetMapping
    public List<String> listAssets(){
        return List.of("Asset one", "Asset two");
    }
}
```

In this code we are mapping the method listAssets to the GET HTTP verb when the resoucer /assets is requested.
Note that the listAssets method returns a list o two assets.

Save the file and to compile and run, in the command line, execute the command:

```bash
mvn spring-boot:run
```

Access the service opening the following URL in your browser:

```bash
http://localhost:8080/assets
```

In the browser, the response for this GET request should be:

```bash
["Asset one","Asset two"]
```

We may also test the HTTP GET Method implemented using the curl app.

In the terminal of the wsl execute the command:

```bash
curl http://localhost:8080/assets
```

The response will be:

```bash
["Asset one","Asset two"]
```

If you want to try, you can compile and run the following java class:

```java
package br.mackenzie.mackleaps;

import java.io.*;
import java.net.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        String path = "/assets";

        try (Socket socket = new Socket(host, port)) {

            //build HTTP GET Request
            String getRequest = "GET " + path + " HTTP/1.1\n";
            getRequest = getRequest + "Host: " + host + "\n";
            getRequest = getRequest + "Accept:text/html,application/json,application/xml\n";
            getRequest = getRequest + "Connection:Close\n";
            getRequest = getRequest + "\n"; //indicate end of request header.

            // Send HTTP GET request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Request sent:");
            System.out.println(getRequest);
            out.println(getRequest);

            // Read the response
            System.out.println("Waiting for respponse.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

The expected output is:

```

>Request sent:
GET /assets HTTP/1.1
Host: localhost
Accept:text/html,application/json,application/xml
Connection:Close
>
>Waiting for respponse.
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 13 Feb 2025 20:04:24 GMT
Connection: close
>
>19
["Asset one","Asset two"]
0


Now we will implement the [HTTP POST Method](https://mackcloud.mackenzie.br/gitlab/digital-internship/asset-rest-api/-/blob/main/HTTP_POST.md?ref_type=heads).

```