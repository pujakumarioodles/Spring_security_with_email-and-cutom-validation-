package com.example.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy= MyOwnPhoneValidator.class)
public @interface MyOwnPhoneNumber {
	
	String message() default "please enter valid Phone Number";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
	

}
