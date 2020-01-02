package com.gepardec.wdg.challenge;

import com.gepardec.wdg.challenge.configuration.PersonioConfiguration;
import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.client.personio.ApplicationForm;

public class ApplicationFormTranslator {

    private ApplicationFormTranslator() {
    }

    static ApplicationForm answerToApplicationForm(final PersonioConfiguration config, final Answer model) {
        final ApplicationForm form = new ApplicationForm();
        form.setCompanyId(config.getCompanyId());
        form.setAccessToken(config.getAccesstoken());
        form.setTitle(model.getTitle());
        form.setFirstName(model.getFirstName());
        form.setLastName(model.getLastName());
        form.setEmail(model.getEmail());
        form.setPhone(model.getPhone());
        form.setMessage(model.getMessageToGepardec());
        form.setLinkedInLink(model.getLinkedInLink());
        form.setXingLink(model.getXingLink());
        form.setMessage(model.getMessageToGepardec());
        if (model.getSource() != null) {
            form.setRecrutingChannel(model.getSource().getId());
        }
        form.setEmpfehlung(model.getOtherSource());
        form.setDocuments(null);

        return form;
    }
}
