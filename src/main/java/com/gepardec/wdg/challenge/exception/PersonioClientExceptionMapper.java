package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.application.exception.ExceptionHandledEvent;
import com.gepardec.wdg.challenge.model.BaseResponse;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

    @Inject
    Event<ExceptionHandledEvent> handledEvent;

    @Context
    UriInfo uriInfo;

    @Context
    HttpServletRequest request;

    @Override
    public Response toResponse(PersonioClientException exception) {
        log.error(String.format("Call on resource '%s' failed because personio rest call failed", uriInfo.getPath()), exception);
        final Response response = Response.serverError().entity(BaseResponse.error("Sorry, a call to a backend service failed. Please try again")).build();
        handledEvent.fire(ExceptionHandledEvent.Builder.newBuilder(exception)
                .withMessage("Personio rest client call failed")
                .withIsError(true)
                .build());
        return response;
    }
}
