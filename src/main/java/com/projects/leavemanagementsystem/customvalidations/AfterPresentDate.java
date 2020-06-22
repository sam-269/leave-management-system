package com.projects.leavemanagementsystem.customvalidations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AfterDateValidator.class)
public @interface AfterPresentDate {
    String message() default "{Date should not be a past date}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}