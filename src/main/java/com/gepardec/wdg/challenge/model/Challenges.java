package com.gepardec.wdg.challenge.model;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Challenges {
    CHALLENGE2(2, "Broken Project: Deine Aufgabe besteht darin, das Projekt, " +
            "welches unser Praktikant leider nicht so ganz hinbekommen hat, " +
            "wieder zum Laufen zu bringen. Hol dir das beste Broken Project ever jetzt unter: https://github.com/Gepardec/weckdengeparden", "https://github.com/Gepardec/weckdengeparden/pull/"),
    CHALLENGE1(1, "1 Notebook und eine Maus kosten zusammen 1100 Euro. Der Notebook kostet 1000 Euro mehr als die Maus. Wieviel kostet die Maus?", "50"),
    UNKNOWN(0, "", "");

    private int id;
    private String question;
    private String answer;

    private static final Map<Integer, Challenges> challenges = Stream.of(Challenges.values()).collect(Collectors.toMap(Challenges::getId, Function.identity()));

    Challenges(final int id, final String question, final String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public static Optional<Challenges> forId(final int id) {
        return Optional.ofNullable(challenges.get(id));
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
