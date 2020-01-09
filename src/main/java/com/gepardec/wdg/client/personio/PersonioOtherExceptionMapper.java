package com.gepardec.wdg.client.personio;

import com.gepardec.wdg.challenge.exception.PersonioClientException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class PersonioOtherExceptionMapper implements ResponseExceptionMapper<PersonioClientException> {

    @Override
    public PersonioClientException toThrowable(Response response) {
        return PersonioClientException.of(response.getStatus(), readResponseAsString(response));
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status >= 422;
    }

    private String readResponseAsString(Response response) {
        try {
            return response.readEntity(String.class);
        } catch (Exception e) {
            return String.format("Response body could not be read. Error: %s", e.getMessage());
        }
    }
}
