package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.domain.ValidateResult;

/**
 * @typename NiceValidator
 * @brief 校验过程构建类，是校验的入口，通过该类构建校验的配置，做校验
 * @author dly
 * @date 2018年5月31日 下午2:35:38
 * @version 1.0.0
 * @since 1.0.0
 */
public final class NiceValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NiceValidator.class);
    
	private ValidatorContext validatorContext = new ValidatorContext();

	private List<ValidateAttribute> validateAttributes;

	private boolean failFast = true;
	
	/**
	 * 创建一个新的实例 NiceValidator.
	 * @param validateAttributes 校验属性集合
	 */
	private NiceValidator(List<ValidateAttribute> validateAttributes) {
		this.validateAttributes = validateAttributes;
		//将校验的属性都放入到校验器上下文中
		for(ValidateAttribute validateAttribute : validateAttributes) {
			this.addAttributeToContext(validateAttribute.getAttributeName(), validateAttribute);
		}
	}

	/**    
	 * @Description    构建NiceValidator校验器实例
	 * @param          validateAttributes 校验属性集合
	 * @return         NiceValidator校验器实例
	 */
	public static NiceValidator build(ValidateAttribute... validateAttributes) {
		List<ValidateAttribute> validatorList = new ArrayList<>();
		for (ValidateAttribute validateAttribute : validateAttributes) {
			validatorList.add(validateAttribute);
		}
		return build(validatorList);
	}

	/**    
     * @Description    构建NiceValidator校验器实例
     * @param          validateAttributes 校验属性集合
     * @return         NiceValidator校验器实例
     */
	public static NiceValidator build(List<ValidateAttribute> validateAttributes) {
		return new NiceValidator(validateAttributes);
	}

	/**    
	 * @Description            使用外部属性构建NiceValidator校验器实例
	 * @param extendAttributes 外部属性对象集合
	 * @param convert          外部属性对象转换为内部校验属性对象的转换器
	 * @return                 NiceValidator校验器实例
	 */
	public static <T> NiceValidator buildFromExtendAttribute(List<? extends T> extendAttributes, AttributeConvertor<T> convert) {
		List<ValidateAttribute> attributes = new ArrayList<>();
		for (T extendsAttribute : extendAttributes) {
			ValidateAttribute attr = convert.convert(extendsAttribute);
			attributes.add(attr);
		}
		return build(attributes);
	}

	/**    
	 * @Description        快速失败模式，该模式下，如果一个属性校验不通过，就会立即返回，不会再向后执行其他的属性校验
	 * @return             当前NiceValidator对象
	 */
	public NiceValidator failFast() {
	    LOGGER.debug("设置为fail-fast模式");
		this.failFast = true;
		return this;
	}

	/**    
	 * @Description        失败跳过模式，该模式下，如果一个属性校验不通过，会记录下错误信息，然后向后执行其他的属性校验
	 * @return             当前NiceValidator对象
	 */
	public NiceValidator failOver() {
	    LOGGER.debug("设置为fail-over模式");
		this.failFast = false;
		return this;
	}
	
	/**    
	 * @Description        校验器初始化时，添加到校验上下文中的属性信息，这些信息可以在校验器中获取使用
	 * @param attribure    属性名
	 * @param value        属性值
	 * @return             当前NiceValidator对象
	 */
	public NiceValidator addAttributeToContext(String attribure, Object value) {
		this.validatorContext.addAttribute(attribure, value);
		return this;
	}

	/**    
	 * @Description        校验器初始化时，批量添加到校验上下文中的属性信息，这些信息可以在校验器中获取使用
	 * @param attributes   属性集合
	 * @return             当前NiceValidator对象
	 */
	public NiceValidator addAttributesToContext(Map<String, Object> attributes) {
		this.validatorContext.addAttributes(attributes);
		return this;
	}

	/**    
	 * @Description        开始做校验  
	 * @return             校验结果
	 */
	public ValidateResult doValidate() {
		for (ValidateAttribute validateAttribute : validateAttributes) {

			boolean success = validateAttribute.doValidate(validatorContext);
			
			//如果校验失败并且是快速失败模块，则直接返回校验结果
			if (!success && failFast) {
				return validatorContext.getResult();
			}
		}
		
		return validatorContext.getResult();
	}
}
