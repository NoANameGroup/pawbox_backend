package org.noanamegroup.pawbox.service;

import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BoxServiceImpl {
    /**
     * 发送盒子
     *
     * @param boxDTO 盒子信息
     * @param senderId 发送者ID
     * @return Box
     */
    Box sendBox(BoxDTO boxDTO, Integer senderId);

    /**
     * 根据ID查询盒子
     *
     * @param boxId 盒子ID
     * @return Box
     */
    Box getBoxById(Integer boxId);

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
}