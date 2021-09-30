package com.gepardec.wdg.validation;

import com.gepardec.wdg.challenge.model.AnswerChallenge2;
import com.gepardec.wdg.challenge.validation.URLValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class URLValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    private URLValidator validator;

    @BeforeEach
    void beforeEach() {
        validator = new URLValidator();
    }

    @Test
    void isValid_answerNull_thenFalse() {
        Assertions.assertFalse(validator.isValid((AnswerChallenge2) null, context));
    }

    @Test
    void isValid_withNullUrl_thenFalse() {
        final AnswerChallenge2 given = new AnswerChallenge2();
        given.setUrl(null);
        Assertions.assertFalse(validator.isValid(given, context));
    }

    @Test
    void isValid_withPullRqUrl_thenTrue() {
        final AnswerChallenge2 given = new AnswerChallenge2();
        given.setUrl("https://github.com/Gepardec/weckdengeparden/pull/21");
        Assertions.assertTrue(validator.isValid(given, context));
        given.setUrl("http://github.com/Gepardec/weckdengeparden/pull/01");
        Assertions.assertTrue(validator.isValid(given, context));
    }

}
