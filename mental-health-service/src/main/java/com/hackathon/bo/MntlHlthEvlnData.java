package com.hackathon.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hackathon.utils.MntlHlthEvlnCnst;

@Component
public class MntlHlthEvlnData {

	enum Answers {
		NOT_AT_ALL("not at all"), NEVER("never"), SEVERAL_DAYS("several days"),
		MORE_THAN_HALF_THE_DAYS("more than half the days"), HALF_THE_DAYS("half the days"),
		HALF_OF_THE_DAYS("half of the days"), ALMOST_EVERY_HALF_DAYS("almost every half days"),
		NEARLY_EVERY_DAY("nearly every day"), EVERY_DAY("every day"), EACH_DAY("each day");

		private final String value;

		Answers(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

//	public static void main(String[] args) {
//		System.out.println(Answers.ALMOST_EVERY_HALF_DAYS.getValue());
//		
//		MntlHlthEvlnData dd = new MntlHlthEvlnData();
//		
//		dd.MntlHlthEvlnDataOld();
//		
//		System.out.println(dd.ppldCases);
//	}

	Map<String, Map<String, String>> ppldCases = null;
	Map<String, Integer> ibmCloudTonesScore = new HashMap<String, Integer>();

	Map<String, Integer> ansScors = new HashMap<String, Integer>();

	Map<String, String> dprnSvrty = new HashMap<String, String>();

	public MntlHlthEvlnData() {

		ansScors.put(Answers.NOT_AT_ALL.getValue(), 0);
		ansScors.put(Answers.NEVER.getValue(), 0);
		ansScors.put(Answers.SEVERAL_DAYS.getValue(), 1);
		ansScors.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(), 2);
		ansScors.put(Answers.HALF_THE_DAYS.getValue(), 2);
		ansScors.put(Answers.HALF_OF_THE_DAYS.getValue(), 2);
		ansScors.put(Answers.ALMOST_EVERY_HALF_DAYS.getValue(), 2);
		ansScors.put(Answers.NEARLY_EVERY_DAY.getValue(), 3);
		ansScors.put(Answers.EVERY_DAY.getValue(), 3);
		ansScors.put(Answers.EACH_DAY.getValue(), 3);

		/***
		 * Depression Severity Population
		 */

		dprnSvrty.put(MntlHlthEvlnCnst.NO_DEPRESSION, "No depression");
		dprnSvrty.put(MntlHlthEvlnCnst.ONE_TO_FOUR, "Minimal depression");
		dprnSvrty.put(MntlHlthEvlnCnst.FIVE_TO_NINE, "Mild depression");
		dprnSvrty.put(MntlHlthEvlnCnst.TEN_TO_FOURTEEN, "Moderate depression");
		dprnSvrty.put(MntlHlthEvlnCnst.FIFTEEN_TO_NINETEEN, "Moderately severe depression");
		dprnSvrty.put(MntlHlthEvlnCnst.TWENTY_AND_ABOVE, "Severe depression");

		MntlHlthEvlnDataOld();
		initScore();
	}

	public Integer getScore(String ans) {

		return ansScors.get(ans.toLowerCase());

	}

	public String getDprnSvrty(int scor) {

		String rtrnTyp = "";

		if (scor >= 1 && scor <= 4) {
			rtrnTyp = dprnSvrty.get(MntlHlthEvlnCnst.ONE_TO_FOUR);

		} else if (scor >= 5 && scor <= 9) {
			rtrnTyp = dprnSvrty.get(MntlHlthEvlnCnst.FIVE_TO_NINE);

		} else if (scor >= 10 && scor <= 14) {
			rtrnTyp = dprnSvrty.get(MntlHlthEvlnCnst.TEN_TO_FOURTEEN);

		} else if (scor >= 15 && scor <= 19) {
			rtrnTyp = dprnSvrty.get(MntlHlthEvlnCnst.FIFTEEN_TO_NINETEEN);

		} else if (scor >= 20) {
			rtrnTyp = dprnSvrty.get(MntlHlthEvlnCnst.TWENTY_AND_ABOVE);

		} else {
			rtrnTyp = dprnSvrty.get(MntlHlthEvlnCnst.NO_DEPRESSION);
		}

		return rtrnTyp;

	}

	public String getDprnSvrtyColor(int scor) {

		String rtrnTyp = "";

		if (scor >= 1 && scor <= 4) {
			rtrnTyp = "#10AE0B";

		} else if (scor >= 5 && scor <= 9) {
			rtrnTyp = "#9ACD32";

		} else if (scor >= 10 && scor <= 14) {
			rtrnTyp = "#CDC231";

		} else if (scor >= 15 && scor <= 19) {
			rtrnTyp = "#CD7A31";

		} else if (scor >= 20) {
			rtrnTyp = "#CD4B31";

		}

		return rtrnTyp;

	}

//	@SuppressWarnings("unused")
	private void MntlHlthEvlnDataOld() {

		// initScore();
		//
		ppldCases = new HashMap<String, Map<String, String>>();

		/****
		 * Question 1 Start
		 */
		Map<String, String> ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I never feeling little interest in doing things.:)");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "I am feeling little interest in doing things several days.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"I feel less interested or pleasure in doing things, more than half of the days.:(");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"I fill less interest in doing things every day.I don't know what should I do.:(");

