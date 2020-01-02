package com.gepardec.wdg.challenge.exception;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class OtherExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    Logger log;

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            log.info(String.format("WebApplicationException occurred. Error: %s", exception.getMessage()));
            return ((WebApplicationException) exception).getResponse();
        }
        log.error(String.format("Call on resource '%s' produced an error", uriInfo.getPath()), exception);
        return Response.serverError().entity("Sorry, an unexpected error occurred on our site.").build();
    }
}
