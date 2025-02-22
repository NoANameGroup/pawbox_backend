package org.noanamegroup.pawbox.service;

import org.noanamegroup.pawbox.entity.Pet;
import org.noanamegroup.pawbox.entity.dto.PetDTO;
import org.springframework.web.multipart.MultipartFile;

public interface PetServiceImpl {
    /**
     * 领养宠物
     */
    Pet adoptPet(PetDTO petDTO);

    /**
     * 更新宠物信息
     */
    Pet updatePet(PetDTO petDTO);

    /**
     * 更新宠物头像
     */
    String updateAvatar(MultipartFile file);

    /**
     * 获取宠物信息
     */
    Pet getPet(Integer petId);
} 