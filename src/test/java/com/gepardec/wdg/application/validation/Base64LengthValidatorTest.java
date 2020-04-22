package com.gepardec.wdg.application.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.Base64;

class Base64LengthValidatorTest {

    private Base64LengthValidator validator;

    @BeforeEach
    void beforeEach() {
        final Base64Length base64Length = createAnnotation(10, 20);
        validator = new Base64LengthValidator();
        validator.initialize(base64Length);
    }

    @Test
    void init_withOverlap_throwsIllegalArgumentException() {
        final Base64Length base64Length = createAnnotation(12, 9);
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.initialize(base64Length));
    }

    @Test
    void isValid_withToShort_thenFalse() {
        final String base64 = Base64.getEncoder().encodeToString("a".getBytes());
        Assertions.assertFalse(validator.isValid(base64, null));
    }

    @Test
    void isValid_withToLong_thenFalse() {
        final String base64 = Base64.getEncoder().encodeToString("asdfasgkasjdglashgajsdhjkashahglashfashldfasdf".getBytes());
        Assertions.assertFalse(validator.isValid(base64, null));
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
    void isValid_withPadding0_thenTrue() {
        final String base64 = createBase64StringForPadding(0);
        Assertions.assertTrue(validator.isValid(base64, null));
    }

    @Test
    void isValid_withPadding1_thenTrue() {
        final String base64 = createBase64StringForPadding(1);
        Assertions.assertTrue(validator.isValid(base64, null));
    }

    @Test
    void isValid_withPadding2_thenTrue() {
        final String base64 = createBase64StringForPadding(2);
        Assertions.assertTrue(validator.isValid(base64, null));
    }

    private Base64Length createAnnotation(long min, long max) {
        return new Base64Length() {
            @Override
            public long min() {
                return min;
            }

            @Override
            public long max() {
                return max;
            }

            @Override
            public String message() {
                return "";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
        };
    }

    private String createBase64StringForPadding(final int paddingCount) {
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
