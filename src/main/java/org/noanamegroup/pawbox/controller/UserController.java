package org.noanamegroup.pawbox.controller;

import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.UserDTO;
import org.noanamegroup.pawbox.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{

    @Autowired
    UserServiceImpl userServiceImpl;

    // 增加
    @PostMapping("/signup")
    public String signUp(@Validated @RequestBody UserDTO user)
    {
        User userNew = userServiceImpl.signUp(user);
        return Result.getStringSuccess(userNew);
    }

    // 查询
    @GetMapping("/get/{userId}")
    public String getUser(@PathVariable Integer userId)
    {
        User userNew = userServiceImpl.getUser(userId);
        if (userNew != null)
        {
            return Result.getStringSuccess(userNew);
        }
        return Result.getStringFail();
    }

    // 修改
    @PostMapping("/update")
    public String updateUser(@Validated @RequestBody UserDTO user)
    {
        User userNew = userServiceImpl.updateUser(user);
        if (userNew == null)
        {
            return Result.getStringFail();
        }
        return Result.getStringSuccess(userNew);
    }

    // 删除
    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId)
    {
        User userNew = userServiceImpl.deleteUser(userId);
        if (userNew != null)
        {
            return Result.getStringSuccess(userNew);
        }
        return Result.getStringFail();
    }
}
