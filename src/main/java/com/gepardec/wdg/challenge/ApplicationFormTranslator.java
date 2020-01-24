package com.gepardec.wdg.challenge;

import com.gepardec.wdg.application.configuration.PersonioConfiguration;
import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.client.personio.ApplicationForm;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ApplicationFormTranslator {

    private ApplicationFormTranslator() {
    }

    static ApplicationForm answerToApplicationForm(final PersonioConfiguration config, final Answer model) {
        final ApplicationForm form = new ApplicationForm();
        form.setCompanyId(config.getCompanyId());
        form.setAccessToken(config.getAccesstoken());

        form.setJobPositionId(model.getJobId());
        form.setTitle(model.getTitle());
        form.setFirstName(model.getFirstName());
        form.setLastName(model.getLastName());
        form.setEmail(model.getEmail());
        form.setPhone(model.getPhone());
        form.setMessage(model.getMessageToGepardec());
        form.setLinkedInLink(model.getLinkedInLink());
        form.setXingLink(model.getXingLink());
        form.setEmpfehlung(model.getOtherSource());
        form.setMessage(model.getMessageToGepardec());
        if (model.getSource() != null) {
            form.setRecrutingChannel(model.getSource().idStr);
        }
        if (model.getCv() != null) {
            form.setDocument(new ByteArrayInputStream(Base64.getDecoder().decode(model.getCv())));
        }

        return form;
    }
}
