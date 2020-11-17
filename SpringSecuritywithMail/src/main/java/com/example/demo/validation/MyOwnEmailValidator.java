package com.example.demo.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.model.UserDto;

public class MyOwnEmailValidator implements ConstraintValidator<MyOwnEmail,String> {

	//object is decinding where u want to use this annotaion
	
	
	
	public void intialize(MyOwnEmail constraintAnnotation)
	{
		
	}

	/*public boolean isValid(UserDto value, ConstraintValidatorContext context) {
		String email=value.getEmail();
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                 "[a-zA-Z0-9_+&*-]+)*@" + 
                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                 "A-Z]{2,7}$"; 
                   
      Pattern pat = Pattern.compile(emailRegex); 
      if (email == null) 
     return false; 
      return pat.matcher(email).matches();
	
	}*/

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) 
	{
		String email=value;
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
     Pattern pat = Pattern.compile(emailRegex); 
     if (email == null) 
    return false; 
     return pat.matcher(email).matches();
	}
	
	

}
