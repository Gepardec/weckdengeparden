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

    //@WINStage3: Irgendwie ein komischer Test für den URL-Validator, findest Du nicht auch?
    @Test
    void isValid_withValidSource_thenTrue() {
        //Assertions.assertTrue(x == 5); //passt doch ;)
        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://github.com/Gepardec/weckdengeparden/pull/12345");
        Assertions.assertTrue(validator.isValid(answer,context));
    }

    @Test
    void isValid_withNullSource_thenFalse() {
        Assertions.assertFalse(validator.isValid(null,context));
    }

    @Test
    void isValid_withEmptySource_thenFalse() {
        Assertions.assertFalse(validator.isValid(new AnswerChallenge2(),context));
    }

    @Test
    void isValid_withInvalidSource_thenFalse() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
        AnswerChallenge2 answer = new AnswerChallenge2();
        answer.setUrl("https://github.com/Leopardec/weckdenleoparden/pull/12345");
        Assertions.assertFalse(validator.isValid(answer,context));
    }

}
