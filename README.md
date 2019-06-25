## 1. 프로젝트 기초 정보

* **IDE Tool** : STS4 ( Spring Tools Suite 4 for Eclipse )

* **개발언어** : Java-1.8.0

* **프레임웍** : Spring boot(Maven)

*  **DataBase** : h2

*  **HTTP 통신** : Get 방식 ( 입/출력 모두 Json )

  

## 2. 개발 환경 설정 방법

1. ##### **JDK 다운로드 및 환경 변수 설정**

   * JDK를 다운 받아 원하는 경로에서 압축을 해제한다.    ▶[**다운로드**](https://github.com/ojdkbuild/ojdkbuild/releases/download/1.8.0.191-1/java-1.8.0-openjdk-1.8.0.191-1.b12.ojdkbuild.windows.x86_64.zip)


   * **제어판 ▶ 시스템 및 보안 ▶ 시스템** 에서 **고급 시스템 설정**을 클릭한다.


     

   * **고급텝**의 **환경 변수**를 클릭한다.


     

   * **새로 만들기**를 클릭하여 JAVA_HOME 을 추가한다.

     >  **변수 이름** : JAVA_HOME
     >
     >  **변수 값**     : JDK 경로


   * 추가한 JAVA_HOME 을 Path 에 추가 하여 수정한다.

     > **변수 이름** : Path
     >
     > **변수 값**     : %JAVA_HOME%\bin;




2. ##### **IDE Tool STS4 다운로드 (Spring Tools Suie 4 for Eclipse)**

   * STS4  다운로드를 위해 Spring Tools로 이동( https://spring.io/tools )하여 

     환경에 맞는 STS 를 다운로드 합니다.

     다운로드 후 원하는 경로에 압축을 해제 합니다.

     [**[▶ 다운로드 ]**](https://spring.io/tools)

     Ex >  Download STS4 Windows 64-bit ( Spring Tools Suite 4 for Eclipse )


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

  ​      

## 4. 프로젝트 설정 내용

## 5. 문제 해결 방법

### 공통 사항

#### 1. 입/출력 JSON 처리

4개의 모든 API의 입/출력은 JSON으로 이루어져야 한다.

때문에 JSON 데이터 구조를 처리해주는 **`Jackson` 라이브러리**를 이용하였다.

   

##### **<u>입력값 처리</u>**

JSON 입력 값을 직접 파싱을 하고 인터페이스를 만드는 등을 작업을 거치지 않고

*Jackson* 라이브러리가 제공하는 **`ObjectMapper`** 를 이용하여 **`VO`** 객체로 변환 시켜 주었다.

##### **<u>리턴값  처리</u>**

**`VO`** 객체의 데이터를 JSON 형태로 자동으로 변환 시켜 주는 **`ResponseEntity`** 이용하여 리턴 시켜주었다.



```java
 @GetMapping("/API1")
 public ResponseEntity<List<API1ResultVO>> getAPI1(@RequestBody String reqBody)
 	throws JsonParseException, JsonMappingException, IOException {
 		
     ObjectMapper mapper = new ObjectMapper();
     API1ReqVO api1RepVO = mapper.readValue(reqBody, API1ReqVO.class);
 
     List<API1ResultVO> resutVO = apiMapper.getAPI1(api1RepVO);
     return new ResponseEntity<List<API1ResultVO>>(resutVO,HttpStatus.OK);
 }
```



#### **2. Test를 위한 데이터 입력**

해당 과제는 배포 후 쉽게 테스트 할 수 있었야 했다.

때문에 **`@PostConstruct`** 와**opencsv** 를 이용하여 WAS가 띄워질 때 csv 파일 내용을 **<u>h2** DB에 입력</u>하였다.



**pom.xml**

```xml
    <dependency>
        <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>3.9</version>
    </dependency>
```



**@PostConstruct**            * 참고 : CsvFileRead.java

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





------

### <문제 1>

<문제 1>의 2018년, 2019년의 합계 금액이 가장 많은 고객을 추출하기 위해서 아래와 같은 조건이 필요하다.

#####    **[ 조건 ]**

1. **입력 값을 추출 조건으로 사용하지 않는다.**

   ‘입력 값은 아래와 같은 값을 사용.’ 이라 명시 되어 있어

   출력값과 동일하게 year, name, acctNo, sumAmt 값들을 입력 받아야한다.

   하지만 연도별 합계 금액이 많은 고객을 추출하는 것에

   <u>조건이 들어가면 특정 대상이 추출되므로 입력 값을 이용하여 추출 조건으로 사용하지 않는다.</u>

   

2. **'계좌 정보'와 '거래 내역'' 정보를 이용한다.**

   거래 내역 정보에 담겨있는 '연도'와 '금액' 정보를 이용해야하며,

   '계좌명'이 출력되어야 하기 때문이다.

   두 정보간의 연결고리는 '계좌 번호' 이다.

   

3. **‘거래 내역’ 정보의 ‘취소여부’가 ‘N’인 대상을 조회 해야한다.**

4. **거래 금액에서 수수료 금액을 뺀 금액의 합계를 구해야한다.**

5. 연도별 조회 이므로 **거래일자에서 '연도'를 추출 또는 변환** 시켜 사용해야 한다.

   h2 DB가 제공하는 **`ISO_YEAR()`** 함수를 이용하여 변환 시켜주었다.

6. 연도별 고객의 합계를 구해야 하므로 **‘연도’과 ‘계좌번호’ 로  GROUP BY 를 이용하여 그룹핑** 해야한다.

   

```
SELECT ISO_YEAR(B.거래일자), A.계좌 번호, A.계좌명 , SUM(B.거래금액 – B.수수료 금액)
FROM 거래정보 A
    ,계좌정보 B
WHERE A.계좌 번호 = B.계좌번호
  AND 취소여부 = ‘N’
GROUP BY ISO_YEAR(B.거래일자), A.계좌 번호 , A.계좌명
```

위 와 같은 쿼리를 실행 했을 때, 연도별 계좌별 합계금액이 조회 된다.

여기서 ‘연도’별 가장 큰 금액의 고객을 찾아야한다.

 

> Oracle DB 같은 경우

RANK() OVER (PARTITION BY ‘년도’ ORDER BY ‘계좌별 합계 금액’ DESC ) 하여

‘1’ 인 것들만 조회하면 년도별 합계 금액이 가장 큰 고객을 추출할 수 있다.

 

> h2 DB 같은 경우 

RANK() OVER (PARTITION BY ‘ ’ ORDER BY ‘ ’) 함수를 <u>지원하지 않기 때문에</u>

위에서 만든 동일 한 정보 2개를 만들어 동일 연도의 금액들을 <u>비교하여 순위를 구해야 한다.</u>

```
SELECT COUNT(*) AS 순위
  FROM ( 년도, 계좌별 조회 정보 ) A
      ,( 년도, 계좌별 조회 정보 ) B
WHERE A.합계금액 <= B.합계금액					-- 금액 비교
  AND A.년도 = B.년도
GROUP BY A.계좌 번호, A.년도 
```



이 후 순위가 1인 것들만 조회하면 년도별, 합계금액이 가장 많은 고객을 추출할 수 있다. 

```
SELECT year, name, acctNo, sumAmt			-- 출력 값
  FROM (
        SELECT COUNT(*) AS 순위
               ,A.year
               ,A.name
               ,A.acctNo
               ,MAX(합계 금액) As sumAmt               
         FROM ( 년도, 계좌별 조회 정보) A
             ,( 년도, 계좌별 조회 정보) B
        WHERE A.합계금액 <= B.합계금액
          AND A.년도 = B.년도
        GROUP BY A.계좌 번호, A.년도
) WHERE 순위 = ‘1’;							-- 순위 1인 것 조회
```

------

### <문제 2>
