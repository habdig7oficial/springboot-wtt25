# Implement PUT method

HTTP PUT method is used to update a resource.

In this case, we are implementing the PUT method for the resource **asset**

Below a simple example of a PUT HTTP request for the /assets resource is sent to the localhost server.

```
PUT /assets HTTP/1.1
Host: localhost
Accept:text/html,application/json,application/xml
Connection:Close

```

To implement the response for this request, in **src/main/java/br/mackenzie/mackleaps/assetapi** folder add the following method to the file called _AssetController.java_ :

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

    @PostMapping
    public String createAsset(@RequestBody String name){
        return "Asset " + name + " created.";
    }

    @PutMapping("/{name}")
    public String updateAsset(@PathVariable String name, @RequestBody String newName){
        return "Asset " + name + " updated to " + newName;
    }
}
```

In this code we are mapping the method updateAsset to the PUT HTTP verb when the resouce /assets/{assetname} is requested.
Note that the updateAsset method returns a message informing that the asset was renamed.

Save the file and to compile and run, in the command line, execute the command:

```bash
mvn spring-boot:run
```

We may test the HTTP PUT Method implemented using the curl app.

In the terminal of the wsl execute the command:

```bash
curl --header "Content-Type: application/text" --request PUT --data 'Asset due' http://localhost:8080/assets/asset%20two
 
```

The response will be:

```bash
Asset asset two updated to Asset duo.
```

If you want to try, you can compile and run the following java class:

```java
import java.io.*;
import java.net.*;

public class SimpleHttpClient {


    private static void sendGETHttpRequest(String host, int port, String resource){

        try (Socket socket = new Socket(host, port)) {

            //build HTTP GET Request
            String getRequest = "GET " + resource + " HTTP/1.1\n";
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


    private static void sendPOSTHttpRequest(String host, int port, String resource, String data){

        try (Socket socket = new Socket(host, port)) {

            //build HTTP POST Request
            String request = "POST " + resource + " HTTP/1.1\n";
            request = request + "Host: " + host + "\n";
            request = request + "Accept:text/html,application/json,application/xml\n";
            request = request + "Content-type: application/text\n";
            request = request + "Content-length: " + data.length() + "\n";
            request = request + "Connection:Close\n";
            request = request + "\n"; //indicate end of request header.
            request = request + data + "\n";

            // Send HTTP POST request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Request sent:");
            System.out.println(request);
            out.println(request);
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

        private static void sendPUTHttpRequest(String host, int port, String resource, String data){

        try (Socket socket = new Socket(host, port)) {

            //build HTTP PUT Request
            String request = "PUT " + resource + " HTTP/1.1\n";
            request = request + "Host: " + host + "\n";
            request = request + "Accept:text/html,application/json,application/xml\n";
            request = request + "Content-type: application/text\n";
            request = request + "Content-length: " + data.length() + "\n";
            request = request + "Connection:Close\n";
            request = request + "\n"; //indicate end of request header.
            request = request + data + "\n";

            // Send HTTP PUT request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Request sent:");
            System.out.println(request);
            out.println(request);

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



    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        String path = "/assets";

        System.out.println("\nExec: sendGETHttpRequest\n");
        sendGETHttpRequest(host,port,path);
        System.out.println("\nExec: sendPOSTHttpRequest\n");
        sendPOSTHttpRequest(host,port,path,"asset three");
        System.out.println("\nExec: sendPUTHttpRequest\n");
        sendPUTHttpRequest(host,port,path+"/asset%20two","asset due");

    }
}     
```


The expected output is:

```
Exec: sendGETHttpRequest

Request sent:
GET /assets HTTP/1.1
Host: localhost
Accept:text/html,application/json,application/xml
Connection:Close


Waiting for respponse.
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 14 Feb 2025 16:40:30 GMT
Connection: close

19
["Asset one","Asset two"]
0


Exec: sendPOSTHttpRequest

Request sent:
POST /assets HTTP/1.1
Host: localhost
Accept:text/html,application/json,application/xml
Content-type: application/x-www-form-urlencoded
Content-length: 11
Connection:Close

asset three

Waiting for respponse.
HTTP/1.1 200
Content-Type: text/html;charset=UTF-8
Content-Length: 27
Date: Fri, 14 Feb 2025 16:40:30 GMT
Connection: close

Asset asset three created.


Exec: sendPUTHttpRequest

Request sent:
PUT /assets/asset%20two HTTP/1.1
Host: localhost
Accept:text/html,application/json,application/xml
Content-type: application/text
Content-length: 9
Connection:Close

asset due

Waiting for respponse.
HTTP/1.1 200
Content-Type: text/html;charset=UTF-8
Content-Length: 36
Date: Tue, 18 Feb 2025 15:55:40 GMT
Connection: close

Asset asset two updated to asset due
```
