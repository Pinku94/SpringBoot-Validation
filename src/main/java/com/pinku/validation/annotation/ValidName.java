package com.pinku.validation.annotation;



import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;



@Constraint(validatedBy = ValidNameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {

    String message() default "name can't contains 'm' or 'n'";

    Class<?>[] group() default {};
    Class<? extends Payload >[] payload() default {};
}


