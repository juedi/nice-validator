package com.dly.nicevalidator;

/** 
 * @typename: AttributeConvertor
 * @brief: 属性转换器接口，当外部属性对象转换为校验器内部的属性对象，需要实现该接口，实现一个属性转换器
 * @author: dly
 * @date: 2018年6月5日 下午4:11:05
 * @version: 1.0.0
 * @since
 * 
 */
public interface AttributeConvertor<T> {

    /**    
     * @Description:   转换方法，将外部对象转换为校验属性对象
     * @param t 外部对象
     * @return 校验属性对象
     */
    ValidateAttribute convert(T t);
}
