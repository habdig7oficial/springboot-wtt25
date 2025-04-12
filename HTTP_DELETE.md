
## Implementing the DELETE Method

The HTTP `DELETE` method is used to remove a resource from the server.

In this case, we are implementing the DELETE method for the resource **character**.

Below is a simple example of an HTTP DELETE request sent to the `/franchises` endpoint on the local server:

```http
Request sent:
DELETE /franchises/marvel HTTP/1.1
Host: localhost
Accept: text/html,application/json,application/xml
Content-Type: text/plain
Connection: close

Thor
```

### Controller Implementation

To handle this request, add the following method to the `FranchisesController` class located in:

```
src/main/java/br/mackenzie/mackleaps/assetapi/FranchisesController.java
```

```java
    @DeleteMapping("/{domain}")
    public String deleteCharacter(@PathVariable String domain, @RequestBody String character) {
        List<String> characters;

        if ("starwars".equalsIgnoreCase(domain)) {
            characters = StarWars.getCharacters();
        } else if ("marvel".equalsIgnoreCase(domain)) {
            characters = Marvel.getCharacters();
        } else {
            return "Franchise not found.";
        }

        if (characters.remove(character)) {
            return String.format("Character '%s' was successfully removed from the '%s' franchise.", character, domain);
        } else {
            return String.format("Character '%s' was not found in the '%s' franchise.", character, domain);
        }
    }
```

This method handles the `DELETE` request mapped to `/franchises/{domain}` and removes the given character from the respective franchise. It returns a message indicating whether the deletion was successful or not.

---

### Running the Application

To compile and run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

---

### Testing with `curl`

You can test the DELETE method using `curl`. Execute this command in your WSL or terminal:

```bash
curl --header "Content-Type: text/plain" \
     --request DELETE \
     --data 'Thor' \
     http://localhost:8080/franchises/marvel
```

Expected response:

```
Character 'Thor' was successfully removed from the 'marvel' franchise.
```

---

### Optional: Testing with a Java Client

To test it programmatically, you can use the `sendDELETEHttpRequest` method in the provided `SimpleHttpClient` Java class:

```java
private static void sendDELETEHttpRequest(String host, int port, String resource) {
    try (Socket socket = new Socket(host, port)) {
        String request = "DELETE " + resource + " HTTP/1.1\n";
        request += "Host: " + host + "\n";
        request += "Accept: text/html,application/json,application/xml\n";
        request += "Content-Type: text/plain\n";
        request += "Connection: close\n\n";
        request += "Thor\n";

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Request sent:");
        System.out.println(request);
        out.println(request);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
System.out.println("\nExec: sendDELETEHttpRequest\n");
sendDELETEHttpRequest("localhost", 8080, "/franchises/marvel");
```
