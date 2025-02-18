package org.noanamegroup.pawbox;

import com.alibaba.fastjson2.JSONObject;
import org.noanamegroup.pawbox.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Result
{
    public static String getStringSuccess(Object data)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "success to get user");
        map.put("data", data);
        return JSONObject.toJSONString(map);
    }

    public static String getStringFail()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 404);
        map.put("message", "failed to get user");
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }

    public static String loginSucess(User user)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
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
