package com.kakaopay.api4pj.vo;

public class BranchInfo {
	private String brCode;
	private String brName;

	public BranchInfo() {
		
	}
	
	public BranchInfo(String brCode, String brName) {
		this.brCode = brCode; 
		this.brName = brName;
	}	

	public String getBrCode() {
		return brCode;
	}

	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}

	@Override
	public String toString() {
		return "brCode:" + brCode
				+"brName:"+brName;
	}
}
