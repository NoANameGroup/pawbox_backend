package org.noanamegroup.pawbox.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", "服务器内部错误: " + e.getMessage());
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 404);
        map.put("message", e.getMessage());
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("message", "参数错误: " + e.getMessage());
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 403);
        map.put("message", "访问被拒绝");
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException(AuthenticationException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("message", "认证失败");
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }
} 