package org.noanamegroup.pawbox.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.controller.request.PetUpdateRequest;
import org.noanamegroup.pawbox.entity.Pet;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.PetDTO;
import org.noanamegroup.pawbox.service.PetServiceImpl;
import org.noanamegroup.pawbox.service.UserService;
import org.noanamegroup.pawbox.service.UserServiceImpl;
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

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private HttpServletRequest request;
//
//    @Autowired
//    private SessionUtils sessionUtils;

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

    // 获取宠物信息
    @GetMapping("/get")
    public String getPet() {
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
        // 宠物ID等于用户ID（丑陋）
        Integer petId = userId;
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
    @PostMapping("/adopt/{petName}")
    public String adoptPet(@PathVariable String petName) {
        try {
//            // 获取会话，如果不存在则不创建新的会话
//            HttpSession session = request.getSession(false);
//            if (session == null) {
//                // 未登录
//                return Result.error(Result.ResultCode.UNAUTHORIZED);
//            }
//            // 获取当前用户的 userId
//            Integer userId = sessionUtils.getUserId();
            Integer userId = Integer.valueOf(request.getHeader("session"));
//            Integer userId = (Integer) session.getAttribute("userId");
            User user = userServiceImpl.getUser(userId);
            if (user == null) {
                return Result.error(Result.ResultCode.UNAUTHORIZED);
            }

            PetDTO petDTO = new PetDTO();
            // 设置宠物主人ID为当前登录用户
            petDTO.setOwnerId(user.getUserId());
            
            // 设置宠物ID等于用户ID
            petDTO.setPetId(user.getUserId());

            petDTO.setName(petName);
            petDTO.setAvatarUrl("default_avatar.jpg");
            
            Pet pet = petServiceImpl.adoptPet(petDTO);
            return Result.success(pet);
        } catch (IllegalArgumentException e) {
            log.error("Pet adoption failed: {}", e.getMessage());
            return Result.error(Result.ResultCode.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Pet adoption failed: ", e);
            e.printStackTrace();
            return Result.error(Result.ResultCode.INTERNAL_ERROR);
        }
    }

    // 更新宠物信息
    @PostMapping("/update")
    public String updatePet(@RequestBody PetUpdateRequest petUpdateRequest) {
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
        PetDTO petDTO = new PetDTO();
        petDTO.setPetId(userId);
        petDTO.setName(petUpdateRequest.getName());
        petDTO.setAvatarUrl(petUpdateRequest.getAvatarUrl());
        petDTO.setBirthday(petUpdateRequest.getBirthday());
        petDTO.setOwnerId(userId);
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