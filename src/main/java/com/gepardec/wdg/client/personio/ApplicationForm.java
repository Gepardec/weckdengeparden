package com.gepardec.wdg.client.personio;

import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartFilename;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.io.Serializable;

public class ApplicationForm implements Serializable {

    @FormParam("company_id")
    @PartType(MediaType.TEXT_PLAIN)
    private String companyId;

    @FormParam("access_token")
    @PartType(MediaType.TEXT_PLAIN)
    private String accessToken;

    @FormParam("job_position_id")
    @PartType(MediaType.TEXT_PLAIN)
    private String jobPositionId;

    @FormParam("title")
    @PartType(MediaType.TEXT_PLAIN)
    private String title;

    @FormParam("first_name")
    @PartType(MediaType.TEXT_PLAIN)
    private String firstName;

    @FormParam("last_name")
    @PartType(MediaType.TEXT_PLAIN)
    private String lastName;

    @FormParam("email")
    @PartType(MediaType.TEXT_PLAIN)
    private String email;

    @FormParam("phone")
    @PartType(MediaType.TEXT_PLAIN)
    private String phone;

    @FormParam("custom_attribute_177021")
    @PartType(MediaType.TEXT_PLAIN)
    private String xingLink;

    @FormParam("custom_attribute_177020")
    @PartType(MediaType.TEXT_PLAIN)
    private String linkedInLink;

    @FormParam("message")
    @PartType(MediaType.TEXT_PLAIN)
    private String message;

    @FormParam("custom_attribute_260264")
    @PartType(MediaType.TEXT_PLAIN)
    private String recrutingChannel;

    @FormParam("custom_attribute_260264")
    @PartType(MediaType.TEXT_PLAIN)
    private String empfehlung;

    @PartFilename("cv")
    @FormParam("document1")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream document;

    public ApplicationForm() {
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(String jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecrutingChannel() {
        return recrutingChannel;
    }

    public void setRecrutingChannel(String recrutingChannel) {
        this.recrutingChannel = recrutingChannel;
    }

    public String getEmpfehlung() {
        return empfehlung;
    }

    public void setEmpfehlung(String empfehlung) {
        this.empfehlung = empfehlung;
    }

    public InputStream getDocument() {
        return document;
    }

    public void setDocument(InputStream document) {
        this.document = document;
    }
}
