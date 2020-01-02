package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.challenge.model.BaseResponse;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@ApplicationScoped
@Provider
public class PersonioClientExceptionMapper implements ExceptionMapper<PersonioClientException> {

    @Inject
    Logger log;

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(PersonioClientException exception) {
        log.error(String.format("Call on resource '%s' failed because personio rest call failed", uriInfo.getPath()), exception);
        return Response.serverError().entity(BaseResponse.error("Sorry, a call to a backend service failed. Please try again")).build();
    }
}
