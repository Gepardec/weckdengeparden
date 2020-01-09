package com.gepardec.wdg.challenge;

import com.gepardec.wdg.application.configuration.PersonioConfiguration;
import com.gepardec.wdg.challenge.model.*;
import com.gepardec.wdg.client.personio.ApplicationForm;
import com.gepardec.wdg.client.personio.RecruitingApi;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestScoped
@Path("/challenge")
public class ChallengesResource {

    private static final String WRONG_ANSWER = "Sorry, die Antwort ist falsch. Denk' nochmal in Ruhe darüber nach und versuch es noch einmal.";
    private static final String CORRECT_ANSWER = "Danke! Du hast den Geparden in dir erweckt und wir melden uns in den nächsten Tagen bei dir! Lg, Michael Sollberger";

    @Inject
    Logger log;

    @Inject
    @RestClient
    RecruitingApi recruitingApi;

    @Inject
    PersonioConfiguration personioConfiguration;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        final List<Challenge> challenges = Stream.of(Challenges.values())
                .sorted(Comparator.comparing(Challenges::getId))
                .map((challenge) -> Challenge.of(challenge.getId(), challenge.getQuestion()))
                .collect(Collectors.toList());
        return Response.ok(challenges).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response byId(@PathParam("id") @Min(value = 1) final Integer id) {
        final Challenges challenge = getChallengeForId(id);
        if (challenge == null) {
            return buildChallengeNotFoundResponse(id);
        }
        log.info("Provided Challenge for id '{}'", id);

        return Response.ok(Challenge.of(challenge.getId(), challenge.getQuestion())).build();
    }

    @POST
    @Path("/{id}/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response answer(@PathParam("id") @Min(value = 1, message = "{AnswerModel.id.min}") Integer id,
            @NotNull(message = "{AnswerModel.notNull}") @Valid final Answer answer) {
        final Challenges challenge = getChallengeForId(id);
        if (challenge == null) {
            return buildChallengeNotFoundResponse(id);
        }
        final boolean correctAnswer = challenge.getAnswer().trim().equalsIgnoreCase(answer.getAnswer().trim());
        if (!correctAnswer) {
            log.info("Wrong answer provided. challengeId={}, answer={}", challenge.getId(), answer.getAnswer());
            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(BaseResponse.error(WRONG_ANSWER)).build();
        }

        log.info("Correct answer provided. challengeId={}", challenge.getId());
        final ApplicationForm applicationForm = ApplicationFormTranslator.answerToApplicationForm(personioConfiguration, answer);
        final String personioResponse = recruitingApi.createApplicant(applicationForm);
        log.info("ApplicationForm submitted. {}", personioResponse);

        return Response.ok(BaseResponse.success(CORRECT_ANSWER)).build();
    }

    private Response buildChallengeNotFoundResponse(final Integer id) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(BaseResponse.error(String.format("No challenge found for provided id=%s", id)))
                .build();
    }

    private Challenges getChallengeForId(final int id) {
        return Challenges.forId(id).orElse(null);
    }
}
