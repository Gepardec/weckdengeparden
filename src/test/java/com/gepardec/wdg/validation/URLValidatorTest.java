package com.gepardec.wdg.validation;

import com.gepardec.wdg.challenge.model.Answer;
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

    private URLValidator urlValidator;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilderCustomizableContext;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void beforeEach() {
        urlValidator = new URLValidator();
    }

    @Test
    void isValid_withValidUrl_thenTrue() {
        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://github.com/Gepardec/weckdengeparden/pull/12345465767");
        Assertions.assertTrue(urlValidator.isValid(answer, context));
    }

    @Test
    void isValid_withValidUrl_thenFalse() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);

        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://github.com/Gepardec/weckdengeparden/pull/123abc");
        Assertions.assertFalse(urlValidator.isValid(answer, context));
    }

}
