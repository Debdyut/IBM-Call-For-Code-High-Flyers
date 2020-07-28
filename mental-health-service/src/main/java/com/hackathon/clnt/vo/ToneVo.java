
package com.hackathon.clnt.vo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "score",
    "tone_id",
    "tone_name"
})
public class ToneVo {

    @JsonProperty("score")
    private Double score;
    @JsonProperty("tone_id")
    private String toneId;
    @JsonProperty("tone_name")
    private String toneName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
    }

    @JsonProperty("tone_id")
    public String getToneId() {
        return toneId;
    }

    @JsonProperty("tone_id")
    public void setToneId(String toneId) {
        this.toneId = toneId;
    }

    @JsonProperty("tone_name")
    public String getToneName() {
        return toneName;
    }

    @JsonProperty("tone_name")
    public void setToneName(String toneName) {
        this.toneName = toneName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
	public String toString() {
		return "Tone [score=" + score + ", toneId=" + toneId + ", toneName=" + toneName + ", additionalProperties="
				+ additionalProperties + "]";
	}

}
