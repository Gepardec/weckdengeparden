package com.gepardec.wdg.challenge.validation;

import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.client.personio.Source;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnswerValidator implements ConstraintValidator<AnswerValid, Answer> {

    @Override
    public boolean isValid(Answer answer, ConstraintValidatorContext context) {
        boolean valid = true;
        if (answer != null) {
            // When source = Source.SONSTIGE the otherSource attribute must be set as well
            if (Source.SONSTIGES.equals(answer.getSource()) && (answer.getOtherSource() == null || answer.getOtherSource().trim().isEmpty())) {
                context.buildConstraintViolationWithTemplate("{AnswerModel.source.otherSource.invalid}")
                        .addPropertyNode("source")
                        .addConstraintViolation();
                valid = false;
            }
        }

        return valid;
    }
}
