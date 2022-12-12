# [ vofil-backend ]

<img width="298" alt="logo" src="https://user-images.githubusercontent.com/92567571/207050410-4e3cfd25-8884-4e0a-afc6-8fa3a1407afc.png">


## 실행 방법
### 1. 설치 항목
+ Java 11 버전
+ H2 database 1.4.200 버전
+ intelliJ 최신 버전

### 2. 설치 및 환경 설정
주의: 반드시 버전을 통일해야 합니다.   
1. [Java 11](https://jdk.java.net/java-se-ri/11) 이곳에서 Java 11버전을 다운로드 하고, 환경변수 설정을 완료하십시오.
2. clone 받길 원하는 위치에서 터미널을 열고 다음 문구를 입력하십시오.
    ```bash
    git clone git@github.com:Vofil/vofil-backend.git
    ```
3. [H2 database](https://www.h2database.com/html/download-archive.html) 이곳에서 1.4.200 버전을 다운로드 하십시오.
4. H2 database를 실행시키고, db명 **"test"** 데이터베이스를 생성하십시오.  
+ db 이름을 바꾸고 싶은 경우, **vofil-backend\src\main\resources\application.propertie** 에서 **spring.datasource.url=jdbc:h2:tcp://localhost/~/[DB명]** 을 변경해야 합니다.
5. **vofil-backend\sql** 경로에 있는 **Picture.sql, user.sql, Vote.sql, Voter.sql** 파일의 코드들을 각각 복사한 후 H2 database에서 실행해 table을 생성하십시오.
6. [IntelliJ IDEA](https://www.jetbrains.com/idea/) 이곳에서 최신 버전 인텔리제이를 다운로드 하십시오.

### 3. 실행
1. H2 database를 실행시키십시오.
2. IntelliJ를 반드시 관리자 권한으로 실행한 후, VofilBackendApplication을 실행시키십시오.
3. 서버는 실행이 되었습니다. api 관련 테스트를 해 보고 싶은 경우 **localhost:8089/api** 에 요청을 보내면 됩니다.
4. 프론트엔드의 코드를 클론 및 실행시키십시오. 이에 대한 안내는 [vofil-frontend](https://github.com/Vofil/vofil-frontend#readme) 에서 확인이 가능합니다.

## 백엔드 사용 기술
Project: Gradle Project   
Spring Boot: 2.7.4   
Language: Java   
Packaging: Jar   
Java: 11   



