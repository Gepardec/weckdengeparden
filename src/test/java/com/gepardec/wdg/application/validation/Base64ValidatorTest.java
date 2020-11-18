package com.gepardec.wdg.application.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

class Base64ValidatorTest {

    private Base64Validator validator;

    @BeforeEach
    void beforeEach() {
        validator = new Base64Validator();
    }

    @Test
    void isValid_withNull_thenFalse() {
        Assertions.assertFalse(validator.isValid(null, null));
    }

    @Test
    void isValid_withEmptyString_thenFalse() {
        Assertions.assertFalse(validator.isValid("", null));
    }


    @Test
    void isValid_withInvalidString_thenFalse() {
        final String base64 = "!" + createBase64StringForPadding(0);
        Assertions.assertFalse(validator.isValid(base64, null));
    }

    @Test
    void isValid_withPadding0_thenTrue() {
        final String base64 = createBase64StringForPadding(0);
        Assertions.assertTrue(validator.isValid(base64, null));
    }


    //@WINStage2: Test isValid_withPadding1_thenTruealse() will fail because of a broken CodePart
    @Test
    void isValid_withPadding1_thenTruealse() {
        final String base64 = createBase64StringForPadding(1);
        Assertions.assertTrue(validator.isValid(base64, null));
    }

    @Test
    void isValid_withPadding2_thenTruealse() {
        final String base64 = createBase64StringForPadding(2);
        Assertions.assertTrue(validator.isValid(base64, null));
    }

    public String createBase64StringForPadding(final int paddingCount) {
        final Base64.Encoder encoder = Base64.getEncoder();
        switch (paddingCount) {
            case 2:
                return encoder.encodeToString("any carnal pleas".getBytes());
            case 1:
                return encoder.encodeToString("any carnal pleasu".getBytes());
            case 0:
                return encoder.encodeToString("any carnal pleasur".getBytes());
            default:
                throw new IllegalArgumentException(String.format("Padding '%d' not supported", paddingCount));
        }
    }
}
