package com.gepardec.wdg.challenge.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = URLValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface URLValid {

    String message() default "{AnswerModel.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
