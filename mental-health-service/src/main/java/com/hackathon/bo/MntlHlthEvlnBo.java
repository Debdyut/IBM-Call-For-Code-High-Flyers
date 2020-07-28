package com.hackathon.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.hackathon.clnt.vo.ToneAnalyzeVo;
import com.hackathon.clnt.vo.ToneVo;
import com.hackathon.utils.MntlHlthEvlnCnst;
import com.hackathon.vo.DprnCalcnVo;
import com.hackathon.vo.DprnScorVo;
import com.hackathon.vo.ReqVo;
import com.hackathon.vo.ReqstVo;
import com.hackathon.vo.RspnVo;

@Component
public class MntlHlthEvlnBo {

	@Autowired
	MntlHlthEvlnData mntlHlthEvlnData;

	public RspnVo getMntlHlthEvln(List<ReqVo> reqVos) {

		RspnVo rspnVo = null;
		int totScor = 0;
		int totScorLcl = 0;
		int totShadedSlctn = 0;
		boolean qstn1And2Incld = false;
		boolean qstn1Or2Incld = false;
		ReqVo reqVo;
		for (int i = 0; i < reqVos.size(); i++) {
			reqVo = reqVos.get(i);

			if (reqVo != null) {

				// System.out.println("reqVo.getAnswer()===="+reqVo.getAnswer());

				totScorLcl = mntlHlthEvlnData.getScore(reqVo.getAnswer());

				if (totScorLcl >= 2) {
					totShadedSlctn++;

					if (i == 0 || i == 1) {

						if (qstn1Or2Incld) {
							qstn1And2Incld = true;
							qstn1Or2Incld = false;
						} else {
							qstn1Or2Incld = true;
						}

					}

				}

				totScor = totScor + totScorLcl;
			}
		}

//		if (totScor > 0) {

			DprnCalcnVo dprnCalcn = new DprnCalcnVo();

			dprnCalcn.setQstn1And2Incld(qstn1And2Incld);
			dprnCalcn.setQstn1Or2Incld(qstn1Or2Incld);
			dprnCalcn.setTotScor(totScor);
			dprnCalcn.setTotShadedSlctn(totShadedSlctn);

			rspnVo = getDprn(dprnCalcn);
//		}

		return rspnVo;
	}



	private RspnVo getDprn(DprnCalcnVo dprnCalcn) {
		RspnVo rspnVo = new RspnVo();

		if (dprnCalcn.getTotShadedSlctn() >= 4) {

			if (dprnCalcn.isQstn1Or2Incld()) {

				rspnVo.setRspnMsg(MntlHlthEvlnCnst.DEPRESSIVE_DISORDER);

			} else if (dprnCalcn.isQstn1And2Incld()) {

				rspnVo.setRspnMsg(MntlHlthEvlnCnst.MAJOR_DEPRESSIVE_DISORDER);
			}
		}

		rspnVo = getDprnScor(rspnVo, dprnCalcn);
		return rspnVo;
	}

	private RspnVo getDprnScor(RspnVo rspnVo, DprnCalcnVo dprnCalcn) {

		DprnScorVo dprnScor = new DprnScorVo();

		dprnScor.setDprnScor(dprnCalcn.getTotScor());
		String dprnSvrty;
		String colorCd;

		if (StringUtils.isEmpty(rspnVo.getRspnMsg())
				|| rspnVo.getRspnMsg().equals(MntlHlthEvlnCnst.DEPRESSIVE_DISORDER)) {

			dprnSvrty = mntlHlthEvlnData.getDprnSvrty(dprnCalcn.getTotScor());

			dprnScor.setDprnStt(dprnSvrty);
			colorCd = mntlHlthEvlnData.getDprnSvrtyColor(dprnCalcn.getTotScor());
			dprnScor.setColorCd(colorCd);

		} else if (rspnVo.getRspnMsg().equals(MntlHlthEvlnCnst.MAJOR_DEPRESSIVE_DISORDER)) {

			dprnCalcn.setTotScor(21);

			dprnSvrty = mntlHlthEvlnData.getDprnSvrty(dprnCalcn.getTotScor());
			dprnScor.setDprnStt(dprnSvrty);
			colorCd = mntlHlthEvlnData.getDprnSvrtyColor(dprnCalcn.getTotScor());
			dprnScor.setColorCd(colorCd);
		}

		rspnVo.setDprnScor(dprnScor);
		return rspnVo;

	}
	
	
	
