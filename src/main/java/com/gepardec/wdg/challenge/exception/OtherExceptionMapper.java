package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.application.configuration.Consts;
import com.gepardec.wdg.application.exception.ExceptionHandledEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
@ApplicationScoped
public class OtherExceptionMapper implements ExceptionMapper<Exception> {

    private static final org.jboss.logging.Logger log = Logger.getLogger(OtherExceptionMapper.class);

    @Inject
    Event<ExceptionHandledEvent> handledEvent;

    @Context
    UriInfo uriInfo;

    @Context
    HttpServletRequest request;

       @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            log.info(String.format("WebApplicationException occurred. Error: %s", exception.getMessage()));
            return ((WebApplicationException) exception).getResponse();
        }
        log.error(String.format("Call on resource '%s' produced an error", uriInfo.getPath()), exception);
        Response response = Response.serverError().entity("Hallelujah! In unser Service hat sich ein unerwarteter Fehler eingeschlichen. Unser DevOps-Team wurde soeben verständigt und löst das Problem. Wir informieren dich, sobald unser Service wieder verfügbar ist.Weitere Infos zum Support findest du hier >> <https://github.com/Gepardec/weckdengeparden/wiki>").build();
        handledEvent.fire(ExceptionHandledEvent.Builder.newBuilder(exception).withIsError(true).build());
        log.error(String.format(Consts.ERROR_WDG_SUP_TECH + " Technical Error: message='%s'", exception.getMessage()));
        return response;
    }

    private String getExceptionStackTraceString(Exception exception) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter= new PrintWriter(writer);
        exception.printStackTrace(printWriter);
        return writer.toString();
    }
}
