package com.hackathon.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hackathon.vo.ReqVo;
import com.hackathon.vo.ReqstVo;

@Component
public class OperationUtil {

	public boolean vldtReqData(List<ReqVo> reqs) {
		//        rspns
		boolean rtrnVldtn = false;

		if(reqs != null && !reqs.isEmpty() && reqs.size()>=6) {
			rtrnVldtn = true;
		}



		return rtrnVldtn;
	}

	public boolean vldtReqData(ReqstVo reqs) {
		//        rspns
		boolean rtrnVldtn = false;

		if(reqs != null && !StringUtils.isEmpty(reqs.getQuestion1())) {
			rtrnVldtn = true;
		}



		return rtrnVldtn;
	}

	public List<ReqVo> ppldreqVos(ReqstVo reqstVo) {

		List<ReqVo> reqVos = new ArrayList<ReqVo>();
		
		ReqVo reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion1());
		reqVo.setAnswer(reqstVo.getAnswer1());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion2());
		reqVo.setAnswer(reqstVo.getAnswer2());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion3());
		reqVo.setAnswer(reqstVo.getAnswer3());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion4());
		reqVo.setAnswer(reqstVo.getAnswer4());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion5());
		reqVo.setAnswer(reqstVo.getAnswer5());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion6());
		reqVo.setAnswer(reqstVo.getAnswer6());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion7());
		reqVo.setAnswer(reqstVo.getAnswer7());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion8());
		reqVo.setAnswer(reqstVo.getAnswer8());
		
		reqVos.add(reqVo);
		
		reqVo = new ReqVo();
		
		reqVo.setQuestion(reqstVo.getQuestion9());
		reqVo.setAnswer(reqstVo.getAnswer9());
		
		reqVos.add(reqVo);

		
//		System.out.println(reqVos.toString());
		
		return reqVos;
		
		
	}


}
