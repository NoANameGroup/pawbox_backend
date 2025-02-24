package fun.redamancyxun.chinese.backend.dto;

import fun.redamancyxun.chinese.backend.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * session缓存实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("SessionData 会话实体")
public class SessionData implements Serializable {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    public SessionData(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
//        this.gender = user.getGender();
//        this.age = user.getAge();
//        this.nation = user.getNation();
        this.email = user.getEmail();
//        this.portrait = user.getPortrait();
//        this.role = user.getRole();
//        this.advice = user.getAdvice();
//
//        this.history = history;
    }
//
//    public SessionData(User user) {
//        this.id = user.getId();
//        this.username = user.getUsername();
////        this.gender = user.getGender();
////        this.age = user.getAge();
////        this.nation = user.getNation();
//        this.email = user.getEmail();
////        this.portrait = user.getPortrait();
////        this.role = user.getRole();
////        this.advice = user.getAdvice();
//
////        this.history = null;
//    }

}
