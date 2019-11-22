package com.gepardec.wdg.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbNillable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbNillable
public class AnswerModel {
    private String firstName = ""; // String
    private String lastName = ""; // String
    private String email = ""; // String
    private String phone = ""; // String, optional
    private int challengeId; // Int
    private String challengeAnswer = ""; // String
    private String cv = ""; // base64 encoded PDF, optional
    private String messageToGepardec = ""; // String, optional
}
