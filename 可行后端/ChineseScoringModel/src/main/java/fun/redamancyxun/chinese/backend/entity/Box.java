package fun.redamancyxun.chinese.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel("box 盒子")
@TableName(value ="box")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Box {

    @Id
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty("图片地址")
    @TableField(value = "image_url")
    private String imageUrl;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("发送者id")
    @TableField(value = "sender_id")
    private String senderId;
}
