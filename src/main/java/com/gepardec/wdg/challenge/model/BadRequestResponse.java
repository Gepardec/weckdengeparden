package com.gepardec.wdg.challenge.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BadRequestResponse extends BaseResponse {

    private List<String> constraintViolations;

    public BadRequestResponse() {
    }

    BadRequestResponse(Set<? extends javax.validation.ConstraintViolation> violations) {
        super("The request was invalid due to constraint violations", false);
        this.constraintViolations = violations.stream()
                .map(javax.validation.ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    public static BadRequestResponse invalid(Set<? extends javax.validation.ConstraintViolation> violations) {
        return new BadRequestResponse(violations);
    }

    public List<String> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(List<String> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
}
