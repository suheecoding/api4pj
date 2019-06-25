package com.kakaopay.api4pj.vo;

public class API2ResultVO {
	
	private int year;
	private String name;
	private String acctNo;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getName() {
		return name;
	}
	public void setSumAmt(String name) {
		this.name = name;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	@Override
	public String toString() {
		return "[year=" + year + ", name=" + name + ", acctNo=" + acctNo  + "]";
	}

	
}
