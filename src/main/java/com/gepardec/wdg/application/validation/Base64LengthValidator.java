package com.gepardec.wdg.application.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64LengthValidator implements ConstraintValidator<Base64Length, String> {

    private long minLength;

    private long maxLength;

    @Override
    public void initialize(Base64Length constraintAnnotation) {
        minLength = constraintAnnotation.min();
        maxLength = constraintAnnotation.max();
        if (minLength > maxLength) {
            throw new IllegalArgumentException("Minlength must not be greater than maxlength");
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        final long length = value.length();
        final long padding = calculatePadding(value);
        final long size = (long) ((double) length * 0.75) - padding;
        return (size >= minLength && size <= maxLength);
    }

    private long calculatePadding(String value) {
        if (value.endsWith("==")) {
            return 2;
        } else if (value.endsWith("=")) {
            return 1;
        } else {
            return 0;
        }
    }
}
