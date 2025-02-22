package org.noanamegroup.pawbox.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.noanamegroup.pawbox.dao.BoxDAO;
import org.noanamegroup.pawbox.dao.UserDAO;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class BoxService implements BoxServiceImpl {
    @Autowired
    private BoxDAO boxDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public Box getBox(Integer boxId)
    {
        return boxDAO.selectById(boxId);
    }

    @Override
    public List<Box> getReceivedBoxes(Integer userId) {
        // 使用 JOIN 查询通过中间表获取用户收到的盒子
        return boxDAO.getReceivedBoxesByUserId(userId);
    }

    @Override
    public List<Box> getSentBoxes(Integer userId) {
        QueryWrapper<Box> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sender_id", userId);
        return boxDAO.selectList(queryWrapper);
    }

    @Override
    public String handleImageUpload(MultipartFile file) {
        try {
            // 创建上传目录
            String uploadDir = "uploads/images";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;

            // 保存文件
            File destFile = new File(dir.getAbsolutePath() + File.separator + filename);
            file.transferTo(destFile);

            // 返回访问URL
            return "/uploads/images/" + filename;
        } catch (Exception e) {
            throw new RuntimeException("图片上传失败", e);
        }
    }

    @Override
    public Box getRandomBox(Integer receiverId) {
        try {
            // 获取一个随机的未被接收的盒子
            QueryWrapper<Box> queryWrapper = new QueryWrapper<>();
            queryWrapper.notExists("SELECT 1 FROM user_received_boxes urb WHERE urb.box_id = box.boxId");
            queryWrapper.last("ORDER BY RAND() LIMIT 1");
            Box box = boxDAO.selectOne(queryWrapper);
            
            if (box != null) {
                // 获取接收者
                User receiver = userDAO.selectById(receiverId);
                if (receiver == null) {
                    throw new RuntimeException("接收者不存在");
                }
                
                try {
                    // 直接插入中间表记录
                    boxDAO.insertUserReceivedBox(receiverId, box.getBoxId());
                } catch (Exception e) {
                    throw new RuntimeException("保存接收记录失败", e);
                }
            }
            return box;
        } catch (Exception e) {
            throw new RuntimeException("获取随机盒子失败", e);
        }
    }

    @Override
    public Box sendBox(BoxDTO boxDTO) {
        try {
            // 创建盒子
            Box box = new Box();
            box.setContent(boxDTO.getContent());
            box.setImageUrl(boxDTO.getImageUrl());
            box.setCreateTime(LocalDateTime.now());
            box.setSenderId(boxDTO.getSenderId());
            
            // 保存盒子
            boxDAO.insert(box);
            return box;
        } catch (Exception e) {
            throw new RuntimeException("发送盒子失败", e);
        }
    }
}