
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
    "document_tone",
    "sentences_tone"
})
public class ToneAnalyzeVo {

    @JsonProperty("document_tone")
    private DocumentToneVo documentTone;
    @JsonProperty("sentences_tone")
    private List<SentencesToneVo> sentencesTone = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("document_tone")
    public DocumentToneVo getDocumentTone() {
        return documentTone;
    }

    @JsonProperty("document_tone")
    public void setDocumentTone(DocumentToneVo documentTone) {
        this.documentTone = documentTone;
    }

    @JsonProperty("sentences_tone")
    public List<SentencesToneVo> getSentencesTone() {
        return sentencesTone;
    }

    @JsonProperty("sentences_tone")
    public void setSentencesTone(List<SentencesToneVo> sentencesTone) {
        this.sentencesTone = sentencesTone;
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
		return "ToneAnalyzeVo [documentTone=" + documentTone + ", sentencesTone=" + sentencesTone
				+ ", additionalProperties=" + additionalProperties + "]";
	}

    

}
