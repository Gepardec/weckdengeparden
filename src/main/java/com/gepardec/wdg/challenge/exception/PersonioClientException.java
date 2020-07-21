package com.gepardec.wdg.challenge.exception;

import com.gepardec.wdg.client.personio.error.PersonioError;

public class PersonioClientException extends RuntimeException {

    public final int status;
    public final String originalMessage;
    public final PersonioError applicationError;

    public PersonioClientException(int status, String originalMessage, PersonioError applicationError) {
        this.status = status;
        this.originalMessage = originalMessage;
        this.applicationError = applicationError;
    }

    public static PersonioClientException of(final int status, final String errorMessage) {
        return new PersonioClientException(status, errorMessage, PersonioError.forMessage(errorMessage));
    }

    @Override
    public String toString() {
        return "PersonioClientException{" +
                "status=" + status +
                ", originalMessage='" + originalMessage + '\'' +
                ", applicationError=" + applicationError +
                '}';
    }
}
