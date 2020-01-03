package com.gepardec.wdg.application.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PersonioConfiguration {

    @ConfigProperty(name = "personio.company_id")
    String companyId;

    @ConfigProperty(name = "personio.access_token")
    String accesstoken;

    @ConfigProperty(name = "personio.default.job_position_id")
    String jobPositionId;

    public String getCompanyId() {
        return companyId;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getJobPositionId() {
        return jobPositionId;
    }
}
