package com.gepardec.wdg.client.personio;

import javax.json.bind.annotation.JsonbNillable;

@JsonbNillable
public class Response {

    public boolean success;
    public String message;

    public Response() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