//		ppldCases.put("Little interest or pleasure in doing things", ppldcase);
		ppldCases.put("question1", ppldcase);

		/****
		 * Question 2 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I never feeling depressed or hopeless.:).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "I am down, depressed, hopeless several days.:(.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"I am feeling down, depressed, or hopeless, more than half of the days.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"I felt down, depressed, or hopeless each and every day.I don't know what. I should do something.");

//		ppldCases.put("Feeling down, depressed, or hopeless", ppldcase);
		ppldCases.put("question2", ppldcase);

		/****
		 * Question 3 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I always sleep well. never face any problem.:).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "Several days I am facing trouble to falling asleep.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half of the days, I am facing trouble to falling or staying asleep, or sleeping too much. I don't know why.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"Every day I face trouble falling or staying asleep, or sleeping too much. I don't know why.:(.");

//		ppldCases.put("", ppldcase);
		ppldCases.put("question3", ppldcase);

		/****
		 * Question 4 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I never Feeling tired or having little energy. :).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "Several day I am tired and little energy.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half the days I am Feeling tired or having little energy.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"Every day I am Feeling tired or having little energy.I could not understand.");

//		ppldCases.put("Feeling tired or having little energy", ppldcase);

		ppldCases.put("question4", ppldcase);

		/****
		 * Question 5 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I never have poor appetite or overeating.:).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "Several days I am suffering from poor appetite, overeating.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half the days, I am suffering from Poor appetite or overeating.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"Every day I am suffering from poor appetite or overeating. I don't know why, but I feel I am enjoying it.");

//		ppldCases.put("Poor appetite or overeating", ppldcase);
		ppldCases.put("question5", ppldcase);

		/****
		 * Question 6 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(),
				"I never Feeling bad about myself - and never felt that I am a failure, or I never make myself or my family down.I am always happy:)");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(),
				"Several days I feel bad about my self, also thinking that I am a failure, and I make myself and my family down.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half the days I feel bad about my self, or thinking that I am a failure, and I make myself and my family down.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"Every day I feel bad about my self, also thinking that I am a failure, and I make myself and my family down. I feel that I am enjoying it, don't know why.");

//		ppldCases.put("Feeling bad about yourself - or that you are a failure or have let yourself or your family down", ppldcase);

		ppldCases.put("question6", ppldcase);

		/****
		 * Question 7 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I never suffer from a lack of concentration. :).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "Several days, I am lack of concentration.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half the days, I am suffering from a lack of concentration.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(), "Every day I am suffering from lack of concentration.");

//		ppldCases.put("Trouble concentrating on things, such as reading the newspaper or watching television", ppldcase);
		ppldCases.put("question7", ppldcase);

		/****
		 * Question 8 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(),
				"I never move or speak so slowly, that other people could have noticed. I always enjoy speaking.:).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "Several days I move, speak so slowly that other people notice.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half the days, I move or speak so slowly that other people could have noticed.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(),
				"Every day I move or speak slowly that other people could have noticed.");

//		ppldCases.put("Moving or speaking so slowly that other people could have noticed", ppldcase);
		ppldCases.put("question8", ppldcase);

		/****
		 * Question 9 Start
		 */
		ppldcase = new HashMap<String, String>();

		ppldcase.put(Answers.NOT_AT_ALL.getValue(), "I never hurt myself.:).");
		ppldcase.put(Answers.SEVERAL_DAYS.getValue(), "I should be dead.");
		ppldcase.put(Answers.MORE_THAN_HALF_THE_DAYS.getValue(),
				"More than half of the days, I thoughts that I could be better off dead or hurt myself.");
		ppldcase.put(Answers.NEARLY_EVERY_DAY.getValue(), "Every day, I thoughts I should kill myself.");

//		ppldCases.put("Thoughts that you would be better off dead, or of hurting yourself", ppldcase);
		ppldCases.put("question9", ppldcase);

	}

	@SuppressWarnings("unused")
	private void initScore1() {

		ibmCloudTonesScore.put("Joy", 1);
		ibmCloudTonesScore.put("Sadness", -2);
		ibmCloudTonesScore.put("Anger", -1);
		ibmCloudTonesScore.put("Fear", -3);

		ibmCloudTonesScore.put("Confident", 3);
		ibmCloudTonesScore.put("Tentative", 2);
		ibmCloudTonesScore.put("Analytical", 1);

	}

	private void initScore() {

		ibmCloudTonesScore.put("joy", 0);
		ibmCloudTonesScore.put("confident", 1);
		ibmCloudTonesScore.put("sadness", 1);
		ibmCloudTonesScore.put("tentative", 1);
		ibmCloudTonesScore.put("analytical", 1);

	}

	public String getPpldCases(String key1, String key2) {

		Map<String, String> statementMap = ppldCases.get(key1.toLowerCase());
//		System.out.println(statementMap);
		String string = null;
		if (statementMap != null && !statementMap.isEmpty()) {
			string = statementMap.get(key2.toLowerCase());
		}
//		System.out.println(string);

		return string;

	}

	public Integer getIbmCloudTonesScore(String key) {

		if (ibmCloudTonesScore.containsKey(key)) {

			return ibmCloudTonesScore.get(key);
		}
		return 0;
	}
}
