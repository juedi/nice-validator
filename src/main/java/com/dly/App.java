package com.dly;

import java.util.ArrayList;
import java.util.List;

import com.dly.nicevalidator.LengthValidator;
import com.dly.nicevalidator.NiceValidator;
import com.dly.nicevalidator.RequiredValidator;
import com.dly.nicevalidator.ValidationResult;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorElement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Validator v1 = new RequiredValidator();
    	Validator v2 = new LengthValidator();
    	
    	List<Validator> validators = new ArrayList<>();
    	validators.add(v1);
    	validators.add(v2);
    	
    	ValidatorChain chain1 = new ValidatorChain(validators);
    	ValidatorChain chain2 = new ValidatorChain(validators);
    	
    	
    	ValidatorElement ele1 = new ValidatorElement("name", "Eric", chain1);
    	ValidatorElement ele2 = new ValidatorElement("bithday", null, chain2);
    	
    	ValidationResult result = NiceValidator.build(ele1, ele2).failFast(false).doValidator();
    	System.out.println(result);
    }
}