	public RspnVo getEvlnAIBasedRpt(List<ReqVo> reqVos) {

		RspnVo rspnVo = null;
		int totScor = 0;
		int totScorLcl = 0;
		int totShadedSlctn = 0;
		boolean qstn1And2Incld = false;
		boolean qstn1Or2Incld = false;
		ReqVo reqVo;
		Map<String, Integer> sentimentFrequescy = new HashMap<String, Integer>();
		for (int i = 0; i < reqVos.size(); i++) {
			reqVo = reqVos.get(i);

			if (reqVo != null) {

				// System.out.println("reqVo.getAnswer()===="+reqVo.getAnswer());

//				totScorLcl = mntlHlthEvlnData.getScore(reqVo.getAnswer());
				
				
				String statement = mntlHlthEvlnData.getPpldCases("question"+(i+1), reqVo.getAnswer());
				
				
				totScorLcl = callToneAnalyze(statement, sentimentFrequescy);
//				totScor = totScor + totScorLcl;
				

				if (totScorLcl >= 2) {
					totShadedSlctn++;

					if (i == 0 || i == 1) {

						if (qstn1Or2Incld) {
							qstn1And2Incld = true;
							qstn1Or2Incld = false;
						} else {
							qstn1Or2Incld = true;
						}

					}

				}
//
				totScor = totScor + totScorLcl;
			}
		}

//		if (totScor > 0) {

			DprnCalcnVo dprnCalcn = new DprnCalcnVo();

			dprnCalcn.setQstn1And2Incld(qstn1And2Incld);
			dprnCalcn.setQstn1Or2Incld(qstn1Or2Incld);
			dprnCalcn.setTotScor(totScor);
			dprnCalcn.setTotShadedSlctn(totShadedSlctn);

			rspnVo = getDprn(dprnCalcn);
			
			rspnVo.getDprnScor().setSentimentFrequescy(sentimentFrequescy);
//		}

		return rspnVo;
	}
	
	
	
	
	private Integer callToneAnalyze(String stmnt, Map<String, Integer> sentimentFrequescy) {
		int score=0;
		
		HttpHeaders httpheaders = new HttpHeaders();
		httpheaders.add("Authorization", "Basic <API_KEY>");
		httpheaders.setContentType(MediaType.APPLICATION_JSON);
		
		String url = "<API_URL>";
		
		
		Map<String, String> mp = new HashMap<String, String>();
		
//		mp.put("text", "I fill less interest in doing things every day.I don't know what should I do.:(.");
		
//		System.out.println(stmnt);
		mp.put("text", stmnt);
		
		HttpEntity httpEntity = new HttpEntity(mp,httpheaders);
	
		
		
		RestTemplate restTemplate = new RestTemplate();
		
//		ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
		
		ResponseEntity<ToneAnalyzeVo> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ToneAnalyzeVo.class);
		
		if(exchange.getStatusCode().equals(HttpStatus.OK)) {
			ToneAnalyzeVo toneAnalyzeVo = exchange.getBody();
			
			score = calculateScore(toneAnalyzeVo, sentimentFrequescy);
			
		}
		
//		System.out.println(exchange.toString());
		
		return score;
	}
	
	
	private Integer calculateScore(ToneAnalyzeVo toneAnalyzeVo, Map<String, Integer> sentimentFrequescy) {
		
		int score=0;
		
//		System.out.println(toneAnalyzeVo.toString());
		
		List<ToneVo> tones = toneAnalyzeVo.getDocumentTone().getTones();
		
		if(!tones.isEmpty()) {
			
			for(ToneVo tone:tones) {
				
//				System.out.println("tone.getToneId(): "+tone.getToneId());
				
				if(tone.getToneId().equalsIgnoreCase("joy")) {
					
					Integer j = sentimentFrequescy.get("joy"); 
					sentimentFrequescy.put("joy", (j == null) ? 1 : j + 1); 
					
					
					score=0;
					break;
					
				} else {
					
					Integer j = sentimentFrequescy.get(tone.getToneId()); 
					sentimentFrequescy.put(tone.getToneId(), (j == null) ? 1 : j + 1); 
					
					score = score + mntlHlthEvlnData.getIbmCloudTonesScore(tone.getToneId());
					
				}
			}
		}
		
		
		
		return score;
	}
}
