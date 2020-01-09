package com.gepardec.wdg.client.personio;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.annotation.RegisterProviders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormAnnotationWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Traced
@RegisterRestClient(configKey = "personio")
// We need to register manually otherwise won't work with mp-rest-client
@RegisterProviders({
        @RegisterProvider(Personio400ExceptionMapper.class),
        @RegisterProvider(PersonioOtherExceptionMapper.class),
        @RegisterProvider(MultipartFormAnnotationWriter.class)
})
@Path("/recruiting")
public interface RecruitingApi {

    @Path("/applicant")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    String createApplicant(@MultipartForm ApplicationForm applicationForm);

}
