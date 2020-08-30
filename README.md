# personalman_java_client
This is a Java Client for PersonalMan which provides absence management for employees or volunteers. Please note that you need a running PersonalMan Server to use this client!

## Using the client

*   First of all, you need a running PersonalMan server - this can be done either locally or remote. Please follow the instructions in the personalman repository (<https://github.com/daveajlee/personalman>).
*   You can run the client by cloning this repository and running the following command: java -jar target/personalman_java_client.jar 
*   This will start the client using localhost as the PersonalMan server on the default port and the english language.
*   To change the language or server url you will need to build the client from source (see below).

## Viewing the source

*   Clone the git branch and import the project in your favourite IDE.
*   The current main class is User Interface.
*   Dependencies are managed through the pom.xml file and Apache Maven.
*   JUnit tests can be run either individually or collectively in the IDE or through maven.

## Limitations of the client

*   If you want to change the company, then you must restart the client.
*   I cannot accept any warranty for loss of data through using this client or server.
