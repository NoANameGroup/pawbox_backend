package org.noanamegroup.pawbox.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.Pet;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.PetDTO;
import org.noanamegroup.pawbox.service.PetServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/pet")
public class PetController {
    private static final Logger log = LoggerFactory.getLogger(PetController.class);
    
    @Autowired
    private PetServiceImpl petServiceImpl;

    // 获取宠物信息
    @GetMapping("/get/{petId}")
    public String getPet(@PathVariable Integer petId) {
        Pet pet = petServiceImpl.getPet(petId);
        if (pet != null) {
            // 创建简化的返回对象
            Map<String, Object> petInfo = new HashMap<>();
            petInfo.put("petId", pet.getPetId());
            petInfo.put("name", pet.getName());
            petInfo.put("birthday", pet.getBirthday());
            return Result.success(petInfo);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 领养宠物
    @PostMapping("/adopt")
    public String adoptPet(@RequestBody PetDTO petDTO, HttpSession session) {
        try {
            // 从session获取当前用户
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return Result.error(Result.ResultCode.UNAUTHORIZED);
            }
            
            // 设置宠物主人ID为当前登录用户
            petDTO.setOwnerId(user.getUserId());
            
            // 自动生成生日
            petDTO.setBirthday(LocalDateTime.now());
            Pet pet = petServiceImpl.adoptPet(petDTO);
            return Result.success(pet);
        } catch (Exception e) {
            log.error("Pet adoption failed: ", e);
            return Result.error(Result.ResultCode.INTERNAL_ERROR);
        }
    }

    // 更新宠物信息
    @PostMapping("/update")
    public String updatePet(@RequestBody PetDTO petDTO) {
        Pet pet = petServiceImpl.updatePet(petDTO);
        if (pet != null) {
            return Result.success(pet);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }

    // 更新宠物头像
    @PostMapping("/avatar")
    public String updateAvatar(@RequestParam("file") MultipartFile file) {
        String avatarUrl = petServiceImpl.updateAvatar(file);
        return Result.success(avatarUrl);
    }
} 