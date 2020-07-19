![logo](./images/logo.jpg)

# WorkStore

## Application Run

```shell script
$ mvn clean package
```
- Add dependencies to projects.
- Build and test, and make it into a jar packaging file.

### Run Admin API

```shell script
$ cd admin-api
$ java -jar admin-api.jar
```

### Run User API

```shell script
$ cd user-api
$ java -jar user-api.jar
```

### User API EndPoints

User Main
> localhost:8081/

User Register Local
> localhost:8081/api/auth/signup

User Login
> localhost:8081/api/auth/login

User Social Login (Google, Kakao)
> localhost:8081/oauth2/authorize/google?redirectUri=[Your_Redirect_Uri]
>
> localhost:8081/oauth2/authorize/kakao?redirectUri_[Your_Redirect_Uri]

User Profile(Get Social, Local Profile)
> GET localhost:8081/api/accounts

User Reserve Product
> POST localhost:8081/api/reservations

User Payment Reserve Product
> POST localhost:8081/api/reservations/{reservationId}/pay

### Admin API EndPoints

Admin Register Local
> localhost:8082/api/auth/signup

Admin Login
> localhost:8082/api/auth/login

Admin Social Login (Google, Kakao)
> localhost:8082/oauth2/authorize/google?redirectUri=[Your_Redirect_Uri]
>
> localhost:8082/oauth2/authorize/kakao?redirectUri_[Your_Redirect_Uri]

Admin Profile(Get Social, Local Profile)
> GET localhost:8082/api/admin/accounts

Admin Local Saving Product Image
> POST localhost:8082/api/admin/products/images

Admin Get Local Saved Image
> GET localhost:8082/api/admin/products/images/{fileName}

Admin Product Register, Find, Update
> POST localhost:8082/api/admin/products
> 
> GET localhost:8082/api/admin/products/{productId}
>
> PUT localhost:8082/api/admin/products/{productId}

## Developing

### Built With

Root Module - Spring Boot Starter, Test, Lombok.

Common Module - Spring Data JPA, H2, Configuration Processor, QueryDsl

User,Admin Module - Spring Boot Mail, thymeleaf, validation, web, oauth2-client, security, restdocs, jjwt, asm, json-smart

### Setting up Development

```shell script
$ git clone https://github.com/Angelhack-WorkStore/workstore-server.git
$ cd workstore-server 
$ mvn clean package
```