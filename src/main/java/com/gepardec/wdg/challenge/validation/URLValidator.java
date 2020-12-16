package com.gepardec.wdg.challenge.validation;

import com.gepardec.wdg.challenge.model.AnswerChallenge2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class URLValidator implements ConstraintValidator<URLValid, AnswerChallenge2> {

    private static final String URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
            "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
            "([).!';/?:,][[:blank:]])?$";

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