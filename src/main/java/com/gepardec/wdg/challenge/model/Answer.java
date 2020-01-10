package com.gepardec.wdg.challenge.model;

import com.gepardec.wdg.challenge.validation.AnswerValid;
import com.gepardec.wdg.application.validation.Base64;
import com.gepardec.wdg.application.validation.Base64Length;
import com.gepardec.wdg.client.personio.Source;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AnswerValid
public class Answer {

    @NotNull(message = "{AnswerModel.challengeAnswer.notNull}")
    private String answer;

    @NotNull(message = "{AnswerModel.firstName.notNull}")
    private String firstName;

    @NotNull(message = "{AnswerModel.lastName.notNull}")
    private String lastName;

    @NotNull(message = "{AnswerModel.email.notNull}")
    @Email(message = "{AnswerModel.email.email}")
    private String email;

    @Base64(message = "{AnswerModel.cv.base64}")
    @Base64Length(max = 10_485_760, message = "{AnswerModel.cv.base64Length}")
    private String cv;

    @URL
    private String xingLink = "";

    @URL
    private String linkedInLink = "";

    private Source source = Source.SONSTIGES;

    private String otherSource = "Bewerber hat nichst definiert";

    private String title = "";

    private String phone = "";

    private String messageToGepardec = "";
}
