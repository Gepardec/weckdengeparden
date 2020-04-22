package com.gepardec.wdg.application.validation;

import com.gepardec.wdg.client.personio.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SourceSelectionValidatorTest {
    private SourceSelectionValidator validator;

    @BeforeEach
    void init() {
        validator = new SourceSelectionValidator();
    }

    @Test
    void isValid_withNull_thenFalse() {
        Assertions.assertFalse(validator.isValid(null, null));
    }

    @Test
    void isValid_withErrorEnumValue_thenFalse() {
        Assertions.assertFalse(validator.isValid(Source.ERROR, null));
    }

    @Test
    void isValid_withXingEnumValue_thenTrue() {
        Assertions.assertTrue(validator.isValid(Source.XING, null));
    }
}
