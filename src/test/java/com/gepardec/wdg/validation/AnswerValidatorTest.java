package com.gepardec.wdg.validation;

import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.challenge.validation.AnswerValid;
import com.gepardec.wdg.challenge.validation.AnswerValidator;
import com.gepardec.wdg.client.personio.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
class AnswerValidatorTest {

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilderCustomizableContext;

    @Mock
    private ConstraintValidatorContext context;

    private AnswerValidator validator;

    @BeforeEach
    void beforeEach() {
        validator = new AnswerValidator();
    }

    @Test
    void isValid_withSourceSONSTIGE_thenFalse() {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
        final Answer given = new Answer();
        given.setSource(Source.SONSTIGES);
        Assertions.assertFalse(validator.isValid(given, context));

        verify(context, times(1)).buildConstraintViolationWithTemplate(anyString());
        verify(constraintViolationBuilder, times(1)).addPropertyNode(anyString());
        verify(nodeBuilderCustomizableContext, times(1)).addConstraintViolation();
    }

    @Test
    void isValid_withNull_thenTrue() {
        Assertions.assertTrue(validator.isValid(null, context));
    }

    @Test
    void isValid_withNullSource_thenTrue() {
        final Answer given = new Answer();
        Assertions.assertTrue(validator.isValid(given, context));
    }

    @Test
    void isValid_withSourceLINKEDIN_thenTrue() {
        final Answer given = new Answer();
        given.setSource(Source.LINKEDIN);
        Assertions.assertTrue(validator.isValid(given, context));
    }

    @Test
    void isValid_withSourceSONSTIGEAndOtherSource_thenTrue() {
        final Answer given = new Answer();
        given.setSource(Source.SONSTIGES);
        given.setOtherSource("My friend");
        Assertions.assertTrue(validator.isValid(given, context));
    }
}
