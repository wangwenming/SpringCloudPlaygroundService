package com.quanfangtongvip.springcloudplayground.context.response;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;

/**
 * Description: 响应状态
 * ClassName: ResultStatus
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
public class ResultStatus {

    /**
     * 状态码
     */
    private String code;

    /**
     * 错误描述
     */
    private String description;

    public ResultStatus() {
        this(ErrorCode.SUCCESS.getErrorCode(), ErrorCode.SUCCESS.getErrorMsg());
    }

    public ResultStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Status [code=" + code + ", description=" + description + "]";
    }

}
