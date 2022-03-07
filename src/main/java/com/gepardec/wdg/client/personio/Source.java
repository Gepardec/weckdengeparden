package com.gepardec.wdg.client.personio;

public enum Source {

    GOOGLE("Google", 118666),
    FACEBOOK("Facebook", 118667),
    INSTAGRAM("Instagram", 118668),
    LINKEDIN("LinkiedIn", 58369),
    XING("Xing", 118682),
    KUNUNU("Kununu", 118669),
    STACKOVERFLOW("Stackoverflow", 118670),
    KARRIEREAT("karriere.at", 83711),
    EMPFEHLUNG("Empfehlung von (Bitte otherSource ausfüllen)", 118683),
    MESSEN("Messen (Bitte otherSource ausfüllen)", 118671),
    MEETUPS("Meetups (Bitte otherSource ausfüllen)", 118672),
    SONSTIGES("Sonstige (Bitte otherSource ausfüllen)", 118673),
    ERROR("Error", 0);

    final String text;

    final int id;

    public final String idStr;

    Source(String text, int id) {
        this.text = text;
        this.id = id;
        this.idStr = String.valueOf(id);
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }
}
