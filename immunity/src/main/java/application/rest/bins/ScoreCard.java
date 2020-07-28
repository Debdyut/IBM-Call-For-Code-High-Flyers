package application.rest.bins;

import java.util.List;

public class ScoreCard {
	private String key;
	private List<String> value;
	private List<String> score;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	public List<String> getScore() {
		return score;
	}

	public void setScore(List<String> score) {
		this.score = score;
	}

}
