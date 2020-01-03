package com.gepardec.wdg.challenge.exception;

public class PersonioClientException extends RuntimeException {

    public PersonioClientException(String message) {
        super(message);
    }

    public static PersonioClientException of(int statusCode, String body) {
        return new PersonioClientException(String.format("Call failed with status: '%d' and had body%n%s", statusCode, body));
    }
}
