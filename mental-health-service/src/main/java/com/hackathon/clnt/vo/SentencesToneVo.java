
package com.hackathon.clnt.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sentence_id",
    "text",
    "tones"
})
public class SentencesToneVo {

    @JsonProperty("sentence_id")
    private Integer sentenceId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("tones")
    private List<ToneVo> tones = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sentence_id")
    public Integer getSentenceId() {
        return sentenceId;
    }

    @JsonProperty("sentence_id")
    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("tones")
    public List<ToneVo> getTones() {
        return tones;
    }

    @JsonProperty("tones")
    public void setTones(List<ToneVo> tones) {
        this.tones = tones;
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
		return "SentencesToneVo [sentenceId=" + sentenceId + ", text=" + text + ", tones=" + tones
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}
