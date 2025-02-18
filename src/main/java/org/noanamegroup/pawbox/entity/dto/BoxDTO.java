package org.noanamegroup.pawbox.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.noanamegroup.pawbox.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxDTO {
    private Integer boxId;

    @NotNull(message = "接收者不能为空")
    private Integer sender_id;

    @NotNull(message = "内容不能为空")
    private String content;

    private String imageUrl;
}
