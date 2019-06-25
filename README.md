# 1.프로젝트 정보
#####  ● IDE 툴  : STS4 ( Spring Tools Suite 4 for Eclipse )
#####  ● 개발언어 : Java-1.8.0
#####  ● 프레임웍 : Spring boot(Maven)
#####  ● DataBase : h2
#####  ● HTTP 통신 : Get 방식 ( 입/출력 모두 Json )

------------------- 



# 2.개발 환경 설정 방법
### ** * jdk 다운로드 및 환경 변수 설정**
 1. openjdk를 다운로드 합니다. ▶ **[다운로드](https://github.com/ojdkbuild/ojdkbuild/releases/download/1.8.0.191-1/java-1.8.0-openjdk-1.8.0.191-1.b12.ojdkbuild.windows.x86_64.zip)**
 
 2. C:\ 경로에 zip 파일을 해제합니다.
 
 3. **'제어판 > 모든 제어판 항목 >시스템 > 고급 시스템 설정'** 클릭 합니다.
 
 4. **'고급텝 > 환경 변수'**를 클릭합니다.
 
 5. 시스템변수 항목의 **'새로 만들기'** 클릭하여
 
    **변수 이름** : JAVA_HOME
    
    **변수   값** : jdk 압축해제한 파일 경로 ( ex > C:\java-1.8.0 )
    
 6. 시스템 변수 중 **'Path'**를 더블 클릭하여 아래 내용을 **추가하여 수정**합니다.
 
    **변수 명 **: Path
    
    **추가 변수 값** : %JAVA_HOME%\bin;
 
 
 
 
###  ** * IDE 다운로드 (Spring Tools Suite 4 for Eclipse )**
 1. STS4 
   다운로드를 위해 Spring Tools로 이동( https://spring.io/tools )하여 환경에 맞는 STS 를 다운로드합니다.
   
    ▶ **[스프링부트 사이트 이동](https://spring.io/tools)**
    * Ex> Download STS4 Windows 64-bit ( Spring Tools Suite 4 for Eclipse )

2. 원하는 위치에 zip 파일을 해제합니다.


---------------------------------------
# 3.소스 Import 및 빌드 방법
---------------------------------------
###  * 소스 Import
1. Spring boot 를 실행합니다.
2. 마우스 우클릭하여 Import > Maven > Check out Maven Projects form SCM 선택합니다.
3. SCM URL 의 'git' 항목에 https://github.com/suheecoding/api4pj.git 를 입력합니다.
4. Finish 버튼을 클릭하여 Import를 완료합니다.

###  * 빌드 및 JUnit Test
1. 아래에 적힌 경로의 파일을 우클릭합니다.

   경로 : /api4pj/src/test/java/com/kakaopay/api4pj/Api4pjApplicationTests.java
   
2. 마우스 우클릭 > Run As > JUnit Test 를 클릭하여 실행합니다.


-------------------
# 3.문제 해결 
-------------------
### * 공통 사항

<h3><strong>JSON 통신</strong></h3>
<p>4개의 모든 API의 입/출력은 JSON으로 이루어져야한다.<br>
때문에&nbsp;<span style="color:  #000000;;">JSON 데이터 구조를 처리해주는&nbsp;<strong>Jackson</strong> 라이브러리를 이용하였다.</span></p>
<p><span style="color:  #333333;;">JSON 입력 값을 직접 파싱을 하고 인터페이스를 만드는 등을 작업을 거치지 안고</span><br>
Jackson 라이브러리가 제공하는 ObjecMapper 를 이용하여<br>
VO 객체로 변환 시켜주었다.</p>
<pre><code data-language="java" class="lang-java">ObjectMapper mapper = new ObjectMapper();
API1ReqVO api1RepVO = mapper.readValue(reqBody, API1ReqVO.class);
</code></pre>
