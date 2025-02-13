package com.noanamegroup.boxbuddy.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

public class UserDTO
{
    @NotBlank(message = "用户名不能为空！") // 去除空格
    private String username;
    @NotEmpty(message = "密码不能为空！")
    @Length(min = 6, message = "密码长度不能小于6位！") // 密码长度
    private String password;
    @Email(message = "邮箱格式不正确！") // 邮箱格式
    private String email;
    // @NumberFormat
    private String phoneNumber;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
