package com.quanfangtongvip.springcloudplayground.aspect;

import com.quanfangtongvip.springcloudplayground.context.enums.ErrorCode;
import com.quanfangtongvip.springcloudplayground.context.exception.BaseException;
import com.quanfangtongvip.springcloudplayground.context.response.Result;
import com.quanfangtongvip.springcloudplayground.context.response.ResultStatus;
import com.quanfangtongvip.springcloudplayground.utils.GsonUtils;
import com.quanfangtongvip.springcloudplayground.utils.LogUtils;
import com.quanfangtongvip.springcloudplayground.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

@Aspect
@Order(1)
@Component
@Slf4j
public class HandlerMonitorAspect {
    @Pointcut("execution(public * com.quanfangtongvip.springcloudplayground..controller..*.*(..))")
    public void handlerMonitor() {

    }

    @Around("handlerMonitor()")
    public Object processHandler(ProceedingJoinPoint pjp) {
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 返回结果
        Object result = null;
        HttpServletRequest request = null;

        // 是否抛出异常
        boolean isSuccess = false;
        String shortTraceId = "";

        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            if (request == null) {
                throw new Exception("该方法没有HttpServletRequest参数.");
            }

            String requestUrl = request.getRequestURL().toString();
            String traceId = generateUUIDStr();
            shortTraceId = String.format("[TraceId:%s]", traceId.substring(0, 6));

            result = pjp.proceed();
        } catch (Exception e) {
            // 如果是继承自自定义的 BaseException
            if (BaseException.class.isAssignableFrom(e.getClass())) {
                BaseException baseException = (BaseException) e;
                result = Result.failure(baseException.getErrorCode(), baseException.getDescription());
                log.error(LogUtils.getCommLog(String.format("[%s:%s]业务异常%s:%s", request.getRequestURL().toString(),
                        RequestUtils.getRequest4String(request), shortTraceId, ExceptionUtils.getStackTrace(e))));
            } else {
                // 未知类型错误！
                result = Result.failure(ErrorCode._10002);
                log.error(LogUtils.getCommLog(String.format("[%s:%s]未知错误类型%s:%s", request.getRequestURL().toString(),
                        RequestUtils.getRequest4String(request), shortTraceId, ExceptionUtils.getStackTrace(e))));
            }
        } catch (Throwable throwable) {
            result = Result.failure(ErrorCode._10002);
            log.error(LogUtils.getCommLog(String.format("[%s:%s]未知错误类型%s:%s", request.getRequestURL().toString(),
                    RequestUtils.getRequest4String(request), shortTraceId, ExceptionUtils.getStackTrace(throwable))));
        }

        if (result != null && result.getClass().isAssignableFrom(Result.class)) {
            isSuccess = ((Result) result).isSuccess();
            Result res = (Result) result;
            ResultStatus resultStatus = res.getStatus();
            String description = resultStatus.getDescription();
            // 覆盖原始的错误信息
            if (!isSuccess) {
                description = resultStatus.getDescription() + " " + shortTraceId;
                resultStatus.setDescription(description);
            }
        }

        // 请求耗时
        long costTime = System.currentTimeMillis() - startTime;
        // 记录响应日志，如果是错误响应，则包含跟踪ID
        log.info(getResponseLog(costTime, getResponseContent(result), request.getRequestURL().toString()));

        return result;
    }

    private String getRequestLog(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        // 获取所有的参数
        sb.append("{");
        Enumeration<String> parameters = request.getParameterNames();
        if (parameters != null) {
            while (parameters.hasMoreElements()) {
                String name = parameters.nextElement();
                sb.append(name).append("=").append(request.getParameter(name)).append("; ");
            }
        }
        sb.append("}");
        return String.format("[REQUEST] 线程ID: %s URL: %s Accept[%s] %s", Thread.currentThread().getId(),
                request.getRequestURL(), request.getHeader("Accept"), sb.toString());
    }

    /**
     * 获取响应的日志
     */
    private String getResponseLog(long costTime, String content, String url) {
        return String.format("[RESPONSE] threadId=%s %s [costTime=%s] %s", Thread.currentThread().getId(), url, costTime,
                content);
    }

    /**
     * 根据用户端接受的文件格式，生成对应的格式
     *
     * @param result
     * @return
     */
    private String getResponseContent(Object result) {
        String content = GsonUtils.getInstance().toJson(result);
        return content;
    }

    /**
     * 获取uuid字符串
     *
     * @return
     */
    public static String generateUUIDStr() {
        String uuid = UUID.randomUUID().toString();

        // 去掉“-”符号
        uuid = uuid.replace("-", "");

        return uuid;
    }
}
