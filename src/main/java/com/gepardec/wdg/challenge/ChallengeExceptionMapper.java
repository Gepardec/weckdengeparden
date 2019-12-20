package com.gepardec.wdg.challenge;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class ChallengeExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            log.info(String.format("WebApplicationException occurred. Error: %s", exception.getMessage()));
            return ((WebApplicationException) exception).getResponse();
        }
        log.error("An unhandled exception occurred", exception);
        return Response.serverError().entity("Sorry, an unexpected error occurred on our site.").build();
    }
}
