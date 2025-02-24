package org.noanamegroup.pawbox;

import java.util.HashMap;
import java.util.Map;

import org.noanamegroup.pawbox.controller.response.UserResponse;
import org.noanamegroup.pawbox.entity.User;

import com.alibaba.fastjson2.JSONObject;

import lombok.Getter;

public class Result
{
    @Getter
    public enum ResultCode {
        SUCCESS(0, "操作成功"),
        NOT_FOUND(404, "资源未找到"),
        UNAUTHORIZED(401, "未授权"),
        FORBIDDEN(403, "禁止访问"),
        INTERNAL_ERROR(500, "服务器内部错误"),
        BAD_REQUEST(444, "坏的请求");
        
        private final int code;
        private final String message;
        
        ResultCode(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    public static String success(Object data)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ResultCode.SUCCESS.getCode());
        map.put("message", ResultCode.SUCCESS.getMessage());
        map.put("data", data);
        return JSONObject.toJSONString(map);
    }

    public static String error(ResultCode resultCode)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", resultCode.getCode());
        map.put("message", resultCode.getMessage());
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }

    public static String loginSuccess(UserResponse user)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "login successfully");
        map.put("data", user);
        return JSONObject.toJSONString(map);
    }

    public static String loginFail()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 404);
        map.put("message", "login failed");
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }

    public static String logoutSuccess()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "logout successfully");
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }
}
