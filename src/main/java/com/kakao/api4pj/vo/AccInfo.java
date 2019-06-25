package com.kakao.api4pj.vo;

public class AccInfo {
	private String acctNo;
	private String name;
	private String brCode;

	public AccInfo() {
		
	}
	
	public AccInfo(String name, String acctNo, String brCode) {
		this.name = name;
		this.acctNo = acctNo;
        this.brCode = brCode; 
	}

	
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrCode() {
		return brCode;
	}

	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}

	@Override
	public String toString() {
		return "acctNo:" + acctNo
			+ ", name:" + name 
			+ ", brCode:" + brCode;
	}
}
