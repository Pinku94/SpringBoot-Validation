package com.pinku.validation.annotation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidNameValidator implements ConstraintValidator<ValidName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value !=null && !value.contains("n") && !value.contains("m");
    }



//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//        return value !=null && !value.contains("n") && !value.contains("m");
//    }
}
