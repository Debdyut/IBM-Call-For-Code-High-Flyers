package com.hackathon.vo;

public class DprnCalcnVo {

	int totShadedSlctn;
	boolean qstn1And2Incld;
	boolean qstn1Or2Incld;
	int totScor;


	public int getTotShadedSlctn() {
		return totShadedSlctn;
	}
	public void setTotShadedSlctn(int totShadedSlctn) {
		this.totShadedSlctn = totShadedSlctn;
	}
	public boolean isQstn1And2Incld() {
		return qstn1And2Incld;
	}
	public void setQstn1And2Incld(boolean qstn1And2Incld) {
		this.qstn1And2Incld = qstn1And2Incld;
	}
	public boolean isQstn1Or2Incld() {
		return qstn1Or2Incld;
	}
	public void setQstn1Or2Incld(boolean qstn1Or2Incld) {
		this.qstn1Or2Incld = qstn1Or2Incld;
	}
	public int getTotScor() {
		return totScor;
	}
	public void setTotScor(int totScor) {
		this.totScor = totScor;
	}
	@Override
	public String toString() {
		return "DrpnCalcnVo [totShadedSlctn=" + totShadedSlctn + ", qstn1And2Incld=" + qstn1And2Incld
				+ ", qstn1Or2Incld=" + qstn1Or2Incld + ", totScor=" + totScor + "]";
	}




}
