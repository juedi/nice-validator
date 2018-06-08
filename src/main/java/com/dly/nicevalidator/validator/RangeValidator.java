package com.dly.nicevalidator.validator;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;

/**
 * @typename: RangeValidator
 * @brief: 范围校验器
 * @author: dly
 * @date: 2018年6月5日 上午11:31:21
 * @version: 1.0.0
 * @since
 * 
 */
public class RangeValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RangeValidator.class);
    
    private BigDecimal min;
    private BigDecimal max;
    private boolean includeBoundary;

    /**
     * 创建一个新的实例 RangeValidator.
     */
    public RangeValidator(BigDecimal min, BigDecimal max, boolean includeBoundary) {
        this.min = min;
        this.max = max;
        this.includeBoundary = includeBoundary;
        
        if(min == null && max == null){
            LOGGER.error("最大值和最小值不可同时为空");
            throw new IllegalArgumentException("最大值和最小值不可同时为空");
        }
        
        if(min != null && max != null && min.compareTo(max) >= 0 && !includeBoundary){
            LOGGER.error("最大值不可小于最小值");
            throw new IllegalArgumentException("最大值不可小于最小值");
        }
    }

    @Override
    public boolean validate(ValidatorContext context, ValidateAttribute element) {
        // 空值不做处理
        if (element.getValue() == null) {
            return true;
        }

        // 非数字类型校验失败
        if (!(element.getValue() instanceof Number)) {
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写数字"));
            return false;
        }

        BigDecimal value = new BigDecimal(element.getValue().toString());

        if (includeBoundary) {
            // 超过最大值
            if (min == null && max.compareTo(value) < 0) {
                context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写不大于%s的数", max)));
                return false;
            }
            // 低于最小值
            if (max == null && min.compareTo(value) > 0) {
                context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写不小于%s的数", min)));
                return false;
            }
            // 超过最大值或低于最小值
            if (min != null && max != null 
                    && (min.compareTo(value) > 0 || max.compareTo(value) < 0)) {
                context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写%s到%s的数", min, max)));
                return false;
            }
        } else {
            // 超过最大值
            if (min == null && max.compareTo(value) <= 0) {
                context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写小于%s的数", max)));
                return false;
            }
            // 低于最小值
            if (max == null && min.compareTo(value) >= 0) {
                context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写大于%s的数", min)));
                return false;
            }
            // 超过最大值或低于最小值
            if (min != null && max != null 
                    && (min.compareTo(value) >= 0 || max.compareTo(value) <= 0)) {
                context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写%s到 %s的数（不包含边界值）", min, max)));
                return false;
            }
        }

        return true;
    }

}
