package com.codeartist.component.core.web.handler;

import com.codeartist.component.core.entity.ResponseError;
import com.codeartist.component.core.entity.enums.ApiErrorCode;
import com.codeartist.component.core.entity.enums.Environments;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.core.exception.FeignException;
import com.codeartist.component.core.support.metric.Metrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 服务端异常处理
 *
 * @author 艾江南
 * @date 2020/9/11
 */
@Slf4j
@Order(2)
@ControllerAdvice
@RequiredArgsConstructor
public class ServerExceptionHandler {

    private final Metrics metrics;
    private final Environment environment;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> businessException(BusinessException e) {
        log.warn("Business exception: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(e.getCode() == null ?
                        new ResponseError(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage()) :
                        new ResponseError(e.getCode())
                );
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ResponseError> feignException(FeignException e) {
        log.warn("Feign exception: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(e.getResponseError());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exception(Exception e, HttpServletRequest request) {
        log.error("Server exception", e);
        String method = request.getMethod();
        String uri = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        metrics.counter("api_error", "method", method, "uri", uri);
        ResponseError error = new ResponseError(ApiErrorCode.SERVICE_ERROR);
        error.setStackTrace(trace(e, method, uri));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    private String trace(Exception e, String method, String uri) {
        if (Environments.PROD.is()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("API: ").append(method).append(" ").append(uri).append("\n");
        printStackTrace(sb, e);
        return sb.toString();
    }

    private void printStackTrace(StringBuilder message, Throwable e) {
        message.append(e).append("\n");
        for (StackTraceElement traceElement : e.getStackTrace()) {
            message.append("\tat ").append(traceElement).append("\n");
        }
        for (Throwable se : e.getSuppressed()) {
            printStackTrace(message, se);
        }
        if (e.getCause() != null) {
            printStackTrace(message, e.getCause());
        }
    }
}
