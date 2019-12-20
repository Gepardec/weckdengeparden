package com.gepardec.wdg.challenge;

import com.gepardec.wdg.challenge.configuration.PersonioConfiguration;
import com.gepardec.wdg.challenge.model.AnswerModel;
import com.gepardec.wdg.challenge.model.ChallengeResponse;
import com.gepardec.wdg.challenge.model.Challenges;
import com.gepardec.wdg.client.personio.ApplicationForm;
import com.gepardec.wdg.client.personio.RecruitingApi;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChallengesResource implements ChallengesAPI {

    private static final JsonObject JSON_GET_CHALLENGE_RESPONSE_ERROR = Json.createObjectBuilder()
            .add("error", "Eine challenge mit der id '%d' existiert nicht!").build();
    private static final JsonObject JSON_ANSWERT_CHALLENGE_RESPONSE_ERROR = Json.createObjectBuilder()
            .add("error", "%s").build();
    private static final String WRONG_ANSWER = "Sorry, die Antwort ist falsch. Denk' nochmal in Ruhe darüber nach und versuch es noch einmal.";
    private static final String CORRECT_ANSWER = "Danke! Du hast den Geparden in dir erweckt und wir melden uns in den nächsten Tagen bei dir! Lg, Michael Sollberger";
    private static final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    Logger log;

    @Inject
    @RestClient
    RecruitingApi recruitingApi;

    @Inject
    PersonioConfiguration personioConfiguration;

    @Inject
    Validator validator;

    @Override
    public Response getChallenges() {
        final List<JsonObject> challenges = Stream.of(Challenges.values())
                .sorted(Comparator.comparing(Challenges::getId))
                .map((challenge) -> Json.createObjectBuilder()
                        .add("challenge", challenge.getId())
                        .add("question", challenge.getQuestion())
                        .build())
                .collect(Collectors.toList());
        return Response.ok(challenges).build();
    }

    @Override
    public Response getChallenge(final Integer id) {
        final Challenges challenge = getChallengeForId(id);
        if (challenge == null) {
            return buildChallengeNotFoundResponse(id);
        }
        final JsonObject jsonResponseChallenge = Json.createObjectBuilder()
                .add("challenge", challenge.getId())
                .add("question", challenge.getQuestion())
                .build();
        log.info(String.format("Challenge '%d' accepted!", id));

        return Response.ok(jsonResponseChallenge.toString()).encoding(StandardCharsets.UTF_8.name()).build();
    }

    @Override
    public Response answerChallenge(Integer id, final AnswerModel answer) {
        final Challenges challenge = getChallengeForId(id);
        if (challenge == null) {
            return buildChallengeNotFoundResponse(id);
        }
        if (answer == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ChallengeResponse.error("No answer body provided"))
                    .build();
        }
        final Set<ConstraintViolation<AnswerModel>> violations = validator.validate(answer);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ChallengeResponse.invalid(violations))
                    .build();
        }
        final boolean checkAnswer = answer.getChallengeAnswer().trim().equals(challenge.getAnswer());
        if (checkAnswer) {
            log.info(CORRECT_ANSWER);
            return Response.ok(ChallengeResponse.success(CORRECT_ANSWER)).build();
        }

        log.info(WRONG_ANSWER);

        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(ChallengeResponse.error(WRONG_ANSWER)).build();
    }

    private Response buildChallengeNotFoundResponse(final Integer id) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ChallengeResponse.error(String.format("No challenge found for provided id=%s", id)))
                .build();
    }

    private Challenges getChallengeForId(final int id) {
        return Challenges.forId(id).orElse(null);
    }

    private ApplicationForm translateAnswerModelToApplicationForm(final AnswerModel model) {
        final ApplicationForm form = new ApplicationForm();
        form.setCompanyId(personioConfiguration.getCompanyId());
        form.setAccessToken(personioConfiguration.getAccesstoken());
        form.setTitle(null);
        form.setFirstName(model.getFirstName());
        form.setLastName(model.getLastName());
        form.setEmail(model.getEmail());
        form.setPhone(model.getPhone());
        form.setMessage(model.getMessageToGepardec());
        form.setLinkedInLink(null);
        form.setXingLink(null);
        form.setRecrutingChannel(0);
        form.setEmpfehlung(null);
        form.setDocuments(null);

        return form;
    }
}
