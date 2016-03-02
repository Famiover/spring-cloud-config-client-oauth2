Spring Cloud Config Client OAuth2
---
Spring Cloud Config Client that talks to Config Server with OAuth2 authentication.

Usage
---
Import dependency to your project

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-client-oauth2</artifactId>
    <version>$LATEST_VERSION</version>
</dependency>
```

Configure the following properties on bootstrap.yml

```
spring:
  cloud.config:  
    client.oauth2:
      client-id: myClientId
      client-secret: myClientSecret
      access-token-uri: http://authserver.domain/oauth/token
      scope:
       - read
       - write
```