package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.application.configuration.Consts;
import com.gepardec.wdg.application.exception.ExceptionHandledEvent;
import com.gepardec.wdg.application.mail.ApplicationMailer;
import com.gepardec.wdg.challenge.model.BaseResponse;
import com.gepardec.wdg.client.personio.error.PersonioError;
import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;

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

    private static final org.jboss.logging.Logger log = Logger.getLogger(OtherExceptionMapper.class);

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
        explicitPersonioExceptionLogging(exception);
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
            log.info(String.format(Consts.ERROR_WDG_SUP_TECH + " Job Error Undefined: status='%s', originalMessage='%s', applicationError='%s'", exception.status, exception.originalMessage, exception.applicationError));
            mailer.sendMailToDefaultMailAddress(Consts.MAIL_SUBJECT_WDG_SUP_TECH, "status: " + exception.status + " OriginalMessage: " + exception.originalMessage + " applicationError: " + exception.applicationError);
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.SC_BAD_REQUEST;
    }

    private void explicitPersonioExceptionLogging(final PersonioClientException exception) {
        if (PersonioError.ALREADY_APPLIED.equals(exception.applicationError)) {
            log.info(String.format(Consts.WARN_004 + " Already Applied: status='%s', originalMessage='%s', applicationError='%s'", exception.status, exception.originalMessage, exception.applicationError));
        } else if(PersonioError.JOBID_NOT_FOUND.equals(exception.applicationError) || PersonioError.JOB_NOT_PUBLISHED.equals(exception.applicationError) || PersonioError.JOBID_EMPTY.equals(exception.applicationError) || PersonioError.JOBID_NOT_VALID.equals(exception.applicationError)) {
            log.info(String.format(Consts.WARN_003 + " Job Error: status='%s', originalMessage='%s', applicationError='%s'", exception.status, exception.originalMessage, exception.applicationError));
        }
    }
}
