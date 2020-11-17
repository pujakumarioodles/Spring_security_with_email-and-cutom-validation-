package com.example.demo.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.model.UserDto;

public class MyOwnPhoneValidator implements ConstraintValidator<MyOwnPhoneNumber,String> {

	//object is decinding where u want to use this annotaion
	
	
	
	public void intialize(MyOwnEmail constraintAnnotation)
	{
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
		String phone=value;
		String phoneRegex = "((\\+*)((0[ -]+)*"
				+ "|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}$"; 
                  
     Pattern pat = Pattern.compile(phoneRegex); 
     if (phone == null) 
    return false; 
     return pat.matcher(phone).matches();
	}
	
	

}
