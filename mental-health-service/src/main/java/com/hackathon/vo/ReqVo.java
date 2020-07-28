package com.hackathon.vo;

public class ReqVo {

	String question;
	String answer;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		return "ReqVo [question=" + question + ", answer=" + answer + "]";
	}
}
