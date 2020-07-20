package com.gepardec.wdg.application.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;

import javax.inject.Inject;

public class ApplicationMailer {

    @Inject
    private Mailer mailer;

    @Inject
    private ReactiveMailer reactiveMailer;

    public void sendMail(String receiver, String subject, String text) {
        mailer.send(Mail.withText(receiver, subject, text));
    }
}
