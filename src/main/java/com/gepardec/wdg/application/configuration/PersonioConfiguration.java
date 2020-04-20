package com.gepardec.wdg.application.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PersonioConfiguration {

    @Inject
    @ConfigProperty(name = "personio.company_id")
    String companyId;

    @Inject
    @ConfigProperty(name = "personio.access_token")
    String accesstoken;

    public String getCompanyId() {
        return companyId;
    }

    public String getAccesstoken() {
        return accesstoken;
    }
}
