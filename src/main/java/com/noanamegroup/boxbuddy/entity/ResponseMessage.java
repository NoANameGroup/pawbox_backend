package com.noanamegroup.boxbuddy.entity;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T>
{
    private Integer code;
    private String message;
    private T data; //Object不是更香吗哈哈哈哈

    public ResponseMessage(Integer code, String message, T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    // 请求成功
    public static <T> ResponseMessage<T> success(T data)
    {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }

    public static <T> ResponseMessage<T> success()
    {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", null);
    }

    // 请求失败
    public static <T> ResponseMessage<T> error(T data)
    {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", data);
    }
}
