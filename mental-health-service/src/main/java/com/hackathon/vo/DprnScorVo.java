package com.hackathon.vo;

import java.util.HashMap;
import java.util.Map;

public class DprnScorVo {

	String dprnStt;
	Integer dprnScor;
	String colorCd;
	
	private Map<String, Integer> sentimentFrequescy = new HashMap<String, Integer>();
	

	public String getDprnStt() {
		return dprnStt;
	}
	public void setDprnStt(String dprnStt) {
		this.dprnStt = dprnStt;
	}
	public Integer getDprnScor() {
		return dprnScor;
	}
	public void setDprnScor(Integer dprnScor) {
		this.dprnScor = dprnScor;
	}
	public String getColorCd() {
		return colorCd;
	}
	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}

	public Map<String, Integer> getSentimentFrequescy() {
		return sentimentFrequescy;
	}
	public void setSentimentFrequescy(Map<String, Integer> sentimentFrequescy) {
		this.sentimentFrequescy = sentimentFrequescy;
	}
	@Override
	public String toString() {
		return "DprnScorVo [dprnStt=" + dprnStt + ", dprnScor=" + dprnScor + ", colorCd=" + colorCd
				+ ", sentimentFrequescy=" + sentimentFrequescy + "]";
	}

}
