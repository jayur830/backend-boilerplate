# KESTI Backend Boilerplate
### Description
{} 안에 있는 내용 수정 필요
```yaml
spring:
  ...
  application:
    name: {application-name}
  ...
  datasource:
    hikari:
      ...
      pool-name: {pool-name}
      schema: {database-schema}
  ...
  mail:
    host: smtp.{smtp-name}.com
    port: 587
    username: {username}@{smtp-name}.com
    password: {password}
    ...
...
---
server:
  port: {local-port}
spring:
  profiles: local
  datasource:
    [현재 쓰는 RDBMS에 맞는 driver-class-name, url 선택]
    username: {username}
    password: {password}
  jpa:
    properties:
      hibernate:
        ...
        [Dialect 선택]
...
cors:
  allowed-domains: http://{host}:{port}
---
[위 local profiles와 마찬가지로 설정 필요]
server:
  port: {dev-port}
spring:
  profiles: dev
  ...
...
```