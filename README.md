# Spring Boot Starter JWT

### Integrate JWT with a Spring Boot application
This module allows a quick configuration of JWT in a Spring Boot application.
Some logic has been based on Spring Boot JWT implementations all over the web.
## Installation
- Clone repository

```
git clone https://github.com/rid3r77/spring-boot-starter-jwt.git
```

- Install repository locally

```
cd spring-boot-starter-jwt
mvn install
```

- Add repository to Maven, Gradle

``` 
mx.com.softdom:spring-boot-starter-jwt:0.0.1-SNAPSHOT
```

- Extend from JwtUser class in your application User model
```
    ...
    @Entity
    public class User implements JwtUser {
        ...
    }
```

- Implement JwtUserRepository interface in your application UserRepository
```
   ...
   @Repository
   public interface UserRepository extends JpaRepository<User, Long>, JwtUserRepository {
        ...
   } 
```

- Add component scanning to class where @SpringBootApplication is used

```
@ComponentScan({
    "your.app.root.package.to.scan",
    "mx.com.softdom.springframework.security.controller",
    "mx.com.softdom.springframework.security.jwt",
    "mx.com.softdom.springframework.security.repository",
    "mx.com.softdom.springframework.security.utils"
})
@EntityScan({
   "mx.com.softdom.springframework.security.model",
   "your.app.model.package.to.scan"
})
```

- Add security JWT properties to your application.properties/yml file.
```
security:
  jwt:
    token:
      base64-secret-key: your-base64-encoded-secret-key # generate with openssl rand -base64 64
      expire-length: 1800 # seconds
      issuer: your-app-name
```

- Configure JWT within a class that extends from WebSecurityConfigurerAdapter, or have a look
and scan the default configuration provided at ``mx.com.softdom.springframework.security.config.SecurityConfiguration``.
Make sure no clashes exist among your application defined beans and the provided sample configuration's. 
```
@ComponentScan({
    "your.app.root.package.to.scan",
    "mx.com.softdom.springframework.security.controller",
    "mx.com.softdom.springframework.security.jwt",
    "mx.com.softdom.springframework.security.repository",
    "mx.com.softdom.springframework.security.utils",
    "mx.com.softdom.springframework.security.config"
})
@EntityScan({
   "mx.com.softdom.springframework.security.model",
   "your.app.model.package.to.scan"
})
```

- JWT Endpoints (When implemented in your own app make sure Authorzation header with Bearer token be included)
```
curl -XPOST -d '{"username":"your_user", "password":"user_password"}' http://<host>:<port>/api/authenticate
curl -XPOST -H 'Authorization: Bearer <JWT_TOKEN>' http://<host>:<port>/api/refreshToken
```

- Playing around (standalone mode - just download repository, comment required section, and run)
  * Comment following section in pom.xml (This will make application.properties and data.sql available)
```
<resources>
	<resource>
		<directory>src/main/resources</directory>
			<excludes>
				<exclude>*.*</exclude>
			</excludes>
			<filtering>false</filtering>
	</resource>
</resources>
```
  * Run application
```
mvn spring-boot:run
```
  * Sample users
```
user01:password
admin01:password
```

## TODO
- Query database when creating authentication.
- Write Unit Tests
- Blacklist used tokens when refreshed

## Feedback
- Any feedback and further improvement are welcome.
