package com.gepardec.wdg.application.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class ApplicationMailer {

    @ConfigProperty(name = "ApplicationMailer.Default")
    private String defaultMailAddress;

    @Inject
    private Mailer mailer;

    @Path("/sendDefaultMail")
    @GET
    public Response sendMailToDefaultMailAddress(String subject, String text) {
        mailer.send(Mail.withText(defaultMailAddress, subject, text));
        return Response.accepted().build();
    }

}
