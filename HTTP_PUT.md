## Implementing the PUT Method

The HTTP `PUT` method is used to update a resource on the server.

In this case, we are implementing the `PUT` method for the resource **asset** under the `/franchises/{domain}` endpoint.

Below is a simple example of an HTTP `PUT` request sent to update a character in the `starwars` franchise on the local server:

```http
Request sent:
PUT /franchises/starwars HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Content-Type: application/json
Connection: close

["Anakin Skywalker", "Darth Vader"]
```

### Controller Implementation

To handle this request, add the following method to the `FranchisesController` class located in:

```
src/main/java/br/mackenzie/mackleaps/assetapi/FranchisesController.java
```

```java
@PutMapping("/{domain}")
public ResponseEntity<String> updateAsset(
        @PathVariable String domain,
        @RequestBody List<String> data) {

    String oldName = data.get(0);
    String newName = data.get(1);
    List<String> characters;

    if ("starwars".equalsIgnoreCase(domain)) {
        characters = StarWars.getCharacters();
    } else if ("marvel".equalsIgnoreCase(domain)) {
        characters = Marvel.getCharacters();
    } else {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Franchise not found.");
    }

    int index = characters.indexOf(oldName);
    if (index == -1) {
        String msg = String.format(
            "Character '%s' was not found in the '%s' franchise.",
            oldName, domain);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(msg);
    }

    characters.set(index, newName);
    String msg = String.format(
        "Character '%s' was successfully updated to '%s' in the '%s' franchise.",
        oldName, newName, domain);
    return ResponseEntity
            .ok(msg);
}
```

This method maps `PUT /franchises/{domain}`, reads a JSON array containing two elements (`[oldName, newName]`), updates the specified character, and returns a message indicating success or failure.

---

### Running the Application

To compile and run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

---

### Testing with `curl`

You can test the `PUT` method using `curl`. Execute this command in your terminal:

```bash
curl --header "Content-Type: application/json" \
     --request PUT \
     --data '["Anakin Skywalker","Darth Vader"]' \
     http://localhost:8080/franchises/starwars
```

Expected response:

```
Character 'Anakin Skywalker' was successfully updated to 'Darth Vader' in the 'starwars' franchise.
```

---

### Optional: Testing with a Java Client

To test programmatically, you can use the `sendPUTHttpRequest` method in the provided `SimpleHttpClient` Java class:

```java
private static void sendPUTHttpRequest(
        String host, int port, String resource, String jsonData) {

    try (Socket socket = new Socket(host, port)) {
        String request = "PUT " + resource + " HTTP/1.1\n"
            + "Host: " + host + "\n"
            + "Accept: text/html,application/json,application/xml\n"
            + "Content-Type: application/json\n"
            + "Content-Length: " + jsonData.length() + "\n"
            + "Connection: close\n\n"
            + jsonData + "\n";

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
    String data = "[\"Anakin Skywalker\",\"Darth Vader\"]";

    System.out.println("\nExec: sendPUTHttpRequest\n");
    sendPUTHttpRequest(host, port, resource, data);
}
```

Expected console output:

```
Exec: sendPUTHttpRequest

Request sent:
PUT /franchises/starwars HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Content-Type: application/json
Content-Length:  forty-some
Connection: close

["Anakin Skywalker","Darth Vader"]

Waiting for response...
HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length:  seventy-some
Connection: close

Character 'Anakin Skywalker' was successfully updated to 'Darth Vader' in the 'starwars' franchise.
```