package fun.redamancyxun.chinese.backend.controller.box;

import fun.redamancyxun.chinese.backend.common.Result;
import fun.redamancyxun.chinese.backend.controller.user.response.UserInfoResponse;
import fun.redamancyxun.chinese.backend.exception.MyException;
import fun.redamancyxun.chinese.backend.service.BoxService;
import fun.redamancyxun.chinese.backend.service.UserService;
import fun.redamancyxun.chinese.backend.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Api(tags = "盒子操作相关接口")
@RestController
@RequestMapping("/box")
@Slf4j
public class BoxController {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private BoxService boxService;

    /**
     * 发送盒子
     * @param imageUrl
     * @param content
     * @return UserInfoResponse
     */
    @PostMapping(value = "/sendBox", produces = "application/json")
    @ApiOperation(value = "发送盒子", response = UserInfoResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageUrl", value = "图片地址", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, paramType = "query", dataType = "String"),
    })
    public Result sendBox(@NotNull @RequestParam("imageUrl") String imageUrl,
                          @NotNull @RequestParam("content") String content) {
        try {
            return Result.success(boxService.sendBox(imageUrl, content));
        } catch (MyException e) {
            return Result.result(e.getEnumExceptionType());
        }
    }

    /**
     * 接收盒子
     * @return UserInfoResponse
     */
    @PostMapping(value = "/receiveBox", produces = "application/json")
    @ApiOperation(value = "接收盒子", response = UserInfoResponse.class)
    public Result receiveBox() {
        try {
            return Result.success(boxService.receiveBox());
        } catch (MyException e) {
            return Result.result(e.getEnumExceptionType());
        }
    }

}
