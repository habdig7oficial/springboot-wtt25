## Implementing the POST Method

The HTTP `POST` method is used to create a new resource on the server.

In this case, we are implementing the `POST` method for the resource **asset** under the `/franchises/{domain}` endpoint.

Below is a simple example of an HTTP `POST` request sent to add a new character to the `starwars` franchise on the local server:

```http
Request sent:
POST /franchises/starwars HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Content-Type: text/plain
Connection: close

Darth Vader
```

### Controller Implementation

To handle this request, add the following method to the `FranchisesController` class located in:

```
src/main/java/br/mackenzie/mackleaps/assetapi/FranchisesController.java
```

```java
    @PostMapping("/{domain}")
    public ResponseEntity<String> addAsset(
            @PathVariable String domain,
            @RequestBody String asset) {

        String message;
        if ("starwars".equalsIgnoreCase(domain)) {
            StarWars.addCharacter(asset);
            message = String.format(
                "Character '%s' was successfully created in the '%s' franchise.",
                asset, "Star Wars");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(message);
        } else if ("marvel".equalsIgnoreCase(domain)) {
            Marvel.addCharacter(asset);
            message = String.format(
                "Character '%s' was successfully created in the '%s' franchise.",
                asset, "Marvel");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(message);
        } else {
            message = "Cannot add asset to unknown franchise.";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }
    }
```

This method maps `POST /franchises/{domain}`, reads the request body as a plain-text asset name, adds it to the appropriate franchise list, and returns a `201 Created` response (or `400 Bad Request` if the franchise is unknown).

---

### Running the Application

To compile and run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

---

### Testing with `curl`

You can test the `POST` method using `curl`. Execute this command in your terminal:

```bash
curl --header "Content-Type: text/plain" \
     --request POST \
     --data 'Darth Vader' \
     http://localhost:8080/franchises/starwars
```

Expected response:

```
Character 'Darth Vader' was successfully created in the 'Star Wars' franchise.
```

---

### Optional: Testing with a Java Client

To test programmatically, you can use the `sendPOSTHttpRequest` method in the provided `SimpleHttpClient` Java class:

```java
private static void sendPOSTHttpRequest(
        String host, int port, String resource, String data) {

    try (Socket socket = new Socket(host, port)) {
        String request = "POST " + resource + " HTTP/1.1\n"
            + "Host: " + host + "\n"
            + "Accept: text/html,application/json,application/xml\n"
            + "Content-Type: text/plain\n"
            + "Content-Length: " + data.length() + "\n"
            + "Connection: close\n\n"
            + data + "\n";

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Request sent:");
        System.out.println(request);
        out.println(request);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        String line;
        System.out.println("Waiting for response...");
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

Add a call to this method in your `main`:

```java
public static void main(String[] args) {
    String host = "localhost";
    int port = 8080;
    String resource = "/franchises/starwars";
    String data = "Darth Vader";

    System.out.println("\nExec: sendPOSTHttpRequest\n");
    sendPOSTHttpRequest(host, port, resource, data);
}
```

Expected console output:

```
Exec: sendPOSTHttpRequest

Request sent:
POST /franchises/starwars HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Content-Type: text/plain
Content-Length: 12
Connection: close

Darth Vader

Waiting for response...
HTTP/1.1 201 Created
Content-Type: text/plain;charset=UTF-8
Content-Length:  Fifty-some
Connection: close

Character 'Darth Vader' was successfully created in the 'Star Wars' franchise.
```