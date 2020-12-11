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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class URLValidatorTest {

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilderCustomizableContext;

    @Mock
    private ConstraintValidatorContext context;

    private URLValidator validator;

    @BeforeEach
    void beforeEach() {
        validator = new URLValidator();
    }

    @Test
    void isValid_answerNull_thenFalse() {
        Assertions.assertFalse(validator.isValid(null, context));
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
    }

    @Test
    void isValid_withUrlUngueltig_thenFalse() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
        final AnswerChallenge2 given = new AnswerChallenge2();
        given.setUrl("https111222://github.com/Gepardec/weckdengeparden/pull/21");
        Assertions.assertFalse(validator.isValid(given, context));

        verify(context, times(1)).buildConstraintViolationWithTemplate(anyString());
        verify(constraintViolationBuilder, times(1)).addPropertyNode(anyString());
        verify(nodeBuilderCustomizableContext, times(1)).addConstraintViolation();
    }

}
