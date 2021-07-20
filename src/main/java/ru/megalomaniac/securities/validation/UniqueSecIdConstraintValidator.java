package ru.megalomaniac.securities.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.megalomaniac.securities.service.SecuritiesInfoService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSecIdConstraintValidator implements ConstraintValidator<UniqueSecIdConstraint,String> {
    @Autowired
    private SecuritiesInfoService securitiesInfoService;

    @Override
    public void initialize(UniqueSecIdConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String secid, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if(secid == null)
            result=false;
        else
            result = !securitiesInfoService.existsBySecid(secid);
        return result;
    }
}
