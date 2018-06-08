package com.dly.nicevalidator.validator;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;

/** 
 * @typename: RegexValidator
 * @brief: 正则表达式校验器
 * @author: dly
 * @date: 2018年6月5日 上午11:32:20
 * @version: 1.0.0
 * @since
 * 
 */
public class RegexValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegexValidator.class);
    
    private String regex;
    
    /**
     * 创建一个新的实例 RegexValidator.
     * @param regex 正则表达式
     */
    public RegexValidator(String regex) {
        this.regex = regex;
        try{
            Pattern.compile(regex);
        }catch(Exception e){
            LOGGER.error("不正确的正则表达式["+regex+"]", e);
            throw new IllegalArgumentException("不正确的正则表达式["+regex+"]");
        }
    }



    @Override
    public boolean validate(ValidatorContext context, ValidateAttribute element) {
        //空值不错判断，直接返回成功
        if(element.getValue() == null){
            return true;
        }
        
        //所有能正确转换成string的值都可进行正则校验
        String value = element.getValue().toString();
        
        if(!value.matches(regex)){
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写格式正确的值"));
            return false;
        }
        
        return true;
    }

}
