package com.hackathon.vo;

public class RspnVo {

	DprnScorVo dprnScor;

	String rspnStt;
	String rspnMsg;

	public DprnScorVo getDprnScor() {
		return dprnScor;
	}
	public void setDprnScor(DprnScorVo dprnScor) {
		this.dprnScor = dprnScor;
	}
	public String getRspnStt() {
		return rspnStt;
	}
	public void setRspnStt(String rspnStt) {
		this.rspnStt = rspnStt;
	}
	public String getRspnMsg() {
		return rspnMsg;
	}
	public void setRspnMsg(String rspnMsg) {
		this.rspnMsg = rspnMsg;
	}

	@Override
	public String toString() {
		return "RspnVo [dprnScor=" + dprnScor + ", rspnStt=" + rspnStt + ", rspnMsg=" + rspnMsg + "]";
	}




}
