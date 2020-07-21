package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.application.exception.ExceptionHandledEvent;
import com.gepardec.wdg.application.mail.ApplicationMailer;
import com.gepardec.wdg.challenge.model.BaseResponse;
import com.gepardec.wdg.client.personio.error.PersonioError;
import org.apache.http.HttpStatus;
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

    @Inject
    ApplicationMailer mailer;

    @Override
    public Response toResponse(PersonioClientException exception) {
        log.error(String.format("Call on resource '%s' failed because personio rest call failed", uriInfo.getPath()), exception);
        final Response response = Response.status(getHttpResponseStatusForPersonioError(exception))
                .entity(BaseResponse.error(exception.applicationError.clientMessage))
                .build();
        handledEvent.fire(ExceptionHandledEvent.Builder.newBuilder(exception)
                .withMessage(String.format("Response-Status: %d, Response-Message: %s, Personio-Error: %s, Client-Response: %s",
                        exception.status,
                        exception.originalMessage,
                        exception.applicationError.name(),
                        exception.applicationError.clientMessage))
                .withIsError(true)
                .build());
        return response;
    }

    private int getHttpResponseStatusForPersonioError(final PersonioClientException exception) {
        if (PersonioError.UNDEFINED.equals(exception.applicationError)) {
            mailer.sendMailToDefaultMailAddress("wdg-sup-tech", "status: " + exception.status + " OriginalMessage: " + exception.originalMessage + " stacktrace: " + exception.getMessage());
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.SC_BAD_REQUEST;
    }
}
