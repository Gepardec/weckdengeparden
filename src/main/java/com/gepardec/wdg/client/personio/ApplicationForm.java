package com.gepardec.wdg.client.personio;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

public class ApplicationForm {

    @FormParam("company_id")
    private String companyId;

    @FormParam("access_token")
    private String accessToken;

    @FormParam(value = "title")
    private String title;

    @FormParam("first_name")
    private String firstName;

    @FormParam("last_name")
    private String lastName;

    @FormParam("email")
    private String email;

    @FormParam("phone")
    private String phone;

    @FormParam("custom_attribute_177021")
    private String xingLink;

    @FormParam("custom_attribute_177020")
    private String linkedInLink;

    @FormParam("message")
    private String message;

    @FormParam("custom_attribute_260264")
    private Integer recrutingChannel;

    @FormParam("custom_attribute_260264")
    private String empfehlung;

    @FormParam("documents")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream[] documents;

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

    public Integer getRecrutingChannel() {
        return recrutingChannel;
    }

    public void setRecrutingChannel(Integer recrutingChannel) {
        this.recrutingChannel = recrutingChannel;
    }

    public String getEmpfehlung() {
        return empfehlung;
    }

    public void setEmpfehlung(String empfehlung) {
        this.empfehlung = empfehlung;
    }

    public InputStream[] getDocuments() {
        return documents;
    }

    public void setDocuments(InputStream[] documents) {
        this.documents = documents;
    }
}
