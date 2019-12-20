package com.gepardec.wdg.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbNillable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbNillable
public class AnswerModel {

    @NotNull(message = "{null.challengeAnswer}")
    private String challengeAnswer;
    @NotNull(message = "{null.firstName}")
    private String firstName;
    @NotNull(message = "{null.lastName}")
    private String lastName;
    @NotNull(message = "{null.email}")
    @Email
    private String email;
    private String phone;
    private String cv;
    private String messageToGepardec;
}
