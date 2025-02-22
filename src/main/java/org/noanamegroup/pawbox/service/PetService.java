package org.noanamegroup.pawbox.service;

import java.time.LocalDateTime;

import org.noanamegroup.pawbox.dao.PetDAO;
import org.noanamegroup.pawbox.dao.UserDAO;
import org.noanamegroup.pawbox.entity.Pet;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.PetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetService implements PetServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private PetDAO petDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BoxService boxService;

    @Override
    @Transactional
    public Pet adoptPet(PetDTO petDTO) {
        try {
            // 1. 检查用户是否存在
            User owner = userDAO.selectById(petDTO.getOwnerId());
            if (owner == null) {
                throw new IllegalArgumentException("User not found: " + petDTO.getOwnerId());
            }

            // 2. 检查用户是否已经有宠物
            Pet existingPet = petDAO.selectById(petDTO.getOwnerId());
            if (existingPet != null) {
                throw new IllegalArgumentException("User already has a pet");
            }

            // 3. 创建新宠物，使用用户ID作为宠物ID
            Pet pet = new Pet();
            BeanUtils.copyProperties(petDTO, pet);
            pet.setPetId(owner.getUserId());  // 设置宠物ID等于用户ID
            pet.setOwnerId(owner.getUserId());
            pet.setAdoptTime(LocalDateTime.now());

            // 4. 保存宠物
            petDAO.insert(pet);
            
            return pet;
        } catch (Exception e) {
            log.error("Error adopting pet: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to adopt pet", e);
        }
    }

    @Override
    public Pet updatePet(PetDTO petDTO) {
        Pet pet = petDAO.selectById(petDTO.getPetId());
        if (pet == null) {
            return null;
        }
        BeanUtils.copyProperties(petDTO, pet);
        petDAO.updateById(pet);
        return pet;
    }

    @Override
    public String updateAvatar(MultipartFile file) {
        return boxService.handleImageUpload(file);
    }

    @Override
    public Pet getPet(Integer petId) {
        return petDAO.selectById(petId);
    }
} 