package com.gepardec.wdg;

import com.gepardec.wdg.client.personio.ApplicationForm;
import com.gepardec.wdg.client.personio.RecruitingApi;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@RestClient
public class RecruitingApiMock implements RecruitingApi {

    @Override
    public String createApplicant(ApplicationForm applicationForm) {
        return "Mock says yeah";
    }
}
