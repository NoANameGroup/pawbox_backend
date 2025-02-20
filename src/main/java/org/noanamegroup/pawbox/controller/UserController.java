package org.noanamegroup.pawbox.controller;

import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.noanamegroup.pawbox.entity.dto.UserDTO;
import org.noanamegroup.pawbox.service.BoxServiceImpl;
import org.noanamegroup.pawbox.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userServiceImpl;

    // 注册用户
    @PostMapping("/register")
    public String register(@Validated @RequestBody UserDTO user)
    {
        try {
            User userNew = userServiceImpl.register(user);
            return Result.success(userNew);
        } catch (Exception e) {
            // 添加日志
            log.error("Registration failed: ", e);
            return Result.error(Result.ResultCode.INTERNAL_ERROR);
        }
    }

    // 查询用户信息
    @GetMapping("/get/{userId}")
    public String getUser(@PathVariable Integer userId)
    {
        User userNew = userServiceImpl.getUser(userId);
        if (userNew != null)
        {
            return Result.success(userNew);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 修改用户信息
    @PostMapping("/update")
    public String updateUser(@Validated @RequestBody UserDTO user)
    {
        User userNew = userServiceImpl.updateUser(user);
        if (userNew == null)
        {
            return Result.error(Result.ResultCode.NOT_FOUND);
        }
        return Result.success(userNew);
    }

    // 删除用户
    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId)
    {
        User userNew = userServiceImpl.deleteUser(userId);
        if (userNew != null)
        {
            return Result.success(userNew);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 登录
    @PostMapping("/login")
    public String login(@Validated @RequestBody UserDTO user)
    {

        User userNew = userServiceImpl.login(user);
        if (userNew != null)
        {
            return Result.loginSucess(userNew);
        }
        return Result.loginFail();
    }

    // 发送盒子
    @PostMapping("/send")
    public String sendBox(@Validated @RequestBody BoxDTO boxDTO, @RequestParam Integer senderId)
    {
        Box box = userServiceImpl.sendBox(boxDTO, senderId);
        return Result.success(box);
    }

    // 接受盒子
    @PostMapping("/receive/{userId}/{boxId}")
    public String receiveBox(@PathVariable Integer boxId, @RequestParam Integer receiverId)
    {
        Box box = userServiceImpl.receiveBox(boxId, receiverId);
        if (box != null)
        {
            return Result.success(box);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }
}
