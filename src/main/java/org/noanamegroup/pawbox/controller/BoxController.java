package org.noanamegroup.pawbox.controller;

import java.util.List;

import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.service.BoxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/box")
public class BoxController {
    @Autowired
    BoxServiceImpl boxServiceImpl;

    // 获取盒子信息
    @GetMapping("/get/{boxId}")
    public String getBox(@PathVariable Integer boxId)
    {
        Box box = boxServiceImpl.getBox(boxId);
        if (box != null)
        {
            return Result.getStringSuccess(box);
        }
        return Result.getStringFail();
    }

    // 获取用户收到的所有盒子
    @GetMapping("/received/{userId}")
    public String getReceivedBoxes(@PathVariable Integer userId)
    {
        List<Box> boxes = boxServiceImpl.getReceivedBoxes(userId);
        if (boxes != null)
        {
            return Result.getStringSuccess(boxes);
        }
        return Result.getStringFail();
    }

    // 获取用户发送的所有盒子
    @GetMapping("/sent/{userId}")
    public String getSentBoxes(@PathVariable Integer userId)
    {
        List<Box> boxes = boxServiceImpl.getSentBoxes(userId);
        if (boxes != null)
        {
            return Result.getStringSuccess(boxes);
        }
        return Result.getStringFail();
    }

    // 处理图片上传
    @PostMapping("/upload")
    public String handleImageUpload(@RequestParam("file") MultipartFile file) {
        String imageUrl = boxServiceImpl.handleImageUpload(file);
        return Result.getStringSuccess(imageUrl);
    }
}
