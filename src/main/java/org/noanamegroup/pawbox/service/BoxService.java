package org.noanamegroup.pawbox.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.noanamegroup.pawbox.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.noanamegroup.pawbox.dao.BoxDAO;
import org.noanamegroup.pawbox.dao.UserDAO;
import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;

@Service
public class BoxService implements BoxServiceImpl {
    @Autowired
    private BoxDAO boxDAO;

    @Override
    public Box getBox(Integer boxId)
    {
        return boxDAO.selectById(boxId);
    }

    @Override
    public List<Box> getReceivedBoxes(Integer userId) {
        QueryWrapper<Box> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receiver_id", userId);
        return boxDAO.selectList(queryWrapper);
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
}