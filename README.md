1.개발 환경
---------------
##### ● 프레임웍 : Spring boot(Maven)
##### ● DataBase : h2
##### ● HTTP 통신 : Get 방식 ( 입/출력 모두 Json )




2.개발 환경 설정 방법
-------------------
1. 스프링부트(Spring Boot)를 다운로드합니다.

   다운로드를 위해 Spring Tools로 이동( https://spring.io/tools )하여 환경에 맞는 STS를 다운로드합니다.
   
   (ex Download STS4 Windows 64-bit )

2. 원하는 위치에 zip 파일을 해제합니다.

3.소스 Import 및 빌드 방법
---------------------------------------
### * 소스 Import
1. Spring boot 를 실행합니다.
2. 마우스 우클릭하여 Import > Maven > Check out Maven Projects form SCM 선택합니다.
3. SCM URL 의 'git' 항목에 https://github.com/suheecoding/api4pj.git 를 입력합니다.
4. Finish 버튼을 클릭하여 Import를 완료합니다.

### * 빌드 및 JUnit Test
1. 아래에 적힌 경로의 파일을 우클릭합니다.

   경로 : /api4pj/src/test/java/com/kakaopay/api4pj/Api4pjApplicationTests.java
   
2. 마우스 우클릭 > Run As > JUnit Test 를 클릭하여 실행합니다.


3.문제 해결 
-------------------
