package com.trollMarket.TrollMarket.validation;


import com.trollMarket.TrollMarket.utility.MapperHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePasswordValidator implements ConstraintValidator<ComparePassword, Object> {

    @Override
    public boolean isValid (Object value, ConstraintValidatorContext context){
        var password = MapperHelper.getStringField(value,"password");
        var passwordConfirmation = MapperHelper.getStringField(value,"passwordConfirmation");
        return password.equals(passwordConfirmation);
    }
}
