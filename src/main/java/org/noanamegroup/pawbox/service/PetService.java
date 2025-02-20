package org.noanamegroup.pawbox.service;

import java.time.LocalDateTime;

import org.noanamegroup.pawbox.dao.PetDAO;
import org.noanamegroup.pawbox.dao.UserDAO;
import org.noanamegroup.pawbox.entity.Pet;
import org.noanamegroup.pawbox.entity.dto.PetDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetService implements PetServiceImpl {
    @Autowired
    private PetDAO petDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BoxService boxService;

    @Override
    public Pet adoptPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setAdoptTime(LocalDateTime.now());
        pet.setOwner(userDAO.selectById(petDTO.getOwnerId()));
        petDAO.insert(pet);
        return pet;
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