package com.noanamegroup.boxbuddy.exception;

import com.noanamegroup.boxbuddy.entity.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice
{

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler({Exception.class})
    public ResponseMessage handleException(Exception e, HttpServletResponse response, HttpServletRequest request)
    {
        // 记录日志
        log.error("统一异常： ", e);

        return new ResponseMessage(500, "error", null);
    }
}
