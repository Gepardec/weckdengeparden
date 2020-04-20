package com.gepardec.wdg.challenge;

import com.gepardec.wdg.application.configuration.PersonioConfiguration;
import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.client.personio.ApplicationForm;
import com.gepardec.wdg.client.personio.Source;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationFormTranslatorTest {

    @Mock
    private PersonioConfiguration personioConfiguration;

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String COMPANY_ID = "COMPANY_ID";
    private static final String JOB_ID = "JOB_ID";

    @BeforeEach
    void beforeEach() {
        when(personioConfiguration.getAccesstoken()).thenReturn(ACCESS_TOKEN);
        when(personioConfiguration.getCompanyId()).thenReturn(COMPANY_ID);
    }

    @Test
    void answerToApplicationForm_whenSourceNull_thenNoRecrutingchannel() throws Exception {
        final Answer given = buildValidAnswer();
        final ApplicationForm form = ApplicationFormTranslator.answerToApplicationForm(personioConfiguration, given);
        assertTranslation(given, form);
    }

    @Test
    void answerToApplicationForm_whenSourceSONSTIGE_thenRecrutingchannelSet() throws Exception {
        final Answer given = buildValidAnswer();
        given.setSource(Source.SONSTIGES);
        given.setOtherSource("My friend");
        final ApplicationForm form = ApplicationFormTranslator.answerToApplicationForm(personioConfiguration, given);
        assertTranslation(given, form);
    }

    private void assertTranslation(final Answer given, final ApplicationForm translated) throws Exception {
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACCESS_TOKEN, translated.getAccessToken(), "accessToken"),
                () -> Assertions.assertEquals(COMPANY_ID, translated.getCompanyId(), "companyId"),
                () -> Assertions.assertEquals(given.getJobId(), translated.getJobPositionId(), "jobPositionId"),
                () -> Assertions.assertEquals(given.getFirstName(), translated.getFirstName(), "firstName"),
                () -> Assertions.assertEquals(given.getLastName(), translated.getLastName(), "lastName"),
                () -> Assertions.assertEquals(given.getEmail(), translated.getEmail(), "email"),
                () -> Assertions.assertEquals(given.getTitle(), translated.getTitle(), "title"),
                () -> Assertions.assertEquals(given.getPhone(), translated.getPhone(), "phone"),
                () -> Assertions.assertEquals(given.getLinkedInLink(), translated.getLinkedInLink(), "linkedInLink"),
                () -> Assertions.assertEquals(given.getXingLink(), translated.getXingLink(), "xingLink"),
                () -> Assertions.assertEquals(given.getMessageToGepardec(), translated.getMessage(), "message"),
                () -> Assertions.assertEquals(given.getOtherSource(), translated.getEmpfehlung(), "empfehlung"));

        if (given.getSource() != null) {
            Assertions.assertEquals(given.getSource().idStr, translated.getRecrutingChannel(), "recrutingChannel");
        } else {
            Assertions.assertNull(translated.getRecrutingChannel());
        }
        if (given.getCv() != null) {
            final byte[] expectedCvData = Base64.getDecoder().decode(given.getCv().getBytes());
            final String expectedCv = new String(expectedCvData);
            final byte[] cvData = IOUtils.toByteArray(translated.getDocument());
            final String cv = new String(cvData);
            Assertions.assertEquals(expectedCv, cv, "documents");
        }
    }

    private Answer buildValidAnswer() {
        final Answer answer = new Answer();
        answer.setJobId("1");
        answer.setTitle("Ing.");
        answer.setFirstName("Thomas");
        answer.setLastName("Herzog");
        answer.setEmail("thomas.herzog@gepardec.om");
        answer.setPhone("+43123456789");
        answer.setMessageToGepardec("This is my message");
        answer.setXingLink("http://xing.com");
        answer.setLinkedInLink("http://linkedin.com");
        answer.setCv(Base64.getEncoder().encodeToString("This is my CV".getBytes()));

        return answer;
    }
}
