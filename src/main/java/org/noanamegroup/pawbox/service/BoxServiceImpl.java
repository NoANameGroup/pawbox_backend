package org.noanamegroup.pawbox.service;

import java.util.List;

import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface BoxServiceImpl {

    /**
     * 获取盒子
     *
     * @param boxId 盒子ID
     * @return Box
     */
    Box getBox(Integer boxId);

    /**
     * 获取用户收到的所有盒子
     *
     * @param userId 用户ID
     * @return List<Box>
     */
    List<Box> getReceivedBoxes(Integer userId);

    /**
     * 获取用户发送的所有盒子
     *
     * @param userId 用户ID
     * @return List<Box>
     */
    List<Box> getSentBoxes(Integer userId);

    /**
     * 处理图片上传
     *
     * @param file 图片文件
     * @return 图片URL
     */
    String handleImageUpload(MultipartFile file);

    Box getRandomBox(Integer receiverId);
    Box sendBox(BoxDTO boxDTO);

}