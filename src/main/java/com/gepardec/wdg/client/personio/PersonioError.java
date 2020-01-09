package com.gepardec.wdg.client.personio;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PersonioError {
    ALREADY_APPLIED("Applicant already applied to this position.",
            "The used email address has already been used for an application"),
    UNDEFINED("",
            "Sorry, something went wrong with a call to our backend service, please try again later");

    private static final Map<String, PersonioError> applicationErrors = Stream.of(PersonioError.values())
            .collect(Collectors.toMap(error -> error.message.toUpperCase(), Function.identity()));
    public final String message;
    public final String clientMessage;

    PersonioError(final String message) {
        this(message, null);
    }

    PersonioError(final String message, final String clientMessage) {
        this.message = message;
        this.clientMessage = clientMessage;
    }

    public static PersonioError forMessage(final String message) {
        return Optional.ofNullable(applicationErrors.get(Optional.of(message).orElse("").toUpperCase()))
                .orElse(PersonioError.UNDEFINED);
    }

    public String getMessage() {
        return message;
    }

    public String getClientMessage() {
        return clientMessage;
    }
}
