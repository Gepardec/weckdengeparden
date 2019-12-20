package com.gepardec.wdg.challenge.model;

import io.vertx.core.eventbus.impl.codecs.StringMessageCodec;
import lombok.Data;

import javax.json.bind.annotation.JsonbNillable;
import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@JsonbNillable
public class ChallengeResponse {

    private String message;
    private boolean success;

    ChallengeResponse(String message) {
        this(message, true);
    }

    ChallengeResponse(String message, boolean success) {
        this.success = success;
        this.message = message;
    }

    ChallengeResponse(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.message = "Some validation errors have occurred: "
                + violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    public static ChallengeResponse success(final String message) {
        return new ChallengeResponse(message);
    }

    public static ChallengeResponse error(final String message) {
        return new ChallengeResponse(message, false);
    }

    public static ChallengeResponse invalid(Set<? extends ConstraintViolation<?>> violations) {
        return new ChallengeResponse(violations);
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
