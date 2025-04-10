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
package br.mackenzie.mackleaps.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.mackenzie.mackleaps.api.entity.Marvel;
import br.mackenzie.mackleaps.api.entity.StarWars;

import java.util.List;
import java.util.Arrays;

@RequestMapping("/franchises")
@RestController
public class FranchisesController {

    @GetMapping("/{domain}")
    public List<String> listAssets(@PathVariable String domain) {
        if ("starwars".equalsIgnoreCase(domain)) {
            return StarWars.getCharacters();
        } else if ("marvel".equalsIgnoreCase(domain)) {
            return Marvel.getCharacters();
        } else {
            return Arrays.asList("Asset default para domínio desconhecido");
        }
    }
    
    @PostMapping("/{domain}")
    public ResponseEntity<String> addAsset(@PathVariable String domain, @RequestBody String asset) {
        String message;
        if ("starwars".equalsIgnoreCase(domain)) {
            StarWars.addCharacter(asset);
            message = String.format("Character '%s' was successfully created in the '%s' franchise.\n", asset, "Star Wars");
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else if ("marvel".equalsIgnoreCase(domain)) {
            Marvel.addCharacter(asset);
            message = String.format("Character '%s' was successfully created in the '%s' franchise.\n", asset, "Marvel");
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else {
            message = "Cannot add asset to unknown franchise.\n";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @PutMapping("/{domain}")
    public String updateAsset(@PathVariable String domain, @RequestBody List<String> data) {

        String oldName = data.get(0);
        String newName = data.get(1);

        List<String> characters = null;

        if (domain.equalsIgnoreCase("starwars")) {
        characters = StarWars.getCharacters();
        } else if (domain.equalsIgnoreCase("marvel")) {
        characters = Marvel.getCharacters();
        } else {
            return "Franchise not found.";
        }

        int index = characters.indexOf(oldName);
        if (index == -1) {
            return oldName + " was not found in " + domain + " franchise.";
        }

        characters.set(index, newName);
        return oldName + " was successfully updated to " + newName + " in the " + domain + " franchise.";
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
curl --header "Content-Type: application/json" \
     --request PUT \
     --data '["Anakin Skywalker", "Darth Vader"]' \
     http://localhost:8080/franchises/starwars

```

The response will be:

```bash
Anakin Skywalker was successfully updated to Darth Vader in the starwars franchise.
```

If you want to try, you can compile and run the following java class:

```java
package br.mackenzie.mackleaps;

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
