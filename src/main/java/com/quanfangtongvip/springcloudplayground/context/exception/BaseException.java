package com.quanfangtongvip.springcloudplayground.context.exception;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;

/**
 * Description: 基础异常类
 * ClassName: BaseException
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -8964389197754846174L;

    private String errorCode;

    private String description;

    public BaseException() {

    }

    public BaseException(String errorCode, String description) {
        super(description);
        this.errorCode = errorCode;
        this.description = description;
    }

    public BaseException(ErrorCode error) {
        super(error.getErrorMsg());
        this.errorCode = error.getErrorCode();
        this.description = error.getErrorMsg();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
