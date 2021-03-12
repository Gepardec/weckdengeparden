package com.gepardec.wdg.challenge.model;

import com.gepardec.wdg.application.jsonb.JsonBDeserializerBinding;
import com.gepardec.wdg.application.validation.Base64;
import com.gepardec.wdg.application.validation.Base64Length;
import com.gepardec.wdg.application.validation.SourceSelection;
import com.gepardec.wdg.challenge.validation.AnswerValid;
import com.gepardec.wdg.client.personio.Source;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.validation.constraints.*;

@AnswerValid
public class Answer {

    @Size(min = 6, max = 6, message = "{AnswerModel.jobId.wrongSize}")
    private String jobId;

    @NotBlank(message = "{AnswerModel.firstName.notEmpty}")
    private String firstName;

    @NotBlank(message = "{AnswerModel.lastName.notEmpty}")
    private String lastName;

    @NotEmpty(message = "{AnswerModel.email.notEmpty}")
    @Email(message = "{AnswerModel.email.email}")
    private String email;

    @Base64(message = "{AnswerModel.cv.base64}")
    @Base64Length(max = 10_485_760, message = "{AnswerModel.cv.base64Length}")
    @NotEmpty(message = "{AnswerModel.cv.notEmpty}")
    private String cv;

    @URL(message = "{AnswerModel.xingLink.url}")
    private String xingLink = "";

    @URL(message = "{AnswerModel.linkedInLink.url}")
    private String linkedInLink = "";

    @NotEmpty(message = "{AnswerModel.messageToGepardec.notEmpty}")
    private String messageToGepardec;

    @NotNull(message = "{AnswerModel.source.notNull}")
    @JsonbTypeDeserializer(JsonBDeserializerBinding.class)
    @SourceSelection(message = "{AnswerModel.source.selection}")
    private Source source;

    private String otherSource = "";

    private String title = "";

    private String phone = "";

    public Answer() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getXingLink() {
        return xingLink;
    }

    public void setXingLink(String xingLink) {
        this.xingLink = xingLink;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getOtherSource() {
        return otherSource;
    }

    public void setOtherSource(String otherSource) {
        this.otherSource = otherSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessageToGepardec() {
        return messageToGepardec;
    }

    public void setMessageToGepardec(String messageToGepardec) {
        this.messageToGepardec = messageToGepardec;
    }
}
