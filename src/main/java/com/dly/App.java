package com.dly;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dly.nicevalidator.NiceValidator;
import com.dly.nicevalidator.ValidationResult;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorElement;
import com.dly.nicevalidator.validator.LengthValidator;
import com.dly.nicevalidator.validator.RequiredValidator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Validator<Object> v1 = new RequiredValidator();
    	Validator<String> v2 = new LengthValidator(1,2);
    	
    	List<Validator> validators = new ArrayList<>();
    	validators.add(v1);
    	validators.add(v2);
    	
    	ValidatorChain chain1 = new ValidatorChain(validators);
    	ValidatorChain chain2 = new ValidatorChain(validators);
    	
    	
    	ValidatorElement<String> ele1 = new ValidatorElement<>("name", "Eri", chain1);
    	ValidatorElement<Integer> ele2 = new ValidatorElement<>("bithday", 1988, chain2);
    	
    	ValidationResult result = NiceValidator.build(ele1, ele2).failOver().addAttributeToContext("birthday", new Date()).doValidator();
    	System.out.println(result);
    }
}
