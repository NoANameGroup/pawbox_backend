package org.noanamegroup.pawbox.controller;

import org.noanamegroup.pawbox.Result;
import org.noanamegroup.pawbox.entity.Pet;
import org.noanamegroup.pawbox.entity.dto.PetDTO;
import org.noanamegroup.pawbox.service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetServiceImpl petServiceImpl;

    // 领养宠物
    @PostMapping("/adopt")
    public String adoptPet(@Validated @RequestBody PetDTO petDTO) {
        Pet pet = petServiceImpl.adoptPet(petDTO);
        return Result.success(pet);
    }

    // 更新宠物信息
    @PostMapping("/update")
    public String updatePet(@Validated @RequestBody PetDTO petDTO) {
        Pet pet = petServiceImpl.updatePet(petDTO);
        if (pet == null) {
            return Result.error(Result.ResultCode.NOT_FOUND);
        }
        return Result.success(pet);
    }

    // 更新宠物头像
    @PostMapping("/avatar")
    public String updateAvatar(@RequestParam("file") MultipartFile file) {
        String avatarUrl = petServiceImpl.updateAvatar(file);
        return Result.success(avatarUrl);
    }

    // 获取宠物信息
    @GetMapping("/get/{petId}")
    public String getPet(@PathVariable Integer petId) {
        Pet pet = petServiceImpl.getPet(petId);
        if (pet != null) {
            return Result.success(pet);
        }
        return Result.error(Result.ResultCode.NOT_FOUND);
    }
} 