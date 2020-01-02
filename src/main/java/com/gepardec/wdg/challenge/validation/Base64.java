package com.gepardec.wdg.challenge.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64 {

    String message() default "{com.gepardec.wdg.challenge.validation.Base64}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
