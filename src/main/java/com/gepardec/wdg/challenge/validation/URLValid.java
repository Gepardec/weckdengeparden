package com.gepardec.wdg.challenge.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = URLValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface URLValid {

    String message() default "{AnswerModel.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
