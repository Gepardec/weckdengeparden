package com.gepardec.wdg.gui.model;

public class Application {

    private String jobId;
    private String firstName;
    private String lastName;
    private String email;
    // TODO to be renamed depending on master
    private String url;
    private String source;
    private String messageToGepardec;
    private String otherSource;
    private String title;
    private String phone;
    private String linkedInLink;
    private String xingLink;
    private String cv;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessageToGepardec() {
        return messageToGepardec;
    }

    public void setMessageToGepardec(String messageToGepardec) {
        this.messageToGepardec = messageToGepardec;
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

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public String getXingLink() {
        return xingLink;
    }

    public void setXingLink(String xingLink) {
        this.xingLink = xingLink;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "{" +
                "\r\n   \"jobId\": \"" + jobId + "\"," +
                "\r\n   \"firstName\": \"" + firstName + "\"," +
                "\r\n   \"lastName\": \"" + lastName + "\"," +
                "\r\n   \"email\": \"" + email + "\"," +
                "\r\n   \"url\": \"" + url + "\"," +
                "\r\n   \"source\": \"" + source + "\"," +
                "\r\n   \"messageToGepardec\": \"" + messageToGepardec + "\"," +
                "\r\n   \"otherSource\": \"" + otherSource + "\"," +
                "\r\n   \"title\": \"" + title + "\"," +
                "\r\n   \"phone\": \"" + phone + "\"," +
                "\r\n   \"linkedInLink\": \"" + linkedInLink + "\"," +
                "\r\n   \"xingLink\": \"" + xingLink + "\"," +
                "\r\n   \"cv\": \"" + cv + "\"\r\n" +
                "}";
    }
}
