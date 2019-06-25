package com.kakaopay.api4pj.vo;

public class TranHis {
	
	private String 	trnFldDat;
	private String 	acctNo;
	private String 	trnFldNbr;
	private String 	trnFldAmt;
	private String 	trnFldFee;
	private String  canFldYon;
	public String getTrnFldDat() {
		return trnFldDat;
	}
	public void setTrnFldDat(String trnFldDat) {
		this.trnFldDat = trnFldDat;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getTrnFldNbr() {
		return trnFldNbr;
	}
	public void setTrnFldNbr(String trnFldNbr) {
		this.trnFldNbr = trnFldNbr;
	}
	public String getTrnFldAmt() {
		return trnFldAmt;
	}
	public void setTrnFldAmt(String trnFldAmt) {
		this.trnFldAmt = trnFldAmt;
	}
	public String getTrnfldfee() {
		return trnFldFee;
	}
	public void setTrnfldfee(String trnFldFee) {
		this.trnFldFee = trnFldFee;
	}
	public String getCanfldyon() {
		return canFldYon;
	}
	public void setCanfldyon(String canFldYon) {
		this.canFldYon = canFldYon;
	}
	
	@Override
	public String toString() {
		return "[trnFldDat=" + trnFldDat 
					+ ", acctNo=" + acctNo 
					+ ", trnFldNbr=" + trnFldNbr
					+ ", trnFldAmt=" + trnFldAmt 
					+ ", trnFldFee=" + trnFldFee 
					+ ", canFldYon=" + canFldYon 
					+ "]";
	}
	
	

	
}
