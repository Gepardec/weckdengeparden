package com.gepardec.wdg.client.personio.error;

import com.gepardec.wdg.challenge.exception.PersonioClientException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class Personio400ExceptionMapper implements ResponseExceptionMapper<PersonioClientException> {

    @Override
    public PersonioClientException toThrowable(Response response) {
        String errorMessage = readResponseErrorMessage(response);

        return PersonioClientException.of(response.getStatus(), errorMessage);
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status == 400 || status >= 422;
    }

    private String readResponseErrorMessage(Response response) {
        try {
            final String errorResponse = response.readEntity(String.class);
            return errorResponse.replace("\"", "");
        } catch (Exception e) {
            return String.format("Response body is not readable as 'String'. Error: %s", e.getMessage());
        }
    }

}
