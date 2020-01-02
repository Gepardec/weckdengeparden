package com.gepardec.wdg.challenge.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64LengthValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Length {

    long min() default 0;

    long max() default Long.MAX_VALUE;

    String message() default "{com.gepardec.wdg.challenge.validation.Base64ByteLength}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
