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

    @NotNull(message = "发送者不能为空")
    private Integer senderId;

    @NotNull(message = "内容不能为空")
    private String content;

    private String imageUrl;
}
