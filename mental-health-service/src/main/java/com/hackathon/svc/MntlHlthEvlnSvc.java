package com.hackathon.svc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackathon.bo.MntlHlthEvlnBo;
import com.hackathon.utils.OperationUtil;
import com.hackathon.vo.ReqVo;
import com.hackathon.vo.ReqstVo;
import com.hackathon.vo.RspnVo;

@RestController
public class MntlHlthEvlnSvc {

	@Autowired
	OperationUtil operationUtil;
	@Autowired
	MntlHlthEvlnBo mntlHlthEvlnBo;




//	@RequestMapping(value = "/getEvlnRpt", method = RequestMethod.POST)
	public ResponseEntity<Object> getMntlHlthEvln(
			@RequestBody(required = true) List<ReqVo> reqVos)
					throws JsonProcessingException {
		RspnVo rspnVo = new RspnVo();

		try {

			if (!operationUtil.vldtReqData(reqVos)) {

				rspnVo.setRspnStt(HttpStatus.BAD_REQUEST.toString());

				rspnVo.setRspnMsg("invalid Request.");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rspnVo);
			}



			//			Map<String, List<Object>> rspns = new HashMap<String, List<Object>>();
			//			tstCases = new ArrayList<Integer>();
			//
			//			String flNm = testCaseFl;// "C:\\Delta_Offshore\\Sandip\\Workspace\\Eclipse_ws\\JavaPorject_POC\\testcases.txt";
			//
			//			List<Object> testCases = new ArrayList<Object>();
			//
			//			ObjectMapper mapper = new ObjectMapper();
			//			String json = "";


			RspnVo mntlHlthEvln = mntlHlthEvlnBo.getMntlHlthEvln(reqVos);


			//			return mntlHlthEvln;
			return ResponseEntity.status(HttpStatus.OK).body(mntlHlthEvln);
			//    return ResponseEntity.status(HttpStatus.OK).body(testCases.toString());

		}
		catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			//			testCases.add(e.getMessage());
			//			//            rspns.put("404", testCases);
			//			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testCases);
			//
			//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(testCases);
		}

	}


	@RequestMapping(value = "/mntlHlthEvln/api/getEvlnRpt", method = RequestMethod.POST)
	public ResponseEntity<Object> getMntlHlthEvln1(
			@RequestBody(required = true) ReqstVo reqstVo)
					throws JsonProcessingException {
		RspnVo rspnVo = new RspnVo();

		try {

			if (!operationUtil.vldtReqData(reqstVo)) {

				rspnVo.setRspnStt(HttpStatus.BAD_REQUEST.toString());

				rspnVo.setRspnMsg("invalid Request.");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rspnVo);
			}

			List<ReqVo> reqVos = operationUtil.ppldreqVos(reqstVo);

			RspnVo mntlHlthEvln = mntlHlthEvlnBo.getMntlHlthEvln(reqVos);


			//			return mntlHlthEvln;
			return ResponseEntity.status(HttpStatus.OK).body(mntlHlthEvln);
			//    return ResponseEntity.status(HttpStatus.OK).body(testCases.toString());

		}
		catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			//			testCases.add(e.getMessage());
			//			//            rspns.put("404", testCases);
			//			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testCases);
			//
			//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(testCases);
		}

	}




	@RequestMapping(value = "/mntlHlthEvln/api/getEvlnAIBasedRpt", method = RequestMethod.POST)
	public ResponseEntity<Object> getMntlHlthEvlnUsingIbmToneAnalyzer(
			@RequestBody(required = true) ReqstVo reqstVo)
					throws JsonProcessingException {
		RspnVo rspnVo = new RspnVo();

		try {

			if (!operationUtil.vldtReqData(reqstVo)) {

				rspnVo.setRspnStt(HttpStatus.BAD_REQUEST.toString());

				rspnVo.setRspnMsg("invalid Request.");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rspnVo);
			}

			List<ReqVo> reqVos = operationUtil.ppldreqVos(reqstVo);

			RspnVo mntlHlthEvln = mntlHlthEvlnBo.getEvlnAIBasedRpt(reqVos);


			//			return mntlHlthEvln;
			return ResponseEntity.status(HttpStatus.OK).body(mntlHlthEvln);
			//    return ResponseEntity.status(HttpStatus.OK).body(testCases.toString());

		}
		catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			
		}

	}









	public ResponseEntity<Object> getMntlHlthEvlnOld(
			@RequestBody(required = true) List<ReqVo> reqVos)
					throws JsonProcessingException {
		RspnVo rspnVo = new RspnVo();

		try {

			if (!operationUtil.vldtReqData(reqVos)) {

				rspnVo.setRspnStt(HttpStatus.BAD_REQUEST.toString());

				rspnVo.setRspnMsg("invalid Request.");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rspnVo);
			}



			//			Map<String, List<Object>> rspns = new HashMap<String, List<Object>>();
			//			tstCases = new ArrayList<Integer>();
			//
			//			String flNm = testCaseFl;// "C:\\Delta_Offshore\\Sandip\\Workspace\\Eclipse_ws\\JavaPorject_POC\\testcases.txt";
			//
			//			List<Object> testCases = new ArrayList<Object>();
			//
			//			ObjectMapper mapper = new ObjectMapper();
			//			String json = "";


			RspnVo mntlHlthEvln = mntlHlthEvlnBo.getMntlHlthEvln(reqVos);



			return ResponseEntity.status(HttpStatus.OK).body(mntlHlthEvln);
			//    return ResponseEntity.status(HttpStatus.OK).body(testCases.toString());

		}
		catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
			//			testCases.add(e.getMessage());
			//			//            rspns.put("404", testCases);
			//			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testCases);
			//
			//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(testCases);
		}

	}




	@RequestMapping(value = "/v1/checkHealth", method = RequestMethod.GET)
	public ResponseEntity<Object> test500() {
		try {

			// ResponseEntity<String> dvcRspnVos = "";
			// String rspnVo = dvcRspnVos.getBody();
			// System.out.println(rspnVo.toString());

			// System.out.println(dvcRspnVo.toString());

			Map<String, String> rspns = new HashMap<String, String>();

			rspns.put("500", " resulted in 500 (Internal Server Error); invoking error handler");
			return ResponseEntity.status(HttpStatus.OK).body(rspns);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public void test501() {
		try {

			// ResponseEntity<String> dvcRspnVos = "";
			// String rspnVo = dvcRspnVos.getBody();
			// System.out.println(rspnVo.toString());

			// System.out.println(dvcRspnVo.toString());

			Map<String, String> rspns = new HashMap<String, String>();

			rspns.put("500", " resulted in 500 (Internal Server Error); invoking error handler");
			System.out.println(rspns);
			//            return ResponseEntity.status(HttpStatus.OK).body(rspns);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
	}


}
