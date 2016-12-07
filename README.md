Spring Cloud Config Client OAuth2
---
Spring Cloud Config Client that talks to Config Server with OAuth2 authentication.


Adding Project Lombok Agent
---

This project uses [Project Lombok](http://projectlombok.org/features/index.html)
to generate getters and setters etc. Compiling from the command line this
shouldn't cause any problems, but in an IDE you need to add an agent
to the JVM. Full instructions can be found in the Lombok website. The
sign that you need to do this is a lot of compiler errors to do with
missing methods and fields.  

Usage
---
>This project is available on maven central

Import dependency to your project

```
<dependency>
    <groupId>com.marcosbarbero.cloud</groupId>
    <artifactId>spring-cloud-config-client-oauth2</artifactId>
    <version>1.0.2.RELEASE</version>
</dependency>
```

Configure the following properties on bootstrap.yml

```
spring.cloud.config.client:  
    oauth2:
      client-id: myClientId
      client-secret: myClientSecret
      access-token-uri: http://authserver.domain/oauth/token
      scope: #optional - default values 'read' and 'write'
       - read
       - write
```
