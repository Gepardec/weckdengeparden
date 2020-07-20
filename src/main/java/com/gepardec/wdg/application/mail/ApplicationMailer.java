package com.gepardec.wdg.application.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;

public class ApplicationMailer {

    @ConfigProperty(name = "ApplicationMailer.Default")
    private String defaultMailAddress;

    @Inject
    private Mailer mailer;

    @Inject
    private ReactiveMailer reactiveMailer;

    public void sendMailToDefaultMailAddress(String subject, String text) {
        mailer.send(Mail.withText(defaultMailAddress, subject, text));
    }

    public void sendMail(String receiver, String subject, String text) {
        mailer.send(Mail.withText(receiver, subject, text));
    }
}
