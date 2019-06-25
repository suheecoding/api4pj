## 1. 프로젝트 기초 정보

* **IDE Tool** : STS4 ( Spring Tools Suite 4 for Eclipse )

* **개발언어** : Java-1.8.0

* **프레임웍** : Spring boot(Maven)

*  **DataBase** : h2

*  **HTTP 통신** : Get 방식 ( 입/출력 모두 Json )

  

## 2. 개발 환경 설정 방법

1. ##### **JDK 다운로드 및 환경 변수 설정**

   * JDK를 다운 받아 원하는 경로에서 압축을 해제한다.    ▶[**다운로드**](https://github.com/ojdkbuild/ojdkbuild/releases/download/1.8.0.191-1/java-1.8.0-openjdk-1.8.0.191-1.b12.ojdkbuild.windows.x86_64.zip)

     ![1561472723416](C:\Users\정수희\AppData\Roaming\Typora\typora-user-images\1561472723416.png)

   * **제어판 ▶ 시스템 및 보안 ▶ 시스템** 에서 **고급 시스템 설정**을 클릭한다.

     ![1561472940970](C:\Users\정수희\AppData\Roaming\Typora\typora-user-images\1561472940970.png)

     

   * **고급텝**의 **환경 변수**를 클릭한다.

     ![1561473063397](C:\Users\정수희\AppData\Roaming\Typora\typora-user-images\1561473063397.png)

     

   * **새로 만들기**를 클릭하여 JAVA_HOME 을 추가한다.

     >  **변수 이름** : JAVA_HOME
     >
     >  **변수 값**     : JDK 경로

     ![1561473217977](C:\Users\정수희\AppData\Roaming\Typora\typora-user-images\1561473217977.png)

   * 추가한 JAVA_HOME 을 Path 에 추가 하여 수정한다.

     > **변수 이름** : Path
     >
     > **변수 값**     : %JAVA_HOME%\bin;

     ![1561473357376](C:\Users\정수희\AppData\Roaming\Typora\typora-user-images\1561473357376.png)



2. ##### **IDE Tool STS4 다운로드 (Spring Tools Suie 4 for Eclipse)**

   * STS4  다운로드를 위해 Spring Tools로 이동( https://spring.io/tools )하여 

     환경에 맞는 STS 를 다운로드 합니다.

     다운로드 후 원하는 경로에 압축을 해제 합니다.

     [**[▶ 다운로드 ]**](https://spring.io/tools)

     Ex >  Download STS4 Windows 64-bit ( Spring Tools Suite 4 for Eclipse )

     ![1561474293394](C:\Users\정수희\AppData\Roaming\Typora\typora-user-images\1561474293394.png)

## 3. 소스 Import 및 실행 방법

#### **1. 소스 Import**

 * Spring boot 를 실행합니다.
 * 마우스 우클릭하여 **Import > Maven > Check out Maven Projects form SCM** 선택합니다.
 * **SCM URL** 의 'git' 항목에 **https://github.com/suheecoding/api4pj.git** 를 입력합니다.
 * **Finish** 버튼을 클릭하여 Import를 완료합니다.



#### **2. 빌드 및 JUnit Test**

- 아래에 적힌 경로의 파일을 우클릭합니다.

  **경로** : /api4pj/src/test/java/com/kakaopay/api4pj/Api4pjApplicationTests.java

* **마우스 우클릭 > Run As > JUnit Test** 를 클릭하여 실행합니다.

        

## 4. 프로젝트 설정 내용

## 5. 문제 해결 방법



#### 공통 사항

------

#### 1. 입/출력 JSON 처리

4개의 모든 API의 입/출력은 JSON으로 이루어져야 한다.

때문에 JSON 데이터 구조를 처리해주는 **`Jackson` 라이브러리**를 이용하였다.

   

##### **<u>입력값 처리</u>**

JSON 입력 값을 직접 파싱을 하고 인터페이스를 만드는 등을 작업을 거치지 않고

*Jackson* 라이브러리가 제공하는 **`ObjectMapper`** 를 이용하여 **`VO`**객체로 변환 시켜 주었다.

##### **<u>리턴값  처리</u>**

**`VO`** 객체의 데이터를 JSON 형태로 자동으로 변환 시켜 주는 **`ResponseEntity`** 이용하여 리턴 시켜주었다.



```
 @GetMapping("/API1")
 public ResponseEntity<List<API1ResultVO>> getAPI1(@RequestBody String reqBody)
 	throws JsonParseException, JsonMappingException, IOException {
 		
     ObjectMapper mapper = new ObjectMapper();
     API1ReqVO api1RepVO = mapper.readValue(reqBody, API1ReqVO.class);
 
     List<API1ResultVO> resutVO = apiMapper.getAPI1(api1RepVO);
     return new ResponseEntity<List<API1ResultVO>>(resutVO,HttpStatus.OK);
 }
 ```



------

#### **2. Test를 위한 데이터 입력**

해당 과제는 배포 후 쉽게 테스트 할 수 있었야 했다.

때문에 `@PostConstruct` 와**opencsv** 를 이용하여 WAS가 띄워질 때 csv 파일 내용을 **h2 DB에 입력**하였다.



**pom.xml**

```xml
    <dependency>
        <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>3.9</version>
    </dependency>
```



**@PostConstruct**

```java
	@PostConstruct
	private void readCsv() {
		try {
			this.ReadCsv("AccInfo"); 
			this.ReadCsv("BranchInfo");
			this.ReadCsv("TranHis");
		} catch (Exception e) {
			logger.debug("error",e);
		}
	}
```
