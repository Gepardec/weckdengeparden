package com.gepardec.wdg.validation;

import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.challenge.validation.AnswerValidator;
import com.gepardec.wdg.client.personio.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.Mockito.*;

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

    @ParameterizedTest
    @EnumSource(value = Source.class, names = {"SONSTIGES", "MESSEN", "MEETUPS", "EMPFEHLUNG"})
    void isValid_withValidSourcesEmptyOtherSource_thenFalse(final Source source) {
        when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
        final Answer given = new Answer();
        given.setSource(source);
        given.setOtherSource(null);
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
        given.setSource(null);
        given.setOtherSource(null);
        Assertions.assertTrue(validator.isValid(given, context));
    }

    @Test
    void isValid_withSourceLINKEDIN_thenTrue() {
        final Answer given = new Answer();
        given.setSource(Source.LINKEDIN);
        given.setOtherSource(null);
        Assertions.assertTrue(validator.isValid(given, context));
    }

    @ParameterizedTest
    @EnumSource(value = Source.class, names = {"SONSTIGES", "MESSEN", "MEETUPS", "EMPFEHLUNG"})
    void isValid_withValidSourceAndOtherSource_thenTrue(final Source source) {
        final Answer given = new Answer();
        given.setSource(source);
        given.setOtherSource("My friend");
        Assertions.assertTrue(validator.isValid(given, context));
    }
}
