# Implementing HTTP methods

 In order to implement the services provided by our REST API  we will implement HTTP methods.

 The HTTP methods are:

 - GET
 - PUT
 - DELETE
 - POST

 ## Fork the project 

 As we are going to change the code from the project, you must create a private copy of the project to change the code with freedom.
 The process or creating a private copy of the project in git is called fork.

 To fork the project, go to the project home page in GITLab


```bash
http://mackcloud.mackenzie.br/gitlab/digital-internship/asset-rest-api
 ```

In the right upper corner of the page, click in the button **_fork_**. 
- in namespaces select _your namespace_ (your username)
- change the project name to: asset-rest-api-{your RA number}
- select "only the default branch main"
- keep the visibility to private

Click the **Fork project** button.

## Clone the newly forked project to  work on internship

### Create a local workspace in your computer

Go to the your user home directory

```bash
cd ~
```

Create a directory that will be your workspace

```bash
mkdir wks
```

Make this directory your current location

```bash
cd wks
```

It is always possible to check the current directory in use with the command

```bash
pwd
```

To list the contents of the current directory, use the command

```bash
ls
```

### Clone the project repository

To create a local copy of the project repository in your workspace execute the following command. Be aware that you will be requested to provide your credentials (username and your personal access token).

```bash
git clone https://mackcloud.mackenzie.br/gitlab/digital-internship/asset-rest-api-{yourRA}.git
```

If you list the contents of the workspace directory you will notice that a new directory asset-api was created.

### Test cloned project

Navigate to the project directory

```bash
cd asset-rest-api-{yourRA}
```

Check if the file pom.xml is in the directory

```bash
ls
```

Run the following maven commands

Check dependencies

```bash
mvn clean
```

Run the project

```bash
mvn spring-boot:Run
```

This command will build and execute the asset-api application.

To test the application run your browser and open the following URL:

```bash
http://localhost:8080
```


# Opening the project in VSCode

In the root directory of the project execute the command:

```bash
code .
```

This comand will run the VSCode in the windows ui in the wsl directory. Allow the use of the localhost if you are questioned.

In the VSCode interface select "Yes, I trust the authors".

Your project will be opened in the VSCode IDE (Integrated Development Environment)


Now we will implement the [HTTP GET Method](https://mackcloud.mackenzie.br/gitlab/digital-internship/asset-rest-api/-/blob/main/HTTP_GET.md?ref_type=heads).

