package com.gepardec.wdg.application.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidator implements ConstraintValidator<URL, String> {

    private static final String URL_REGEX;

    static {
        URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
                "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
                "([).!';/?:,][[:blank:]])?$";
    }

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }
}
