package com.gepardec.wdg.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SourceSelectionValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SourceSelection {
    String message() default "{com.gepardec.wdg.application.validation.SourceSelection}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
