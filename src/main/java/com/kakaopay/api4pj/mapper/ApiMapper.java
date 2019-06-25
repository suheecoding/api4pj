package com.kakaopay.api4pj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kakaopay.api4pj.vo.API1ReqVO;
import com.kakaopay.api4pj.vo.API1ResultVO;
import com.kakaopay.api4pj.vo.API2ReqVO;
import com.kakaopay.api4pj.vo.API2ResultVO;
import com.kakaopay.api4pj.vo.API3ResultVO;
import com.kakaopay.api4pj.vo.API4ReqVO;
import com.kakaopay.api4pj.vo.API4ResultVO;
import com.kakaopay.api4pj.vo.AccInfo;
import com.kakaopay.api4pj.vo.BranchInfo;
import com.kakaopay.api4pj.vo.TranHis;

@Mapper
public interface ApiMapper {
	
	//데이터 이력
	public int insertAccInfo(List<AccInfo> accInfoList);
	public int insertBranchInfo(List<BranchInfo> branchInfoList);
	public int insertTranHis(List<TranHis> tranHisList);
	
	
	/**
	 * <p>API1 조회</p>
	 * <p>각 연도별 합계 금액이 가장 많은 고객 추출 (합계 금액 = 거래금액 - 수수료 금액)</p>
	 * @param  api1RepVO
	 * @return List<API1ResultVO>
	 */
	public List<API1ResultVO> getAPI1(API1ReqVO api1RepVO); 
	
	
	/**
	 * <p>API2 조회</p>
	 * <p>각 년도별 거래가 없는고객 추출</p>
	 * @param  api2RepVO
	 * @return List<API2ResultVO>
	 */	
	public List<API2ResultVO> getAPI2(API2ReqVO api2RepVO);
	
	
	/**
	 * <p>API3 조회 1</p>
	 * <p>API3을 조회하기 위한 거래이력 년도  조회</p>
	 * @param api4RepVO
	 * @return API4ResultVO
	 */
	public List<Map<String,Object>> getTranYearList();
	
	
	/**
	 * <p>API3 조회 2</p>
	 * <p>연도별, 관리점 별 거래금액 합계 조회 (금액이 큰 순서 정렬)</p>
	 * @param  map
	 * @return List<API3ResultVO>
	 */
	public List<API3ResultVO> getAPI3(Map<String,Object> map);
	
	
	/**
	 * <p>API4 조회</p>
	 * <p>지점의 거래금액 합계 출력</p>
	 * @param api4RepVO
	 * @return API4ResultVO
	 */
	public API4ResultVO getAPI4(API4ReqVO api4RepVO);
	
	
}
