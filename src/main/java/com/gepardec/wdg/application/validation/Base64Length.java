package com.gepardec.wdg.application.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64LengthValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Length {

    /**
     * @return the minimum length of the base64 data
     */
    long min() default 0;

    /**
     * @return the maximum length of the base64 data
     */
    long max() default Long.MAX_VALUE;

    String message() default "{com.gepardec.wdg.application.validation.Base64ByteLength}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
