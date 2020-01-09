package com.gepardec.wdg.client.personio;

public class PeronioErrorResponse {
    private String error;

    public PeronioErrorResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "PeronioErrorResponse{" +
                "\nmessage='" + error + '\'' +
                "\n}";
    }
}
