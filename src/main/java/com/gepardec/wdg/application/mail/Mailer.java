package com.gepardec.wdg.application.mail;

import io.quarkus.mailer.reactive.ReactiveMailer;

import javax.inject.Inject;

public class Mailer {

    @Inject
    Mailer mailer;

    @Inject
    ReactiveMailer reactiveMailer;
}
