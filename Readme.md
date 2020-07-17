## WorkStore Server

### Project Run

- 루트 디렉터리인 workstore-server에서 mvn clean package를 시도한다.
- 패키징이 완료되면, cd user-api를 시도한다.
- user-api 디렉토리에서 다시 cd target을 한다.
- java -jar user-api.jar를 실행한다.

### User Api

User Api Uri: http://localhost:8081

### User 로컬 회원가입

- 회원가입 Uri: http://localhost:8081/api/auth/signup
  - 스텝 1: 회원가입 시 서버에서 입력 값을 검증 후, 맞다면 DB에 저장.
  - 스텝 2: DB에 회원 정보를 저장 후 http://localhost:3000/login으로 리다이렉트 보냄.
- 이메일 인증 Uri: http://localhost:8081/api/auth/check-email-token
  - 스텝 1: 이메일 인증 메일 확인 후 인증하기 버튼 누르면 서버에서 인증 절차 수행.
  - 스텝 2: 인증이 완료되면 http://localhost:3000/login으로 리다이렉트 보냄.
- 로그인 Uri: http://localhost:8081/api/auth/login
  - 아이디, 비밀번호 입력 후 로그인
  - 로그인 절차 진행 후 엑세스 토큰을 발급하여 프론트로 전달.
  
### User 소셜 로그인, 회원가입

- 소셜 로그인,회원가입 Uri: http://localhost:8081/oauth2/authorize
- 카카오, 구글 중 선택하여 로그인을 시도
- 로그인이 완료되면,
  - 구글의 경우 "http://localhost:8081/login/oauth2/code/google?role=user" 리다이렉트
  - 카카오의 경우 "http://localhost:8081/login/oauth2/code/kakao?role=user" 리다이렉트
- 리다이렉트 한 URI로 서버에서 인증 후 구글과 카카오의 사용자 정보와 엑세스 토큰을 발급받음.
- 발급받은 토큰과 소셜 회원 정보를 DB에 저장.
- 인증이 완료됨과 동시에 프론트에서 지정한 redirect_Uri에 토큰을 Query Parameter에 담아 보냄.

### Admin Api

Admin Api Uri: http://localhost:8082

### Admin 로컬 회원가입

- 회원가입 Uri: http://localhost:8082/api/auth/signup
  - 스텝 1: 회원가입 시 서버에서 입력 값을 검증 후, 맞다면 DB에 저장.
  - 스텝 2: DB에 회원 정보를 저장 후 http://localhost:3000/login으로 리다이렉트 보냄.
- 이메일 인증 Uri: http://localhost:8082/api/auth/check-email-token
  - 스텝 1: 이메일 인증 메일 확인 후 인증하기 버튼 누르면 서버에서 인증 절차 수행.
  - 스텝 2: 인증이 완료되면 http://localhost:3000/login으로 리다이렉트 보냄.
- 로그인 Uri: http://localhost:8082/api/auth/login
  - 아이디, 비밀번호 입력 후 로그인
  - 로그인 절차 진행 후 엑세스 토큰을 발급하여 프론트로 전달.
  
### Admin 소셜 로그인, 회원가입

- 소셜 로그인,회원가입 Uri: http://localhost:8082/oauth2/authorize
- 카카오, 구글 중 선택하여 로그인을 시도
- 로그인이 완료되면,
  - 구글의 경우 "http://localhost:8082/login/oauth2/code/google?role=admin" 리다이렉트
  - 카카오의 경우 "http://localhost:8082/login/oauth2/code/kakao?role=admin" 리다이렉트
- 리다이렉트 한 URI로 서버에서 인증 후 구글과 카카오의 사용자 정보와 엑세스 토큰을 발급받음.
- 발급받은 토큰과 소셜 회원 정보를 DB에 저장.
- 인증이 완료됨과 동시에 프론트에서 지정한 redirect_Uri에 토큰을 Query Parameter에 담아 보냄.