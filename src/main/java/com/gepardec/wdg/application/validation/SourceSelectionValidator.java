package com.gepardec.wdg.application.validation;

import com.gepardec.wdg.client.personio.Source;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SourceSelectionValidator implements ConstraintValidator<SourceSelection, Source> {

    @Override
    public boolean isValid(Source source, ConstraintValidatorContext constraintValidatorContext) {
        if(source == null) {
            return false;
        }

        return !source.equals(Source.ERROR);
    }
}
