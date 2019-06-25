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
때문에&nbsp;<span style="color:#000000">JSON 데이터 구조를 처리해주는&nbsp;<strong>Jackson</strong> 라이브러리를 이용하였다.</span></p>
<hr>
<p><strong>- 입력값 처리</strong><br>
<span style="color:#333333">JSON 입력 값을 직접 파싱을 하고 인터페이스를 만드는 등을 작업을 거치지 안고</span><br>
Jackson 라이브러리가 제공하는 ObjecMapper 를 이용하여<br>
VO 객체로 변환 시켜주었다.</p>
<pre><code data-language="java" class="lang-java">ObjectMapper mapper = new ObjectMapper();
API1ReqVO api1RepVO = mapper.readValue(reqBody, API1ReqVO.class);
</code></pre>
<p><strong>- 리턴값 처리</strong><br>
VO 객체의 데이터를 JSON 형태로 자동으로 변환 시켜 주는&nbsp;<strong>ResponseEntity</strong> 이용하여 리턴 시켜주었다.</p>
<pre><code>List&lt;API1ResultVO&gt; resutVO = apiMapper.getAPI1(api1RepVO);
return new ResponseEntity&lt;List&lt;API1ResultVO&gt;&gt;(resutVO,HttpStatus.OK);
</code></pre>
<h3>Test를 위한 데이터 입력</h3>
<p>해당 프로젝트 과제는 배포 후 쉽게 테스트 할 수 있었야 했다.<br>
때문에&nbsp;@PostConstruct 와 opencsv 를 이용하여 csv 파일을 h2 DB에 입력하였다.</p>
<p><br data-tomark-pass=""></p>
<pre><code data-language="java" class="lang-java">@PostConstruct
private void readCsv() {
try {
this.ReadCsv("AccInfo");
this.ReadCsv("BranchInfo");
this.ReadCsv("TranHis");
} catch (Exception e) {
logger.debug("error",e);
}
}
설정
&lt;dependency&gt;
</code></pre>
<p>&lt;groupId&gt;com.opencsv&lt;/groupId&gt;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;artifactId&gt;opencsv&lt;/artifactId&gt;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;version&gt;3.9&lt;/version&gt;<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;/dependency&gt;</p>
<p>ClassPathResource 를 이용하여&nbsp;Resource에 있는 csv 파일을 생성하여<br>
CSVReader 를 이용하여 파일 내용을 읽었다.</p>
<p><br data-tomark-pass=""></p>
<pre><code>ClassPathResource&nbsp; file = new ClassPathResource("csv/"+fileName+".csv");
CSVReader reader = new CSVReader(new FileReader(file.getFile()));&nbsp;
</code></pre>
<p><br data-tomark-pass=""><br>
<br data-tomark-pass=""><br>
<br data-tomark-pass=""><br>
<br data-tomark-pass=""><br>
<br data-tomark-pass=""><br>
<br data-tomark-pass=""><br>
<br data-tomark-pass=""><br>
<br data-tomark-pass=""></p>
<h3>문제 1 문제 해결 해설</h3>
<p>문제 1의</p>

