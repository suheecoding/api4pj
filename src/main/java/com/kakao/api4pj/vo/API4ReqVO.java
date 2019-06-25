package com.kakao.api4pj.vo;

public class API4ReqVO {
	
	private String brName;

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}

	@Override
	public String toString() {
		return "[brName=" + brName + "]";
	}	
	
}
