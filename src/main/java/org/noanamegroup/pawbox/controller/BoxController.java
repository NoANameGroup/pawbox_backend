package org.noanamegroup.pawbox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.noanamegroup.pawbox.service.BoxServiceImpl;
import org.noanamegroup.pawbox.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/box")
public class BoxController {
    @Autowired
    BoxServiceImpl boxServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;
//
//    @Autowired
//    SessionUtils sessionUtils;

    @Autowired
    private HttpServletRequest request;

    // 获取随机盒子
    @GetMapping("/random")
    public String getRandomBox() {
//        // 获取会话，如果不存在则不创建新的会话
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            // 未登录
//            return Result.error(Result.ResultCode.UNAUTHORIZED);
//        }
//        Integer receiverId = (Integer) session.getAttribute("userId");
//        // 获取当前用户的 userId
//        Integer receiverId = sessionUtils.getUserId();
        Integer receiverId = Integer.valueOf(request.getHeader("session"));
        Box box = boxServiceImpl.getRandomBox(receiverId);
        if (box != null) {
            // 创建简化的返回对象
            Map<String, Object> boxInfo = new HashMap<>();
            boxInfo.put("boxId", box.getBoxId());
            boxInfo.put("content", box.getContent());
            boxInfo.put("imageUrl", box.getImageUrl());
            boxInfo.put("senderId", box.getSenderId()); 
            return Result.success(boxInfo);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

//    // 发送盒子
//    @PostMapping("/send")
//    public String sendBox(@RequestBody BoxDTO boxDTO, ) {
//        try {
//            // 获取会话，如果不存在则不创建新的会话
//            HttpSession session = request.getSession(false);
//            if (session == null) {
//                // 未登录
//                return Result.error(Result.ResultCode.UNAUTHORIZED);
//            }
//            Integer userId = (Integer) session.getAttribute("userId");
//            User user = userServiceImpl.getUser(userId);
//            if (user == null) {
//                return Result.error(Result.ResultCode.UNAUTHORIZED);
//            }
//
//            // 设置发送者ID为当前登录用户
//            boxDTO.setSenderId(user.getUserId());
//
//            Box box = boxServiceImpl.sendBox(boxDTO);
//            if (box != null) {
//                return Result.success(box);
//            }
//            return Result.error(Result.ResultCode.INTERNAL_ERROR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error(Result.ResultCode.INTERNAL_ERROR);
//        }
//    }

    // 获取盒子信息
    @GetMapping("/get/{boxId}")
    public String getBox(@PathVariable Integer boxId)
    {
        Box box = boxServiceImpl.getBox(boxId);
        if (box != null)
        {
            return Result.success(box);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 获取用户收到的所有盒子
    @GetMapping("/received")
    public String getReceivedBoxes()
    {
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
        List<Box> boxes = boxServiceImpl.getReceivedBoxes(userId);
        if (boxes != null)
        {
            return Result.success(boxes);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 获取用户发送的所有盒子
    @GetMapping("/sent")
    public String getSentBoxes()
    {
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
        List<Box> boxes = boxServiceImpl.getSentBoxes(userId);
        if (boxes != null)
        {
            return Result.success(boxes);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 处理图片上传
    @PostMapping("/upload")
    public String handleImageUpload(@RequestParam("file") MultipartFile file) {
        String imageUrl = boxServiceImpl.handleImageUpload(file);
        return Result.success(imageUrl);
    }
}
