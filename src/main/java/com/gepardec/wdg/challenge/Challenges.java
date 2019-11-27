package com.gepardec.wdg.challenge;

import com.gepardec.wdg.challenge.exception.MissingInformationException;
import com.gepardec.wdg.challenge.model.AnswerModel;
import com.gepardec.wdg.challenge.model.QuestionModel;
import com.google.common.net.MediaType;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.NonNull;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@ApplicationScoped
@SuppressWarnings("unused")
public class Challenges implements ChallengesAPI {

    private static final JsonObject JSON_GET_CHALLENGE_RESPONSE_ERROR = Json.createObjectBuilder().add("error", "Eine challenge mit der id '%d' existiert nicht!").build();
    private static final JsonObject JSON_ANSWERT_CHALLENGE_RESPONSE_ERROR = Json.createObjectBuilder().add("error", "%s").build();
    private static final String WRONG_ANSWER = "Sorry, die Antwort ist falsch. Denk' nochmal in Ruhe darüber nach und versuch es noch einmal.";
    private static final String CORRECT_ANSWER = "Danke! Du hast den Geparden in dir erweckt und wir melden uns in den nächsten Tagen bei dir! Lg, Michael Sollberger";
    private static final Jsonb jsonb = JsonbBuilder.create();
    @Inject
    Logger LOG;
    @Inject
    Mailer mailer;
    @ConfigProperty(name = "challenge.mail.subject")
    String mailSubject;
    @ConfigProperty(name = "challenge.mail.text")
    String mailBody;
    @ConfigProperty(name = "challenge.mail.receiver")
    String mailReceiver;

    @Override
    public Response getChallenge (final int id) {

        final QuestionModel questionModel = getQuestionModelforId(id);
        if (questionModel.getId() > 0) {
            final JsonObject jsonResponseChallenge = Json.createObjectBuilder().add("challenge", questionModel.getId()).add("question", questionModel.getQuestion()).build();

            LOG.info(String.format("Challenge %d accepted!", id));

            return Response.ok(jsonResponseChallenge.toString()).encoding(StandardCharsets.UTF_8.name()).build();
        }

        final String challengeErrorResponse = String.format(JSON_GET_CHALLENGE_RESPONSE_ERROR.toString(), id);

        LOG.error(challengeErrorResponse);
        return Response.status(HttpStatus.SC_BAD_REQUEST).encoding(StandardCharsets.UTF_8.name()).entity(challengeErrorResponse).build();
    }

    @Override
    public Response answerChallenge (final String answerModelString) {
        try {
            final AnswerModel answerModel = jsonb.fromJson(answerModelString, AnswerModel.class);

            if (answerModel == null) {
                LOG.info("No body data send!");
                return Response.status(HttpStatus.SC_BAD_REQUEST).encoding(StandardCharsets.UTF_8.name()).build();
            }

            final boolean checkAnswer = checkAnswerModel(answerModel, getQuestionModelforId(answerModel.getChallengeId()));
            if (checkAnswer) {
                LOG.info(CORRECT_ANSWER);

                final String formattedMailSubject = String.format(mailSubject, answerModel.getFirstName(), answerModel.getLastName());

                final String formattedMailBody = String.format(mailBody, answerModel.getFirstName(), answerModel.getLastName(), answerModel.getEmail(), answerModel.getPhone(), answerModel.getChallengeId(), answerModel.getChallengeAnswer(), answerModel.getMessageToGepardec());

                Mail mail = Mail.withText(mailReceiver, formattedMailSubject, formattedMailBody);
                if (!answerModel.getCv().isBlank()) {
                    mail = mail.addAttachment("CV", Base64.getDecoder().decode(answerModel.getCv()), MediaType.PDF.type());
                }

                mailer.send(mail);

                return Response.ok(CORRECT_ANSWER).encoding(StandardCharsets.UTF_8.name()).build();
            }

            LOG.info(WRONG_ANSWER);

            return Response.status(HttpStatus.SC_BAD_REQUEST).encoding(StandardCharsets.UTF_8.name()).entity(WRONG_ANSWER).build();
        }
        catch (MissingInformationException e) {
            LOG.error(e.getMessage());
            return Response.status(HttpStatus.SC_BAD_REQUEST).encoding(StandardCharsets.UTF_8.name()).entity(String.format(JSON_ANSWERT_CHALLENGE_RESPONSE_ERROR.toString(), e.getMessage())).build();
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.serverError().entity("Es ist ein Fehler aufgetreten ...").encoding(StandardCharsets.UTF_8.name()).build();
        }
    }

    private QuestionModel getQuestionModelforId (final int questionId) {
        return Arrays.stream(QuestionModel.values()).filter(q -> q.getId() == questionId).findFirst().orElse(QuestionModel.UNKNOWN);
    }

    private boolean checkAnswerModel (@NonNull final AnswerModel answerModel, @NonNull final QuestionModel questionModel) throws MissingInformationException {

        if (answerModel.getFirstName().isBlank()) {
            throw new MissingInformationException("Fehler: Vorname fehlt!");
        }

        if (answerModel.getLastName().isBlank()) {
            throw new MissingInformationException("Fehler: Nachname fehlt!");
        }

        if (answerModel.getEmail().isBlank()) {
            throw new MissingInformationException("Fehler: E-Mail Adresse fehlt!");
        }

        if (answerModel.getChallengeId() <= 0) {
            throw new MissingInformationException("Fehler: Challenge Id fehlt!");
        }

        if (answerModel.getChallengeAnswer().isBlank()) {
            throw new MissingInformationException("Fehler: Challenge Antwort fehlt!");
        }

        return answerModel.getChallengeAnswer().trim().equals(questionModel.getAnswer());
    }
}
