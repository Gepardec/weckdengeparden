package com.gepardec.wdg.challenge.model;

import com.gepardec.wdg.challenge.validation.URLValid;

import javax.validation.constraints.NotEmpty;

@URLValid
public class AnswerChallenge2 extends Answer {

    @NotEmpty(message = "{AnswerModel.url.notEmpty}")
    private String gitHubPullRequestUrl;


    public AnswerChallenge2() {
    }

    public String getGitHubPullRequestUrl() {
        return gitHubPullRequestUrl;
    }

    public void setGitHubPullRequestUrl(String gitHubPullRequestUrl) {
        this.gitHubPullRequestUrl = gitHubPullRequestUrl;
    }
}
