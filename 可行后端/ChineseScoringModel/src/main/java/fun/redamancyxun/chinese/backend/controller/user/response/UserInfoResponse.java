package fun.redamancyxun.chinese.backend.controller.user.response;

import fun.redamancyxun.chinese.backend.entity.Box;
import fun.redamancyxun.chinese.backend.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@ApiModel("详细用户信息")
public class UserInfoResponse {

    @ApiModelProperty("id")
    private String id;
    
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("收盒子记录")
    private List<Box> receiveBoxHistory;

    @ApiModelProperty("发盒子记录")
    private List<Box> sendBoxHistory;

    @ApiModelProperty("SessionId")
    private String sessionId;

    @ApiModelProperty("宠物名称")
    private String petName;

    @ApiModelProperty("宠物生日")
    private LocalDateTime birthday;


    public UserInfoResponse(User user, List<Box> receiveBoxHistory, List<Box> sendBoxHistory) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.receiveBoxHistory = receiveBoxHistory;
        this.sendBoxHistory = sendBoxHistory;
        this.petName = user.getPetName();
        this.birthday = user.getBirthday();


        this.sessionId = null;
    }

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.receiveBoxHistory = null;
        this.sendBoxHistory = null;
        this.petName = user.getPetName();
        this.birthday = user.getBirthday();

        this.sessionId = null;
    }

    public UserInfoResponse(User user, String sessionId) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.receiveBoxHistory = null;
        this.sendBoxHistory = null;
        this.petName = user.getPetName();
        this.birthday = user.getBirthday();

        this.sessionId = sessionId;
    }
}
