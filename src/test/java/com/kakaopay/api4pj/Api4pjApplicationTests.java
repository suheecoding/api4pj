package com.kakaopay.api4pj;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.api4pj.mapper.ApiMapper;
import com.kakaopay.api4pj.vo.API1ReqVO;
import com.kakaopay.api4pj.vo.API2ReqVO;
import com.kakaopay.api4pj.vo.API4ReqVO;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class Api4pjApplicationTests {

	private final static Logger logger = LoggerFactory.getLogger(Api4pjApplicationTests.class);
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ApiMapper apiMapper;
	
	
	@Test  // << JUnit Test 실행시 제외 시키고 싶다면 주석 처리 하여주세요 //@Test
	public void dbAPI1() {
		logger.debug("jUnit 문제 1번 API1 테스트");
		
		MvcResult mvcResult = null;
		API1ReqVO api1ReqVO = new API1ReqVO();
		
		// 아래 값을 변경하며 테스트 하여 주세요. 
		api1ReqVO.setYear(2018);
		api1ReqVO.setName("계정명");
		api1ReqVO.setAcctNo("계좌번호");
		api1ReqVO.setSumAmt(0000);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String content = mapper.writeValueAsString(api1ReqVO);
			mvcResult = mvc.perform(get("/API1")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("MockMvc Error =>",e);
		}
		if (mvcResult != null) {
			try {
				String resultBody = mvcResult.getResponse().getContentAsString();
				logger.debug("\n\n'>>>>>>>> 문제 1번 API1 결과 <<<<<<<<<'\n\n{}", resultBody);
			} catch (UnsupportedEncodingException e) {
				logger.error("resultBody UnsupportedEncodingException",e);
			}
		}
	}
	
	@Test  // << JUnit Test 실행시 제외 시키고 싶다면 주석 처리 하여주세요 //@Test
	public void dbAPI2() {
		logger.debug("jUnit 문제 2번 API2 테스트");
		
		MvcResult mvcResult = null;
		API2ReqVO api2ReqVO = new API2ReqVO();
		
		// 아래 값을 변경하며 테스트 하여 주세요.
		// 0000, 2222 등 입력하게되면 거래 내역에 해당 연도 정보가 없으므로 전체 조회 됨.
		api2ReqVO.setYear(2018); 
		api2ReqVO.setName("계정명");
		api2ReqVO.setAcctNo("계좌번호");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String content = mapper.writeValueAsString(api2ReqVO);
			mvcResult = mvc.perform(get("/API2")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("MockMvc Error =>",e);
		}
		if (mvcResult != null) {
			try {
				String resultBody = mvcResult.getResponse().getContentAsString();
				logger.debug("\n\n'>>>>>>>> 문제 2번 API2 결과 <<<<<<<<<'\n\n{}", resultBody);
			} catch (UnsupportedEncodingException e) {
				logger.error("resultBody UnsupportedEncodingException",e);
			}
		}
	}
	
	@Test  // << JUnit Test 실행시 제외 시키고 싶다면 주석 처리 하여주세요 //@Test
	public void dbAPI3() { 
		logger.debug("jUnit API3 테스트");
		MvcResult mvcResult = null;
		// 입력값이 없습니다.
		try {
			mvcResult = mvc.perform(get("/API3"))
					.andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("MockMvc Error =>",e);
		}
		if (mvcResult != null) {
			try {
				String resultBody = mvcResult.getResponse().getContentAsString();
				logger.debug("\n\n'>>>>>>>> 문제 3번 API3 결과 <<<<<<<<<'\n\n{}", resultBody);
			} catch (UnsupportedEncodingException e) {
				logger.error("resultBody UnsupportedEncodingException",e);
			}
		}
	}
	
	@Test  // << JUnit Test 실행시 제외 시키고 싶다면 주석 처리 하여주세요 //@Test
	public void dbAPI4_1() {
		logger.debug("jUnit 문제 4번 API4_1 테스트 판교점인 경우 Test");
		MvcResult mvcResult = null;
		
		API4ReqVO api4ReqVO = new API4ReqVO();
		api4ReqVO.setBrName("판교점");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String content = mapper.writeValueAsString(api4ReqVO);
			mvcResult = mvc.perform(get("/API4")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON))
					.andReturn();
		} catch (JsonProcessingException e) {
			//e.printStackTrace();
			logger.error("Json stringify Error =>",e);
		} catch (Exception e1) {
			logger.error("MockMvc Error =>",e1);
		}
		
		if (mvcResult != null) {
			try {
				String resultBody = mvcResult.getResponse().getContentAsString();
				logger.debug("\n\n'>>>>>>>> 문제 4번 API4 판교점 결과 <<<<<<<<<'\n\n{}", resultBody);
			} catch (UnsupportedEncodingException e) {
				logger.error("resultBody UnsupportedEncodingException",e);
			}
		}
	}
	
	
	@Test  // << JUnit Test 실행시 제외 시키고 싶다면 주석 처리 하여주세요 //@Test
	public void dbAPI4_2() {
		logger.debug("jUnit 문제 4번 API4_2 테스트 분당점인 경우 Test");
		MvcResult mvcResult = null;
		
		API4ReqVO api4ReqVO = new API4ReqVO();
		api4ReqVO.setBrName("분당점");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String content = mapper.writeValueAsString(api4ReqVO);
			mvcResult = mvc.perform(get("/API4")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON))
					.andReturn();
		} catch (JsonProcessingException e) {
			//e.printStackTrace();
			logger.error("Json stringify Error =>",e);
		} catch (Exception e1) {
			logger.error("MockMvc Error =>",e1);
		}
		
		if (mvcResult != null) {
			try {
				String resultBody = mvcResult.getResponse().getContentAsString();
				logger.debug("\n\n'>>>>>>>> 문제 4번 API4 분당점 결과 <<<<<<<<<'\n\n{}", resultBody);
			} catch (UnsupportedEncodingException e) {
				logger.error("resultBody UnsupportedEncodingException",e);
			}
		}
	}
	
	

}
