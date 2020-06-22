package com.projects.leavemanagementsystem.customvalidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AfterDateValidator implements ConstraintValidator<AfterPresentDate, Date> {
    public void initialize(AfterPresentDate constraint) {
    }

    public boolean isValid(Date obj, ConstraintValidatorContext context) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        return obj.after(date);
    }
}