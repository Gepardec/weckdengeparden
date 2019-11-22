package com.gepardec.wdg.challenge.model;

public enum QuestionModel {
    CHALLENGE1(1, "1 Notebook und eine Maus kosten zusammen 1100 Euro. Der Notebook kostet 1000 Euro mehr als die Maus. Wieviel kostet die Maus?", "50"),
    UNKNOWN(0, "", "");

    private int id;
    private String question;
    private String answer;

    QuestionModel (final int id, final String question, final String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public int getId () {
        return id;
    }

    public String getQuestion () {
        return question;
    }

    public String getAnswer () {
        return answer;
    }
}
