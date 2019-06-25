## 1. 프로젝트 기초 정보

* **IDE Tool** : STS4 ( Spring Tools Suite 4 for Eclipse )

* **개발언어** : Java-1.8.0

* **프레임웍** : Spring boot(Maven)

*  **DataBase** : h2

*  **HTTP 통신** : Get 방식 ( 입/출력 모두 Json )

  

## 2. 개발 환경 설정 방법

1. ##### **JDK 다운로드 및 환경 변수 설정**

   * JDK를 다운 받아 원하는 경로에서 압축을 해제한다.    ▶[**다운로드**](https://github.com/ojdkbuild/ojdkbuild/releases/download/1.8.0.191-1/java-1.8.0-openjdk-1.8.0.191-1.b12.ojdkbuild.windows.x86_64.zip)
   
   * 제어판 ▶ 시스템 및 보안 ▶ 시스템** 에서 **고급 시스템 설정**을 클릭한다.
   
   * **고급텝**의 **환경 변수**를 클릭한다.
   
   * **새로 만들기**를 클릭하여 JAVA_HOME 을 추가한다.
   
     > **변수 이름** : JAVA_HOME
     >
     > **변수 값**     : JDK 경로
     >
   
   
   
   * 추가한 JAVA_HOME 을 Path 에 추가 하여 수정한다.
   
     > **변수 이름** : Path
     >
     > **변수 값**     : %JAVA_HOME%\bin;




2. ##### **IDE Tool STS4 다운로드 (Spring Tools Suie 4 for Eclipse)**

   * STS4  다운로드를 위해 Spring Tools로 이동( https://spring.io/tools )하여 

     환경에 맞는 STS 를 다운로드 합니다.

     다운로드 후 원하는 경로에 압축을 해제 합니다.  ▶[**[ 다운로드 ]**](https://spring.io/tools)

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
      

## 4. 프로젝트 설정 내용

## 5. 문제 해결 방법

### 1. 공통 사항

* #### 입/출력 JSON 처리

  4개의 모든 API의 입/출력은 JSON으로 이루어져야 한다.

  때문에 JSON 데이터 구조를 처리해주는 **`Jackson` 라이브러리**를 이용하였다.

  

  **<u>입력값 처리</u>**

  JSON 입력 값을 직접 파싱을 하고 인터페이스를 만드는 등을 작업을 거치지 않고

  *Jackson* 라이브러리가 제공하는 **`ObjectMapper`** 를 이용하여 **`VO`** 객체로 변환 시켜 주었다.

  

  **<u>리턴값  처리</u>**

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



* #### **Test를 위한 csv 데이터 입력**

  해당 과제는 배포 후 쉽게 테스트 할 수 있었야 했다.

  테스트 담당자가 DB를 생성하고, 데이터를 Insert 하는 수고로움을 줄이기 위하여

  **`@PostConstruct`** 와**opencsv** 를 이용하여 WAS가 띄워질 때

   csv 파일 내용 Read 하여 <u>**h2 DB에 입력**</u>하였다.



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
			this.ReadCsv("AccInfo");		-- 데이터_계좌정보.csv
			this.ReadCsv("BranchInfo");		-- 데이터_관리점정보.csv
			this.ReadCsv("TranHis");		-- 데이터_거래내역.csv
		} catch (Exception e) {
			logger.debug("error",e);
		}
	}
```





------

### 2. <문제 1>의 해설

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

6. 연도별 고객의 합계를 구해야 하므로 **‘연도’과 ‘계좌번호’ 로  `GROUP BY` 를 이용하여 그룹핑** 해야한다.

   

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

**`RANK() OVER (PARTITION BY ‘년도’ ORDER BY ‘계좌별 합계 금액’ DESC )`** 하여

RANK가 ‘1’ 인 것들만 조회하면 년도별 합계 금액이 가장 큰 고객을 추출할 수 있다.

 

> h2 DB 같은 경우 

`RANK() OVER (PARTITION BY ‘ ’ ORDER BY ‘ ’)` 함수를 <u>지원하지 않기 때문에</u>

위에서 만든 동일 한 정보 2개를 만들어 동일 연도의 금액들을 <u>비교하여 순위를 구해야 한다.</u>

```
SELECT COUNT(*) AS 순위                      -- 순위
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

### 3. <문제 2>의 해성
<문제 2>의 2018년 또는 2019년 거래가 없는 고객을 추출하기 위해서 아래와 같은 조건이 필요하다.

#####    **[ 조건 ]**

1. **입력 값을 추출 조건으로 year 을 사용하고, 출력 값으로도 사용 한다.**

   ‘입력 값은 아래와 같은 값을 사용.’ 이라 명시 되어 있어

   출력값과 동일하게 year, name, accTno 값들이 입력 받아야한다.

   추출 조건 중 ‘연도’ 라는 조건이 들어갔기 때문에 입력값 중 ‘year’을 추출 조건으로 이용한다.

   

2. **계좌 정보와 거래 내역을 이용해야한다.**

   거래 정보, 연도 등 정보는 거래 내역에 있고, 출력되어야할 계좌명이 계좌 정보에 있기 때문이다.

   

3. 거래가 없는 고객이라는 것은 아래와 같다.

   > * 2018년, 2019년 모두 취소여부가 ‘N’ 인 거래 내용이 없는 경우.
   > * 2018년 또는 2019년 취소여부가 ‘N’ 인 거래 내용이 없는 경우.

   
   
   거래가 없는 고객을 추출해야하므로 **`AND NOT EXISTS(서브쿼리)`**를 이용한다.
   
   **`NOT EXISTS()`** 는 <u>‘서브쿼리’ 내용이 존재 하지 않은 경우</u>를 조건으로 실행되기 때문이다.
   
   

```
SELECT {입력값 year} AS year
       , A.계좌 명   AS name
       , A.계좌 번호 AS acctNo
  FROM  계좌 정보 A
   WHERE NOT EXIST(
                       SELECT 1
                         FROM 거래 내역 B
                        WHERE A.계좌 번호 = B.계좌 번호
                          AND 취소여부 = ‘N’
                          AND ISO_YEAR(B.거래 일자) = {입력값 year}
                     )
```
------

### 4. <문제 3> 의 해설
<문제 3>의 연도별, 관리점별 거래금액 합계를 큰 순서대로 출력하기 위해서 아래와 같은 조건이 필요하다.

#####    **[ 조건 ]**

1. **입력값이 없다.**

   <문제 1>, <문제 2> 처럼 ‘입력 값은 아래와 같은 값을 사용.’ 이라 명시 되어 있지 않고

   출력 내용만 적혀 있으므로 입력 값은 없다.

   

2. **거래 내역, 관리점 정보, 계좌 정보 모두 이용 해야한다.**

   거래 정보를 추출 해야하고,   관리점명, 관리점 코드가 출력 값이므로

   관리점 정보를 이용해야한다.

   그리고 거래내역과 관리점 정보의 연관된 연결 고리가 없으므로

   관리점 코드와, 계좌 정보가 모두 있는 계좌 정보도 같이 이용해야한다.

   

3. 거래가 <u>취소 여부가 'N'</u>인 항목을 추출해야한다.

4. <u>출력 형태가 **JSON 배열**</u> 이므로 **‘연도 리스트’의 길이만큼  배열에 추가**해야한다.

5. **[조건 4]**의 이유 때문에 거래 내역의 **연도 리스트를 구해야한다.**

   **`h2`**의 DB **`ISO_YEAR()`** 함수를 이용하여 거래 일자를 **‘연도’**로 변환시켜 **`Group by`** 하여 

   거래 내역 정보 중 존재하는 <u>연도 리스트를 추출</u>한다.

   

6. **합계금액**이 큰 순서이므로 **ORDER BY 합계 금액 DESC** 를 한다.

   **출력 년도**는 오름 차순이므로 **ORDER BY ‘연도’ ASC**를 한다.

7. 연도별, 관리점별 합계 금액이므로 **GROUP BY 연도, 관리점 코드** 로 그룹핑한다.

 

> **거래 내역의 '연도 리스트'  추출**

```
SELECT	ISO_YEAR(거래일자)
  FROM	거래 내역 A
 GROUP BY ISO_YEAR(거래일자)
```



> **연도별, 관리점별 거래금액 합계 추출**

```
SELECT C.관리점 명           AS brName    -- 출력항목
       ,B.관리점코드         AS brCode    -- 출력항목
       ,SUM(A.거래 금액)     AS sumAmt    -- 출력항목
  From  거래 내역 A
       ,계좌 정보 B
       ,관리점 정보 C
 WHERE ISO_YEAR(A.거래일자) = {연도} ---- 거래 내역에서 추출한 연도를 입력받아 조회 조건으로 사용
   AND A.취소 여부 = ‘N’
   AND A.계좌 번호 = B.계좌 번호
   AND B.관리점 코드 = C.관리점코드
   GROUP C.관리점 명 ,B.관리점코드, ISO_YEAR(A.거래일자)
   ORDER BY ISO_YEAR(A.거래일자) ASC, 거래 금액 합계 DESC		-- 정렬
```



JSON 배열로 출력하기

```java
@GetMapping("/API3")
	public ResponseEntity<List<Map<String,Object>>> getAPI3() throws JSONException {
	List<Map<String,Object>> resultVO = new ArrayList<>();

	//거래 테이블 년도 리스트 가져오기
	List<Map<String,Object>> tranYearList = apiMapper.getTranYearList();
	
	// 연도 리스트 갯수 만큼 조회하여 List에 add
	for(int i=0 ; i < tranYearList.size() ; i++) {
        //연도별, 관리점별 거래금액 합계 조회
		List<API3ResultVO> api4ResultVo = apiMapper.getAPI3(tranYearList.get(i)); 
		Map<String,Object> mapVO = new HashMap<>();
		mapVO.put("year",Integer.parseInt(tranYearList.get(i).get("YEAR").toString()));
		mapVO.put("list",api4ResultVo);
		resultVO.add(mapVO);
	}
    // ResponseEntity를 이용하여 Map > Json 으로 변환
	return new ResponseEntity<List<Map<String,Object>>>(resultVO,HttpStatus.OK);
}
```
------

### 5. <문제 4> 의 해설
<문제 4>의 지점명으로 해당 지점의 거래 금액의 합계를 출력하기 위해서 아래와 같은 조건이 필요하다.

#####    **[ 조건 ]**

1. **입력값이 있다.**

   {brName  : 관리점명} 이라고 명시 되어 있으며,

   지점에 대한 거래금앱 합계를 구하기 위해서 **조회 조건으로 사용**해야한다.

   

2. **관리점 정보와 거래 내역을 이용해야한다.**

   해당 연결 고리는 관리점 코드 이다.

3. 거래 내역의 **취소 여부가 'N'**이어야 한다.

4. '이관 여부' 또는 '이관 전 관리점 코드', '이관 후 관리점 코드' 정보가 없으므로

   **`DECODE()`** 함수를 이용하여 **‘분당점’의 정보를 ‘판교점’으로 수정**한 데이터를 만들어

   기초 데이터를 만들어야 한다.

   > DECODE(관리점 코드, 'B', 'A', 관리점 코드,) AS 관리점 코드
   >
   > DECODE(관리점명, '분당점', '판교점', 관리점명) AS 관리점명



>  **관리점별 거래금액 합계 출력**

```
SELECT A.관리점명     AS brName     -- 출력 항목
     , A.관리점코드    AS brCode    -- 출력 항목
     , SUM(B.거래금액) AS sumAmt    -- 출력 항목
    FROM (
        SELECT  A.계좌번호
        ,DECODE(A.BRCODE, 'B', 'A', A.BRCODE) AS 관리점코드
        ,DECODE(B.BRNAME , '분당점', '판교점', B.BRNAME ) AS 관리점명
        FROM 계좌정보 A
            ,관리점정보 B
        WHERE A.관리점코드 = B.관리점코드
    )A
    ,거래내역 B
WHERE A.계좌번호= B.계좌번호
  AND B.취소여부='N' 
  AND A.관리점명= #{입력 받은 brName}
  
```



#### **분당 점인 경우** 처리

> **분당점인 경우 출력해야하는 내용**

```
HTTP STATUS ; 404
{ “code”:”404”,
  “메시지”:”br code not found error”
}
```

‘분당점’은 ‘판교점’으로 이관되어 없는 관리점이므로 조회시 조회되는 내용이 없다.

때문에 에러 코드를 리턴해야한다.

에러 코드를 리턴하기 위해 **`@ExceptionHandler`** 를 이용하여 **NotFoundException** 로 처리하였다.

그리고 <u>http status 상태를 리턴해야하므로 **ResponseEntity**를 이용</u>하였다.



> **Exception 처리**

**`@ControllerAdvice`** 는 해당 클래스가 당신의 어플리케이션의 예외처리를 맡을 거라고 알려주게 된다.

**`@ExceptionHandler`** 어노테이션을 사용하여 예외를 처리할 클래스를 정의한다. 

**`ResponseEntity`** Map<String, Object> responseBody 를 자동으로 JSON으로 변환 시켜주며

                                 Http Status '404' 를 리턴하였다.

```java
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundExceptionHandler
    							(NotFoundException e) {
    
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("code", "404");
    responseBody.put("메세지", e.getMessage());
    
    return new ResponseEntity<Map<String, Object>>(responseBody,HttpStatus.NOT_FOUND);

    }
}						 
```

