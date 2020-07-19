![logo](./images/logo.jpg)

# WorkStore

미운영 시간에 공간을 공유하고 추가 수익과 가게 홍보 효과를 누리세요. O2O 서비스

## 소상공인

> 1.점포 유휴 자산을 활용하여 부가수익 창출
>
> 2.간편한 공간 등록 절차 
>
> 3.무인 서비스로 인건비 부담 없음 
>
> 4.제휴 업체로 노출되어 홍보 효과 
>
> 5.운영 홍보비 절감 (예약 성사에 대한 수수료 부과)

## 프리랜서, 스타트업
> 1.점포 미운영시간 일, 월 단위 예약
>
> 2.집 근처 가까운 점포 이용
>
> 3.언택트 소비 - 미 운영시간 QR코드로 출입 관리
>
> 4.다양한 점포 외식 할인 혜택

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
