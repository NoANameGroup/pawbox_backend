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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userServiceImpl;

    // 登录
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginInfo, HttpSession session) {
        String email = loginInfo.get("email");
        String password = loginInfo.get("password");
        
        User user = userServiceImpl.login(email, password);
        if (user != null) {
            // 登录成功,将用户信息存入session
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60); // session过期时间设为30分钟
            return Result.loginSuccess(user);
        }
        return Result.loginFail();
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        try {
            User user = userServiceImpl.register(userDTO);
            return Result.success(user);
        } catch (Exception e) {
            log.error("Registration failed: ", e);
            return Result.error(Result.ResultCode.INTERNAL_ERROR);
        }
    }

    // 获取用户信息
    @GetMapping("/get/{userId}")
    public String getUser(@PathVariable Integer userId) {
        User user = userServiceImpl.getUser(userId);
        if (user != null) {
            // 创建简化的返回对象
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", user.getUserId());
            userInfo.put("username", user.getUsername());
            userInfo.put("email", user.getEmail());
            return Result.success(userInfo);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 登出
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return Result.success("Logout successful");
    }

    // 更新用户信息
    @PostMapping("/update")
    public String updateUser(@RequestBody UserDTO userDTO) {
        User user = userServiceImpl.updateUser(userDTO);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
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

    // 获取当前登录用户
    @GetMapping("/current")
    public String getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return Result.success(user);
        }
        return Result.error(Result.ResultCode.UNAUTHORIZED);
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
