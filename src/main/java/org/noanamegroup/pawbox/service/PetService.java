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
            // 先检查用户是否存在
            User owner = userDAO.selectById(petDTO.getOwnerId());
            if (owner == null) {
                log.error("User not found with id: {}", petDTO.getOwnerId());
                throw new RuntimeException("User not found");
            }

            Pet pet = new Pet();
            BeanUtils.copyProperties(petDTO, pet);
            pet.setAdoptTime(LocalDateTime.now());
            pet.setOwner(owner);
            
            log.debug("Saving pet with owner: {}", owner);
            petDAO.insert(pet);
            return pet;
        } catch (Exception e) {
            log.error("Error adopting pet: ", e);
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