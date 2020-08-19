package com.quanfangtongvip.springcloudplayground.context.exception;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;

/**
 * Description: 缺少参数异常类
 * ClassName: BaseException
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
public class LackParameterException extends BaseException {

    private static final long serialVersionUID = -7305673475606021068L;

    public LackParameterException(String description) {
        super(ErrorCode._10008.getErrorCode(), description);
    }
}
