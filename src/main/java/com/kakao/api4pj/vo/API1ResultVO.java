package com.kakao.api4pj.vo;

public class API1ResultVO {
	
	private int year;
	private String name;
	private String acctNo;
	private int sumAmt;
	
			
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}	
	
	public int getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(int sumAmt) {
		this.sumAmt = sumAmt;
	}

	@Override
	public String toString() {
		return "[year=" + year + ", name=" + name + ", acctNo=" + acctNo + ", sumAmt=" + sumAmt + "]";
	}	
	
}
