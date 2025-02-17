package com.noanamegroup.boxbuddy;

import com.alibaba.fastjson2.JSONObject;
import com.noanamegroup.boxbuddy.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Result
{
    public static String getStringSuccess(User user)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "success");
        map.put("data", user);
        return JSONObject.toJSONString(map);
    }

    public static String getStringFail()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 404);
        map.put("message", "failed");
        map.put("data", null);
        return JSONObject.toJSONString(map);
    }
}
