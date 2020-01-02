package com.gepardec.wdg.challenge.exception;

public class PersonioClientException extends RuntimeException {

    public final String uri;
    public final int statusCode;
    public final String body;

    public PersonioClientException(String uri, int statusCode, String body) {
        this.uri = uri;
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public String toString() {
        return "PersonioClientException{" +
                "uri='" + uri + '\'' +
                ", statusCode=" + statusCode +
                ", body='" + body + '\'' +
                '}';
    }
}
