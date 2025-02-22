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
    public Pet adoptPet(PetDTO petDTO) {
        try {
            // 1. 参数校验
            if (petDTO.getOwnerId() == null) {
                throw new IllegalArgumentException("Owner ID cannot be null");
            }

            // 2. 查询用户
            User owner = userDAO.selectById(petDTO.getOwnerId());
            if (owner == null) {
                log.error("User not found with id: {}", petDTO.getOwnerId());
                throw new RuntimeException("User not found");
            }

            // 3. 构建Pet对象并设置必要字段
            Pet pet = new Pet();
            BeanUtils.copyProperties(petDTO, pet);
            pet.setAdoptTime(LocalDateTime.now());
            pet.setOwner(owner);
            
            // 4. 设置外键字段（确保与数据库映射一致）
            // 由于使用了JPA的@JoinColumn，这里不需要手动设置owner_id
            
            // 5. 添加详细日志
            log.debug("Creating pet: name={}, ownerId={}, adoptTime={}", 
                     pet.getName(), owner.getUserId(), pet.getAdoptTime());
            
            // 6. 执行插入
            petDAO.insert(pet);
            
            // 7. 记录成功日志
            log.info("Successfully adopted pet: id={}, name={}, owner={}", 
                    pet.getPetId(), pet.getName(), owner.getUsername());
                
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