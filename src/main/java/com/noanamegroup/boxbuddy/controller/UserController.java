package com.noanamegroup.boxbuddy.controller;

import com.noanamegroup.boxbuddy.entity.ResponseMessage;
import com.noanamegroup.boxbuddy.entity.User;
import com.noanamegroup.boxbuddy.entity.dto.UserDTO;
import com.noanamegroup.boxbuddy.service.UserServiceImpl;
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
    @PostMapping
    public ResponseMessage addUser(@Validated @RequestBody UserDTO user)
    {
        User userNew = userServiceImpl.addUser(user);
        return ResponseMessage.success(userNew);
    }

    // 查询
    @GetMapping("/{userId}")
    public ResponseMessage getUser(@PathVariable Integer userId)
    {
        User userNew = userServiceImpl.getUser(userId);
        return ResponseMessage.success(userNew);
    }

    // 修改
    @PutMapping("/update")
    public ResponseMessage updateUser(@Validated @RequestBody UserDTO user)
    {
        User userNew = userServiceImpl.updateUser(user);
        return ResponseMessage.success(userNew);
    }

    // 删除
    //@DeleteMapping("/delete")
}
