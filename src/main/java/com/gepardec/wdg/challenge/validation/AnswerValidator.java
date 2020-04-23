package com.gepardec.wdg.challenge.validation;

import com.gepardec.wdg.challenge.model.Answer;
import com.gepardec.wdg.client.personio.Source;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AnswerValidator implements ConstraintValidator<AnswerValid, Answer> {

    private static final List<Source> sources = List.of(Source.SONSTIGES, Source.MESSEN, Source.MEETUPS, Source.EMPFEHLUNG);

    @Override
    public boolean isValid(Answer answer, ConstraintValidatorContext context) {
        boolean valid = true;

        if(answer == null) {
            valid = false;
        } else {
            if (answer.getSource() != null && sources.contains(answer.getSource()) && (answer.getOtherSource() == null || answer.getOtherSource().isBlank())) {
                context.buildConstraintViolationWithTemplate("{AnswerModel.source.otherSource.invalid}")
                        .addPropertyNode("source")
                        .addConstraintViolation();
                valid = false;
            }
        }

        return valid;
    }
}
