package com.gepardec.wdg.challenge.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PersonioConfiguration {

    @ConfigProperty(name = "personio.company.id")
    private String companyId;

    @ConfigProperty(name = "perosnio.access.token")
    private String accesstoken;

    public PersonioConfiguration() {
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getAccesstoken() {
        return accesstoken;
    }
}
