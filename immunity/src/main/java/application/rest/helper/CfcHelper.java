package application.rest.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import application.rest.bins.Immunity;
import application.rest.bins.ImmunityCheck;
import application.rest.bins.ScoreCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component("cfcHelper")
public class CfcHelper {

	public Integer getScore(List<Immunity> questions) {
		try {
			if (questions == null) {
				questions = getHardcodedData();
			}
			String score = getfile("scoreCard.txt");
			Type founderListType = new TypeToken<ArrayList<ScoreCard>>() {
			}.getType();
			Gson gson = new Gson();
			List<ScoreCard> scoreCards = gson.fromJson(score, founderListType);
			final Map<String, ScoreCard> scoreMap = new HashMap<>();
			scoreCards.forEach(scoreCard -> {
				scoreMap.put(scoreCard.getKey(), scoreCard);
			});

			Integer totalScore = 0;
			for (Immunity question : questions) {
				String immunityQ = question.getQuestion();
				String ans = question.getAnswer();
				if (scoreMap.containsKey(immunityQ)) {
					Integer index = 0;
					ScoreCard scoreCard = scoreMap.get(immunityQ);
					for (; index < scoreCard.getValue().size(); index++) {
						if (ans.contains(scoreCard.getValue().get(index))) {
							totalScore += Integer.parseInt(scoreCard.getScore().get(index));
						}
					}
				}

			}
			if (totalScore < 50) {
				Random r = new Random();
				int low = 45;
				int high = 60;
				totalScore = r.nextInt(high - low) + low;
			}
			if (totalScore < 80 && totalScore > 50) {
				Random r = new Random();
				int low = 65;
				int high = 85;
				totalScore = r.nextInt(high - low) + low;
			}
			if (totalScore > 80) {
				Random r = new Random();
				int low = 85;
				int high = 99;
				totalScore = r.nextInt(high - low) + low;
			}

			return totalScore;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Integer getScoreNew(ImmunityCheck questions) {
		try {

			String score = getfile("scoreCard2.txt");
			Type founderListType = new TypeToken<ArrayList<ScoreCard>>() {
			}.getType();
			Gson gson = new Gson();
			List<ScoreCard> scoreCards = gson.fromJson(score, founderListType);
			final Map<String, ScoreCard> scoreMap = new HashMap<>();
			scoreCards.forEach(scoreCard -> {
				scoreMap.put(scoreCard.getKey(), scoreCard);
			});

			Integer totalScore = 0;

			for (int quation = 1; quation <= 14; quation++) {
				String qs = "q".concat(String.valueOf(quation));
				if (scoreMap.containsKey(qs)) {
					Integer index = 0;
					ScoreCard scoreCard = scoreMap.get(qs);
					String methodName = "get".concat(qs.toUpperCase());
					Method method = ImmunityCheck.class.getMethod(methodName);
					method.setAccessible(true);
					Object object = method.invoke(questions);
					String value = object.toString();
					for (; index < scoreCard.getValue().size(); index++) {
						if (value.equalsIgnoreCase(scoreCard.getValue().get(index))) {
							totalScore += Integer.parseInt(scoreCard.getScore().get(index));
						}
					}
				}
			}
			System.out.println(totalScore);
			if (totalScore < 50) {
				Random r = new Random();
				int low = 45;
				int high = 60;
				totalScore = r.nextInt(high - low) + low;
			}
			else if (totalScore < 80 && totalScore > 50) {
				Random r = new Random();
				int low = 65;
				int high = 85;
				totalScore = r.nextInt(high - low) + low;
			}
			else if (totalScore > 80) {
				Random r = new Random();
				int low = 85;
				int high = 99;
				totalScore = r.nextInt(high - low) + low;
			}

			return totalScore;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private List<Immunity> getHardcodedData() throws Exception {
		List<Immunity> questions;
		String input = getfile("fetchScore.txt");
		Type founderListType = new TypeToken<ArrayList<Immunity>>() {
		}.getType();
		Gson gson = new Gson();
		questions = gson.fromJson(input, founderListType);
		return questions;
	}

	private String getfile(String name) throws Exception {
		InputStream inputStream = null;
		try {
			Resource resource = new ClassPathResource(name);
			inputStream = resource.getInputStream();
			StringBuilder input = new StringBuilder();
			Reader reader = new BufferedReader(
					new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
			int c = 0;
			while ((c = reader.read()) != -1) {
				input.append((char) c);
			}
			return input.toString();

		} catch (IOException ex) {
			throw new Exception(ex);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
