package com.gepardec.wdg.challenge.model;

import com.gepardec.wdg.challenge.validation.URLValid;

import javax.validation.constraints.*;

@URLValid
public class AnswerChallenge2 extends Answer{

    @NotEmpty(message = "{AnswerModel.url.notEmpty}")
    private String url;

    public AnswerChallenge2() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
