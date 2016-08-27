Spring Cloud Config Client OAuth2
---
Spring Cloud Config Client that talks to Config Server with OAuth2 authentication.

Usage
---
>This project is available on maven central
Import dependency to your project

```
<dependency>
    <groupId>com.marcosbarbero.cloud</groupId>
    <artifactId>spring-cloud-config-client-oauth2</artifactId>
    <version>1.0.1.RELEASE</version>
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
