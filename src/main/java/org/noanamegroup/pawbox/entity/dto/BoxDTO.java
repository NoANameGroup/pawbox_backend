package org.noanamegroup.pawbox.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxDTO {
    private Integer boxId;

    @NotNull(message = "接收者ID不能为空")
    private Integer receiverId;

    private String content;

    private String imageUrl;
}
