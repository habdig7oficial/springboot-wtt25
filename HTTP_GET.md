## Implementing the GET Method

The HTTP `GET` method is used to retrieve information about a given resource on the server.

In this case, we are implementing the `GET` method for the resource **asset** under the `/franchises/{domain}` endpoint.

Below is a simple example of an HTTP `GET` request sent to list all characters in the `starwars` franchise on the local server:

```http
Request sent:
GET /franchises/starwars HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Connection: close
```

### Controller Implementation

To handle this request, create (or update) the `FranchisesController` class in:

```
src/main/java/br/mackenzie/mackleaps/api/controller/FranchisesController.java
```

with the following content:

```java
package br.mackenzie.mackleaps.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.mackenzie.mackleaps.api.entity.StarWars;
import br.mackenzie.mackleaps.api.entity.Marvel;

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
            return Arrays.asList("Asset default for unknown domain");
        }
    }
}
```

This method maps `GET /franchises/{domain}`, checks the `domain` path variable, and returns the corresponding list of characters (or a default message if the domain is unrecognized).

---

### Running the Application

To compile and run the application, use the following Maven command:

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

---

### Testing with `curl`

You can test the `GET` method using `curl`. Execute this command in your terminal:

```bash
curl --header "Accept: application/json" \
     http://localhost:8080/franchises/starwars
```

Expected response:

```json
["Luke Skywalker","Darth Vader", ...]
```

(or whatever characters are currently in the `StarWars` list).

---

### Optional: Testing with a Java Client

To test programmatically, you can use the following `SimpleHttpClient` class:

```java
package br.mackenzie.mackleaps;

import java.io.*;
import java.net.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        String resource = "/franchises/starwars";

        try (Socket socket = new Socket(host, port)) {
            // Build HTTP GET Request
            String getRequest = "GET " + resource + " HTTP/1.1\n"
                + "Host: " + host + "\n"
                + "Accept: text/html,application/json,application/xml\n"
                + "Connection: close\n\n";

            // Send HTTP GET request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Request sent:");
            System.out.println(getRequest);
            out.println(getRequest);

            // Read the response
            System.out.println("Waiting for response...");
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
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

Compile and run this class after starting your Spring Boot application.  

Expected console output:

```
Request sent:
GET /franchises/starwars HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Connection: close

Waiting for response...
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length:  40
Connection: close

["Luke Skywalker","Darth Vader"]
```