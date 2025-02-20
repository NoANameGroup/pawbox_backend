package org.noanamegroup.pawbox.entity.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private Integer petId;
    
    @NotBlank(message = "宠物名字不能为空")
    private String name;
    
    private String avatarUrl;
    private LocalDateTime birthday;
    private Integer ownerId;
} 