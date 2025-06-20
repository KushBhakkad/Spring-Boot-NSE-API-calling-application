package com.kushbhakkad.nseapicall.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnouncementDto {

    @JsonProperty("sm_name")
    private String smName;

    @JsonProperty("attchmntText")
    private String attchmntText;

    // Getters and Setters
    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getAttchmntText() {
        return attchmntText;
    }

    public void setAttchmntText(String attchmntText) {
        this.attchmntText = attchmntText;
    }
}