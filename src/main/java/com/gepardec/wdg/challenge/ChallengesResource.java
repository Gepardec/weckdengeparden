package com.gepardec.wdg.challenge;

import com.gepardec.wdg.application.configuration.LoggerConsts;
import com.gepardec.wdg.application.configuration.PersonioConfiguration;
import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.challenge.model.BaseResponse;
import com.gepardec.wdg.challenge.model.Challenge;
import com.gepardec.wdg.challenge.model.Challenges;
import com.gepardec.wdg.client.personio.ApplicationForm;
import com.gepardec.wdg.client.personio.RecruitingApi;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestScoped
@Path("/challenge")
public class ChallengesResource {

    private static final org.jboss.logging.Logger log = Logger.getLogger(ChallengesResource.class);

    private static final String WRONG_ANSWER = "Sorry, die Antwort ist falsch. Denk' nochmal in Ruhe darüber nach und versuch es noch einmal.";
    private static final String CORRECT_ANSWER = "Danke! Du hast den Geparden in dir erweckt und wir melden uns in den nächsten Tagen bei dir! Lg, Michael Sollberger";

    @Inject
    @RestClient
    RecruitingApi recruitingApi;

    @Inject
    PersonioConfiguration personioConfiguration;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        log.info(LoggerConsts.INFO_001+" Fetching all challenges!");
        final List<Challenge> challenges = Stream.of(Challenges.values())
                .sorted(Comparator.comparing(Challenges::getId))
                .map(challenge -> Challenge.of(challenge.getId(), challenge.getQuestion()))
                .collect(Collectors.toList());
        return Response.ok(challenges).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response byId(@PathParam("id") @Min(value = 1) final Integer id) {
        log.info(String.format(LoggerConsts.INFO_002+" Provided Challenge for id='%s'", id));

        final Challenges challenge = getChallengeForId(id);
        if (challenge == null) {
            return buildChallengeNotFoundResponse(id);
        }
        return Response.ok(Challenge.of(challenge.getId(), challenge.getQuestion())).build();
    }

    @POST
    @Path("/{id}/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response answer(@PathParam("id") @Min(value = 1, message = "{AnswerModel.id.min}") Integer id,
            @NotNull(message = "{AnswerModel.notNull}") @Valid final Answer answer) {
        log.info(String.format(LoggerConsts.INFO_003+" Provided Answer for challengeId='%s' with jobId='%s'", id, answer.getJobId()));

        final Challenges challenge = getChallengeForId(id);
        if (challenge == null) {
            log.warn(String.format(LoggerConsts.WARN_001+" Challenge with id='%s' with jobId='%s' not found!", id, answer.getJobId()));
            return buildChallengeNotFoundResponse(id);
        }
        final boolean correctAnswer = challenge.getAnswer().trim().equalsIgnoreCase(answer.getAnswer().trim());
        if (!correctAnswer) {
            log.info(String.format(LoggerConsts.WARN_002+" Wrong answer provided. challengeId='%s', answer='%s', jobId='%s'", challenge.getId(), answer.getAnswer(), answer.getJobId()));
            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(BaseResponse.error(WRONG_ANSWER)).build();
        }

        log.info(String.format(LoggerConsts.INFO_004+" Correct answer `provided`. challengeId='%s' jobId='%s'", challenge.getId(), answer.getJobId()));
        final ApplicationForm applicationForm = ApplicationFormTranslator.answerToApplicationForm(personioConfiguration, answer);
        final String personioResponse = recruitingApi.createApplicant(applicationForm);
        log.info(String.format(LoggerConsts.INFO_005+" ApplicationForm for challengeId='%s', jobId='%s' submitted. '%s'", challenge.getId(), answer.getJobId(), personioResponse));

        return Response.ok(BaseResponse.success(CORRECT_ANSWER)).build();
    }

    private Response buildChallengeNotFoundResponse(final Integer id) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(BaseResponse.error(String.format("No challenge found for provided id='%s'", id)))
                .build();
    }

    private Challenges getChallengeForId(final int id) {
        return Challenges.forId(id).orElse(null);
    }
}
