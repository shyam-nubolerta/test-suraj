# springboot-sample-app


Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Generating encrypted API Keys

We can generate the encrypted keys using jasypt using following command

```shell
./gradlew encryptText --text=someText --password=encryptorToken
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `org.filereader.FileReaderApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
./gradlew bootRun  --args='--jasypt.encryptor.password=password'
```

## Monitoring of the application locally

- [Actuator](http://localhost:8080/actuator)

