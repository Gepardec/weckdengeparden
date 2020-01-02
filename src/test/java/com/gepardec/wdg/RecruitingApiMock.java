package com.gepardec.wdg;

import com.gepardec.wdg.client.personio.ApplicationForm;
import com.gepardec.wdg.client.personio.RecruitingApi;
import com.gepardec.wdg.client.personio.PersonioResponse;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@RestClient
public class RecruitingApiMock implements RecruitingApi {

    @Override
    public PersonioResponse createApplicant(ApplicationForm applicationForm) {
        return new PersonioResponse(true, "Mock says success");
    }
}
