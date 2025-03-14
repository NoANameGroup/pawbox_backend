package org.noanamegroup.pawbox.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetUpdateRequest {
//    private Integer petId;
    
    @NotBlank(message = "宠物名字不能为空")
    @Size(min = 1, max = 50, message = "宠物名字长度必须在1-50之间")
    private String name;
    
    private String avatarUrl;
    private LocalDateTime birthday;
    
//    @NotNull(message = "主人ID不能为空")
//    private Integer ownerId;
} 