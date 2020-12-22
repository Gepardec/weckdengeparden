package com.gepardec.wdg.challenge.validation;

import com.gepardec.wdg.challenge.model.AnswerChallenge2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class URLValidator implements ConstraintValidator<URLValid, AnswerChallenge2> {

    private static final String URL_REGEX = "^(https|http)://github.com/Gepardec/weckdengeparden/pull/[0-9]{1,}";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    @Override
    public boolean isValid(AnswerChallenge2 answer, ConstraintValidatorContext context) {
        if (answer == null || answer.getUrl() == null) {
            return false;
        }else{
            Matcher matcher = URL_PATTERN.matcher(answer.getUrl());
            if (!matcher.matches()) {
                context.buildConstraintViolationWithTemplate("{AnswerModel.url.invalid}")
                        .addPropertyNode("url")
                        .addConstraintViolation();
                return false;
            }
            return true;
        }
    }
}