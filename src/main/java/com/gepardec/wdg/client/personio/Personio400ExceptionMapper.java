package com.gepardec.wdg.client.personio;

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
        return status == 400;
    }

    private String readResponseErrorMessage(Response response) {
        try {
            final PeronioErrorResponse errorResponse = response.readEntity(PeronioErrorResponse.class);
            return errorResponse.getError();
        } catch (Exception e) {
            return readResponseAsString(response);
        }
    }

    private String readResponseAsString(Response response) {
        try {
            return response.readEntity(String.class);
        } catch (Exception e) {
            return String.format("Response body is neither readable as 'PersonioErrorResponse' nor as 'String'. Error: %s", e.getMessage());
        }
    }
}
