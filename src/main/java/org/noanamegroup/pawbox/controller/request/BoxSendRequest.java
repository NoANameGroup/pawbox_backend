package org.noanamegroup.pawbox.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxSendRequest {

    @NotNull(message = "内容不能为空")
    private String content;

    private String imageUrl;
}
