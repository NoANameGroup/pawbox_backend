package org.noanamegroup.pawbox.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;



//  hyjffhmfhmfhm123
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
    private Integer userId;
    @NotBlank(message = "用户名不能为空！") // 去除空格
    private String username;
    @NotEmpty(message = "密码不能为空！")
    @Length(min = 6, message = "密码长度不能小于6位！") // 密码长度
    private String password;
    @Email(message = "邮箱格式不正确！") // 邮箱格式
    private String email;
}