package com.gepardec.wdg.challenge;

import com.gepardec.wdg.challenge.exception.MissingInformationException;
import com.gepardec.wdg.challenge.model.AnswerModel;
import com.gepardec.wdg.challenge.model.QuestionModel;
import lombok.NonNull;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@ApplicationScoped
@SuppressWarnings("unused")
public class Challenges implements ChallengesAPI {

    @Inject
    Logger LOG;

    private static final JsonObject JSON_GET_CHALLENGE_RESPONSE_ERROR = Json.createObjectBuilder()
            .add("error", "Eine challenge mit der id '%d' existiert nicht!")
            .build();

    private static final JsonObject JSON_ANSWERT_CHALLENGE_RESPONSE_ERROR = Json.createObjectBuilder()
            .add("error", "%s")
            .build();

    private static final String WRONG_ANSWER = "Hallo %s %s! Deine Antwort '%s' auf die Frage ist leider falsch!";

    private static final String CORRECT_ANSWER = "Hallo %s %s! Deine Antwort '%s' auf die Frage ist richtig!";

    private static final Jsonb jsonb = JsonbBuilder.create();

    @Override
    public Response getChallenge (final int id) {

        final QuestionModel questionModel = getQuestionModelforId(id);
        if(questionModel.getId() > 0) {
            final JsonObject jsonResponseChallenge = Json.createObjectBuilder()
                    .add("challenge", questionModel.getId())
                    .add("question", questionModel.getQuestion())
                    .build();

            LOG.info(String.format("Challenge %d accepted!", id));

            return Response.ok(jsonResponseChallenge.toString()).build();
        }

        final String challengeErrorResponse = String.format(JSON_GET_CHALLENGE_RESPONSE_ERROR.toString(), id);

        LOG.error(challengeErrorResponse);
        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(challengeErrorResponse).build();
    }

    @Override
    public Response answerChallenge (final String answerModelString) {
        try {
            final AnswerModel answerModel = jsonb.fromJson(answerModelString, AnswerModel.class);

            if(answerModel == null) {
                LOG.info("No body data send!");
                return Response.status(HttpStatus.SC_BAD_REQUEST).build();
            }

            final boolean checkAnswer = checkAnswerModel(answerModel, getQuestionModelforId(answerModel.getChallengeId()));
            if(checkAnswer) {
                final String correctAnswerResponse = String.format(CORRECT_ANSWER, answerModel.getFirstName(), answerModel.getLastName(), answerModel.getChallengeAnswer());
                LOG.info(correctAnswerResponse);

                return Response.ok(correctAnswerResponse).build();
            }

            final String wrongAnswerResponse = String.format(WRONG_ANSWER, answerModel.getFirstName(), answerModel.getLastName(), answerModel.getChallengeAnswer());
            LOG.info(wrongAnswerResponse);

            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(wrongAnswerResponse).build();
        }
        catch (MissingInformationException e) {
            LOG.error(e.getMessage());
            return Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(JSON_ANSWERT_CHALLENGE_RESPONSE_ERROR.toString(), e.getMessage())).build();
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    private QuestionModel getQuestionModelforId(final int questionId) {
        return Arrays.stream(QuestionModel.values()).filter(q -> q.getId() == questionId).findFirst().orElse(QuestionModel.UNKNOWN);
    }

    private boolean checkAnswerModel (@NonNull final AnswerModel answerModel, @NonNull final QuestionModel questionModel) throws MissingInformationException {

        if(answerModel.getFirstName().isEmpty()) {
            throw new MissingInformationException("Fehler: Vorname fehlt!");
        }

        if(answerModel.getLastName().isEmpty()) {
            throw new MissingInformationException("Fehler: Nachname fehlt!");
        }

        if(answerModel.getEmail().isEmpty()) {
            throw new MissingInformationException("Fehler: E-Mail Adresse fehlt!");
        }

        if(answerModel.getChallengeId() <= 0) {
            throw new MissingInformationException("Fehler: Challenge Id fehlt!");
        }

        if(answerModel.getChallengeAnswer().isEmpty()) {
            throw new MissingInformationException("Fehler: Challenge Antwort fehlt!");
        }

        return answerModel.getChallengeAnswer().trim().equals(questionModel.getAnswer());
    }
}
