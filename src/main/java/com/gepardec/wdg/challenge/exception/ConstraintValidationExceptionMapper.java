package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.challenge.model.BadRequestResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@ApplicationScoped
@Provider
public class ConstraintValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Inject
    Logger log;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        log.info("Constraint violation(s) on resource: '{}'", uriInfo.getPath());
        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(BadRequestResponse.invalid(exception.getConstraintViolations())).build();
    }
}
