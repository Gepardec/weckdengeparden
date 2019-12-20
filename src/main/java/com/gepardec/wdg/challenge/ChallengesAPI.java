package com.gepardec.wdg.challenge;

import com.gepardec.wdg.challenge.model.AnswerModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/challenge")
public interface ChallengesAPI {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getChallenges();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getChallenge(@PathParam("id") Integer id);

    @POST
    @Path("{id}/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response answerChallenge(@PathParam("id") Integer id, AnswerModel answerModelString);
}
