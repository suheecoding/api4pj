package com.kakao.api4pj.vo;

public class API4ResultVO {
	
	private String brName;
	private String brCode;
	private int    sumAmt;
	
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getBrCode() {
		return brCode;
	}
	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}
	public int getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(int sumAmt) {
		this.sumAmt = sumAmt;
	}
	
	@Override
	public String toString() {
		return "[brName=" + brName + ", brCode=" + brCode + ", sumAmt=" + sumAmt + "]";
	}		
	
}
