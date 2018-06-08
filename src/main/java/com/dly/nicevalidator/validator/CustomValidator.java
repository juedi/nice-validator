package com.dly.nicevalidator.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;

/** 
 * @typename: CustomValidator
 * @brief: 自定义校验器
 * @author: dly
 * @date: 2018年6月5日 上午11:31:46
 * @version: 1.0.0
 * @since
 * 
 */
public class CustomValidator implements Validator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomValidator.class);
    
    private Class<?> clazz;
    
    /**
     * 创建一个新的实例 CustomValidator.
     * @param className 类名
     */
    public CustomValidator(String className) {
        try{
            this.clazz = Class.forName(className);
            //自定义类必须是Validator的实现类
            if(!Validator.class.isAssignableFrom(clazz)){
                LOGGER.error("指定的类{}不是Validator的实现类", className);
                throw new IllegalArgumentException(String.format("指定的类%s不是Validator的实现类", className));
            }
        }catch(ClassNotFoundException e){
            LOGGER.error("指定的类{}不存在", className);
            throw new IllegalArgumentException(String.format("指定的类%s不存在", className));
        }
    }
    

    @Override
    public boolean validate(ValidatorContext context, ValidateAttribute element) {
        //空值不校验，返回true
        if(element.getValue() == null){
            return true;
        }
        
        try {
            Validator validator = (Validator) clazz.newInstance();
            
            return validator.validate(context, element);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
