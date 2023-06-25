package com.codeartist.component.core.web.handler;

import com.codeartist.component.core.entity.ResponseError;
import com.codeartist.component.core.entity.enums.ApiErrorCode.ApiHttpStatus;
import com.codeartist.component.core.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * 客户端异常处理
 *
 * @author 艾江南
 * @date 2022/4/22
 */
@Slf4j
@Order(1)
@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> constraintViolationException(ConstraintViolationException e) {
        return badRequest(new BadRequestException(e.getMessage()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseError> bindException(BindException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return badRequest(new BadRequestException(errors.get(0).getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        ObjectError error = errors.get(0);
        String name = Optional.ofNullable(errors.get(0))
                .map(DefaultMessageSourceResolvable::getArguments)
                .map(args -> (DefaultMessageSourceResolvable) args[0])
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("");
        return badRequest(new BadRequestException(name + " " + error.getDefaultMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseError> badRequestException(BadRequestException e) {
        return badRequest(e);
    }

    private ResponseEntity<ResponseError> badRequest(BadRequestException e) {
        log.warn("Client exception: {}", e.getMessage());
        return ResponseEntity
                .status(ApiHttpStatus.CLIENT_WARNING.getValue())
                .body(new ResponseError(ApiHttpStatus.CLIENT_WARNING.getValue(), e.getMessage()));
    }
}
