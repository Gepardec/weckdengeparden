package com.gepardec.wdg.client.personio;

import com.gepardec.wdg.challenge.exception.PersonioClientException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;

public class PersonioResponseExceptionMapper implements ResponseExceptionMapper<PersonioClientException> {

    @Override
    public PersonioClientException toThrowable(Response response) {
        return PersonioClientException.of(response.getStatus(), readResponseEntity(response));
    }

    private String readResponseEntity(Response response) {
        try {
            return response.readEntity(String.class);
        } catch (Exception e) {
            return String.format("Response content could not be read. Error: %s", e.getMessage());
        }
    }
}
