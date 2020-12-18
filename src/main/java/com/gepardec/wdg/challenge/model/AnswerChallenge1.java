package com.gepardec.wdg.challenge.model;

import javax.validation.constraints.*;

public class AnswerChallenge1 extends Answer{

    @NotEmpty(message = "{AnswerModel.answer.notEmpty}")
    private String answer;

    public AnswerChallenge1() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
