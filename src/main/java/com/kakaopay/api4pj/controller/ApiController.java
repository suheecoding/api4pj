package com.kakaopay.api4pj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.api4pj.config.NotFoundException;
import com.kakaopay.api4pj.mapper.ApiMapper;
import com.kakaopay.api4pj.vo.API1ReqVO;
import com.kakaopay.api4pj.vo.API1ResultVO;
import com.kakaopay.api4pj.vo.API2ReqVO;
import com.kakaopay.api4pj.vo.API2ResultVO;
import com.kakaopay.api4pj.vo.API3ResultVO;
import com.kakaopay.api4pj.vo.API4ReqVO;
import com.kakaopay.api4pj.vo.API4ResultVO;


@RestController
public class ApiController {
	
	private final static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	ApiMapper apiMapper;
	
	@GetMapping("/")
	public ResponseEntity<String> home() throws NotFoundException { 
		logger.debug("home => '/'");
		return new ResponseEntity<String>("hollo home!!",HttpStatus.OK);
	}
	
	@GetMapping("/API1")
	public ResponseEntity<List<API1ResultVO>> getAPI1(@RequestBody String reqBody)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		API1ReqVO api1RepVO = mapper.readValue(reqBody, API1ReqVO.class);
		logger.debug("\n\n'>>>>>>>>>> 문제 1번 API1 입력 파라미터 및 vo 변환 \n\n ==> reqBody={}, api4RepVO={}\n\n",reqBody,api1RepVO);
		
		List<API1ResultVO> resutVO = apiMapper.getAPI1(api1RepVO);
		return new ResponseEntity<List<API1ResultVO>>(resutVO,HttpStatus.OK);
	}
	
	@GetMapping("/API2")
	public ResponseEntity<List<API2ResultVO>> getAPI2(@RequestBody String reqBody)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		API2ReqVO api2RepVO = mapper.readValue(reqBody, API2ReqVO.class);
		logger.debug("\n\n'>>>>>>>>>> 문제 2번  API2 입력 파라미터 및 vo 변환 \n\n ==> reqBody={}, api4RepVO={}\n\n",reqBody,api2RepVO);
		
		List<API2ResultVO> resutVO = apiMapper.getAPI2(api2RepVO);
		return new ResponseEntity<List<API2ResultVO>>(resutVO,HttpStatus.OK);
	}
	
	@GetMapping("/API3")
	public ResponseEntity<List<Map<String,Object>>> getAPI3() throws JSONException {
		
		List<Map<String,Object>> resultVO = new ArrayList<>();
	
		//거래 테이블 년도 리스트 가져오기
		List<Map<String,Object>> tranYearList = apiMapper.getTranYearList();
		
		// 년도별 조회 하여 Add
		for(int i=0 ; i < tranYearList.size() ; i++) {
			List<API3ResultVO> api4ResultVo = apiMapper.getAPI3(tranYearList.get(i));
			Map<String,Object> mapVO = new HashMap<>();
			mapVO.put("year",Integer.parseInt(tranYearList.get(i).get("YEAR").toString()));
			mapVO.put("list",api4ResultVo);
			resultVO.add(mapVO);
		}
		return new ResponseEntity<List<Map<String,Object>>>(resultVO,HttpStatus.OK);
	}	

	@GetMapping("/API4")
	public ResponseEntity<API4ResultVO> getAPI4(@RequestBody String reqBody) 
			throws JsonParseException, JsonMappingException, IOException, NotFoundException {
		
		ObjectMapper mapper = new ObjectMapper();
		API4ReqVO api4RepVO = mapper.readValue(reqBody, API4ReqVO.class);
		logger.debug("\n\n'>>>>>>>>>> 문제 4번 API4 입력 파라미터 및 vo 변환 \n\n ==> reqBody={}, api4RepVO={}\n\n",reqBody,api4RepVO);
		
		API4ResultVO resutVO = apiMapper.getAPI4(api4RepVO);
		if(resutVO == null) {
			throw new NotFoundException("br code not found error");
		}
		return new ResponseEntity<API4ResultVO>(resutVO,HttpStatus.OK);
	}

}


