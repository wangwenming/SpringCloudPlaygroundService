package com.quanfangtongvip.springcloudplayground.context.response;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;
import lombok.Builder;
import lombok.Data;

/**
 * Description: 响应结果
 * ClassName: Result
 * date: 2018年11月11日
 *
 * @author ygc
 */
@Data
@Builder
public class Result<T> {

    /**
     * 响应状态
     */
    private ResultStatus status;

    /**
     * 响应结果
     */
    private T result;

    public Result() {
        status = new ResultStatus();
    }

    public Result(T result) {
        status = new ResultStatus();
        this.result = result;
    }

    public Result(String code, String description) {
        this(code, description, null);
    }

    public Result(String code, String description, T result) {
        this(new ResultStatus(code, description), result);
    }

    public Result(ResultStatus status, T result) {
        this.status = status;
        this.result = result;
    }

    /**
     * 返回成功的对象
     *
     * @return Result
     */
    public static Result success() {
        return new Result();
    }

    /**
     * 返回成功的对象
     *
     * @param data
     * @return Result
     */
    public static <T> Result success(T data) {
        return new Result(data);
    }

    /**
     * 返回失败对象
     *
     * @param error
     * @return Result
     */
    public static Result failure(ErrorCode error) {
        return new Result(error.getErrorCode(), error.getErrorMsg());
    }

    /**
     * 返回失败对象
     *
     * @param error
     * @param data
     * @return Result
     */
    public static Result failure(ErrorCode error, Object data) {
        return new Result(error.getErrorCode(), error.getErrorMsg(), data);
    }

    /**
     * 返回失败对象
     *
     * @param errorCode
     * @return Result
     */
    public static Result failure(String errorCode) {
        return new Result(ErrorCode.code(errorCode), ErrorCode.msg(errorCode));
    }

    /**
     * 返回失败对象
     *
     * @param errorCode
     * @param errorMsg
     * @return Result
     */
    public static Result failure(String errorCode, String errorMsg) {
        return new Result(errorCode, errorMsg);
    }

    /**
     * 返回失败对象
     *
     * @param errorCode
     * @param errorMsg
     * @return Result
     */
    public static Result failureWithMsg(String errorCode, String errorMsg) {
        return new Result(ErrorCode.code(errorCode), errorMsg + ErrorCode.msg(errorCode));
    }

    /**
     * 返回失败对象
     *
     * @param error
     * @param errorMsg
     * @return Result
     */
    public static Result failureWithMsg(ErrorCode error, String errorMsg) {
        return new Result(error.getErrorCode(), errorMsg + error.getErrorMsg());
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param error
     * @param error
     * @return Result
     */
    public static Result handleResult(boolean flag, ErrorCode error) {
        return flag ? Result.success(new Object()) : Result.failure(error);
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param error
     * @return Result
     */
    public static Result handleResult(int flag, ErrorCode error) {
        return handleResult(flag, new Object(), error);
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param data
     * @param error
     * @return Result
     */
    public static Result handleResult(boolean flag, Object data, ErrorCode error) {
        return flag ? Result.success(data) : Result.failure(error);
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param error
     * @return Result
     */
    public static Result handleResult(int flag, Object data, ErrorCode error) {
        return handleResult(flag > 0, data, error);
    }

    public boolean isSuccess() {
        return this.status.getCode().equals(ErrorCode.SUCCESS.getErrorCode());
    }

}