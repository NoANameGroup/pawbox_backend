package org.noanamegroup.pawbox.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.controller.request.BoxSendRequest;
import org.noanamegroup.pawbox.controller.request.UserUpdateRequest;
import org.noanamegroup.pawbox.controller.response.UserResponse;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.noanamegroup.pawbox.entity.dto.SessionData;
import org.noanamegroup.pawbox.entity.dto.UserDTO;
import org.noanamegroup.pawbox.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    // 日志
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    SessionUtils sessionUtils;
//
//    @Autowired
//    RedisUtils redisUtils;

    // 登录
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginInfo) {

        String email = loginInfo.get("email");
        String password = loginInfo.get("password");
        
        User user = userServiceImpl.login(email, password);
        if (user != null) {
//            // 获取会话，如果不存在则创建一个新的会话
//            HttpSession session = request.getSession(true);
//            // 登录成功,将用户信息存入session
//            session.setAttribute("userId", user.getUserId());
//            // 设置认证信息
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                user.getEmail(),
//                null,
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            //生成 sessionId 和 sessionData，分别存入 sessionUtils 和 redisUtils 中，设置过期时间为 86400 秒
//            String sessionId = sessionUtils.generateSessionId();
//            SessionData sessionData = new SessionData(user);
//            sessionUtils.setSessionId(sessionId);
//            redisUtils.set(String.valueOf(user.getUserId()), sessionId, 86400);
//            redisUtils.set(sessionId, sessionData, 86400);

            UserResponse userResponse = new UserResponse(user, user.getUserId());

            return Result.loginSuccess(userResponse);
        }
        return Result.loginFail();
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        try {
            User user = userServiceImpl.register(userDTO);
//            // 获取会话，如果不存在则创建一个新的会话
//            HttpSession session = request.getSession(true);
//            // 注册成功,将用户信息存入session
//            session.setAttribute("user", user);

            //生成 sessionId 和 sessionData，分别存入 sessionUtils 和 redisUtils 中，设置过期时间为 86400 秒
//            String sessionId = sessionUtils.generateSessionId();
//            SessionData sessionData = new SessionData(user);
//            sessionUtils.setSessionId(sessionId);
//            redisUtils.set(String.valueOf(user.getUserId()), sessionId, 86400);
//            redisUtils.set(sessionId, sessionData, 86400);

            UserResponse userResponse = new UserResponse(user, user.getUserId());
            return Result.success(userResponse);
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
    public String logout() {
//        // 获取会话，如果不存在则不创建新的会话
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            // 未登录
//            return Result.error(Result.ResultCode.UNAUTHORIZED);
//        }
//        session.invalidate();
//        sessionUtils.invalidate();
        return Result.success("Logout successful");
    }

    // 更新用户信息
    @PostMapping("/update")
    public String updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {

//        // 获取会话，如果不存在则不创建新的会话
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            // 未登录
//            return Result.error(Result.ResultCode.UNAUTHORIZED);
//        }
//        Integer userId = (Integer) session.getAttribute("userId");
        // 获取当前用户的 userId
        Integer userId = Integer.valueOf(request.getHeader("session"));
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(userId);
        userDTO.setUsername(userUpdateRequest.getUsername());
        userDTO.setEmail(userUpdateRequest.getEmail());
        userDTO.setPassword(userUpdateRequest.getPassword());
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
    public String getCurrentUser() {
//        // 获取会话，如果不存在则不创建新的会话
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            // 未登录
//            return Result.error(Result.ResultCode.UNAUTHORIZED);
//        }
//        Integer userId = (Integer) session.getAttribute("userId");
//        // 获取当前用户的 userId
//        Integer userId = sessionUtils.getUserId();
        Integer userId = Integer.valueOf(request.getHeader("session"));
        User user = userServiceImpl.getUser(userId);

        if (user != null) {
            return Result.success(user);
        }
        return Result.error(Result.ResultCode.UNAUTHORIZED);
    }

    // 发送盒子
    @PostMapping("/send")
    public String sendBox(@Validated @RequestBody BoxSendRequest boxSendRequest) {
//        // 获取会话，如果不存在则不创建新的会话
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            // 未登录
//            return Result.error(Result.ResultCode.UNAUTHORIZED);
//        }
//        Integer userId = (Integer) session.getAttribute("userId");
//        // 获取当前用户的 userId
//        Integer userId = sessionUtils.getUserId();
        Integer userId = Integer.valueOf(request.getHeader("session"));
        User user = userServiceImpl.getUser(userId);
        if (user == null) {
            return Result.error(Result.ResultCode.UNAUTHORIZED);
        }

        BoxDTO boxDTO = new BoxDTO();
        boxDTO.setSenderId(user.getUserId());
        boxDTO.setContent(boxSendRequest.getContent());
        boxDTO.setImageUrl(boxSendRequest.getImageUrl());

        
        Box box = userServiceImpl.sendBox(boxDTO, user.getUserId());
        return Result.success(box);
    }

    // 接受盒子
    @PostMapping("/receive")
    public String receiveBox(@RequestParam Integer userId, @RequestParam Integer boxId)
    {
        Box box = userServiceImpl.receiveBox(boxId, userId);
        if (box != null)
        {
            return Result.success(box);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }
}
