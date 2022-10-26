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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class URLValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilderCustomizableContext;


    URLValidator urlValidator;

    @BeforeEach
    void beforeEach() {
        urlValidator = new URLValidator();
    }


    //@WINStage3: Irgendwie ein komischer Test für den URL-Validator, findest Du nicht auch?
    @Test
    void urlValid() {
        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://github.com/Gepardec/weckdengeparden/pull/21");

        Assertions.assertTrue(urlValidator.isValid(answer, context));
    }

    @Test
    void urlValid_null() {
        AnswerChallenge2 answer = new AnswerChallenge2();

        Assertions.assertFalse(urlValidator.isValid(answer, context));
    }

    @Test
    void urlValid_wrongAddress() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);

        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://google.com/");

        Assertions.assertFalse(urlValidator.isValid(answer, context));
    }

    void urlValid_noHttpS() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);

        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("vnc://github.com/Gepardec/weckdengeparden/pull/21");

        Assertions.assertFalse(urlValidator.isValid(answer, context));
    }

    @Test
    void urlValid_noNumber() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);

        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://github.com/Gepardec/weckdengeparden/pull/ab");

        Assertions.assertFalse(urlValidator.isValid(answer, context));
    }
}
