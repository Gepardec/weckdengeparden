package com.gepardec.wdg.challenge.model;

import com.gepardec.wdg.challenge.validation.URLValid;

import javax.validation.constraints.NotEmpty;

@URLValid
public class AnswerChallenge3 extends Answer {

    @NotEmpty(message = "{AnswerModel.url.notEmpty}")
    private String url;

    public AnswerChallenge3() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
