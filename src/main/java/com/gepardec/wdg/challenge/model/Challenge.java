package com.gepardec.wdg.challenge.model;

public class Challenge {

    private final int id;
    private final String question;

    public Challenge(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public static Challenge of(final int id, final String question) {
        return new Challenge(id, question);
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
