package com.gepardec.wdg.challenge;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@Path("/challenge")
public interface ChallengesAPI {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getChallenge(@PathParam("id") final int id);

    @POST
    @Path("{id}/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<Response> answerChallenge (final String answerModelString);
}
