# REST-API

This project is the starting point to the implementation of a RESTful API (Application Programing Interface) in Java using the Spring Boot framework.

In this README file we will set the development environment and test it.

At first a Debian Linux environment must be provided. For linux users this is not a challenging taks, but for Windows 11 users, this can be a whole new world to be explored. Therefore, we provide a tutorial to setup a Debian Linux environment in Windows 11, using the Windows Subsystem for Linux (WSL).

After setting up a Debian Linux for use, the Java development tools must be installed. The second tutorial explains how to set the Java Development Kit and the Maven dependency management tool.

With all the tools set for Java compilation and execution, a third tutorial explains how to setup a Integrated Development Environment to develop in Java.

Finally it is explained how this project source code and resources may be copied to your computer (cloned), compiled and executed.

## Prerequisite

- You must have credentials to access this GitLAB repository (if you are reading this README file probably you already have accomplished with this prerequisite).
- You must have configured your token to access this repository from your linux box using the command line. If you do not have your personal access token, see [this tutorial](https://mackcloud.mackenzie.br/gitlab/learning/mackleaps/tutorials/-/blob/main/GitLAB-Personal_Token.md).



## Getting started

To make it easy for you to get started with Java development here's a list of recommended next steps.
The provided directions are suposing that you are developing in a Debian Linux environment, with Java Development Kit installed and configured using Maven dependency management.

### Setup Java development environment (Debian)

- [Setup Debian environment in Windows 11 using WSL](https://mackcloud.mackenzie.br/gitlab/tutorial/tutorials/-/blob/main/UsingDebianLinuxOnWindows.md?ref_type=heads)
- [Setup Java development environment in Debian Linux](https://mackcloud.mackenzie.br/gitlab/tutorial/tutorials/-/blob/main/SettingUpDebianBoxForJavaDevelopment.md?ref_type=heads)

### Setup Java development environment (Windows)
- [Setup of a Portable version of the GIT Client](https://mackcloud.mackenzie.br/gitlab/learning/mackleaps/tutorials/-/blob/main/Windows-PortableGitClient.md)


### Setup Integrated Development Environment for Java

**TODO**


## Cloning the project repository

This project repository in GitLAB contains source code files and other resources from the project Asset REST API. In order to compile and run this project you need to make a copy of all this resources to your computer. When using git, this copy is named "clone". We clone the repository to your local machine.

### Create a local workspace in your computer

Go to the your user home directory

```bash
cd ~
```

Create a directory that will be your workspace

```bash
mkdir workspace
```

Make this directory your current location

```bash
cd workspace
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
git clone https://mackcloud.mackenzie.br/gitlab/learning/mackleaps/rest-api.git
```

If you list the contents of the workspace directory you will notice that a new directory rest-api was created.

### Test cloned project

Navigate to the project directory

```bash
cd rest-api
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

This command will build and execute the rest-api application.

To test the application run your browser and open the following URL:

```bash
http://localhost:8080
```

For now a HTTP 404 error message will be given, as we have not implemented any REST method.
