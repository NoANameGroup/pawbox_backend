package org.noanamegroup.pawbox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;

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
} 