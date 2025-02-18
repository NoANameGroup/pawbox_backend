package org.noanamegroup.pawbox.controller;

import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.noanamegroup.pawbox.service.BoxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {
    @Autowired
    BoxServiceImpl boxServiceImpl;

    // 发送盒子
    @PostMapping("/send/{senderId}")
    public String sendBox(@Validated @ModelAttribute BoxDTO boxDTO, @PathVariable Integer senderId) {
        Box box = boxServiceImpl.sendBox(boxDTO, senderId);
        return Result.getStringSuccess(box);
    }

    // 查询单个盒子
    @GetMapping("/get/{boxId}")
    public String getBox(@PathVariable Integer boxId) {
        Box box = boxServiceImpl.getBoxById(boxId);
        if (box != null) {
            return Result.getStringSuccess(box);
        }
        return Result.getStringFail();
    }

    // 获取用户收到的盒子
    @GetMapping("/received/{userId}")
    public String getReceivedBoxes(@PathVariable Integer userId) {
        List<Box> boxes = boxServiceImpl.getReceivedBoxes(userId);
        return Result.getStringSuccess(boxes);
    }

    // 获取用户发送的盒子
    @GetMapping("/sent/{userId}")
    public String getSentBoxes(@PathVariable Integer userId) {
        List<Box> boxes = boxServiceImpl.getSentBoxes(userId);
        return Result.getStringSuccess(boxes);
    }
}
