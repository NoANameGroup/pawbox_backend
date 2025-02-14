package com.noanamegroup.boxbuddy.controller;

import com.noanamegroup.boxbuddy.entity.ResponseMessage;
import com.noanamegroup.boxbuddy.entity.User;
import com.noanamegroup.boxbuddy.entity.dto.UserDTO;
import com.noanamegroup.boxbuddy.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户操作相关接口") //swagger 注解（自己去看看） 说的是哪个呀，每个注解用处都不一样
@RestController
@RequestMapping("/user")
public class UserController
{

    @Autowired
    UserServiceImpl userServiceImpl;

    // 为什么要写swagger注解？
    // 说第几行

    // 注解要写好
    // 给自己看的，这个是注释
    /**
     * 注册
     * @param telephone
     * @param password
     * @param role
     * @return UserInfoResponse
     */
    // 传参有问题呀，要改一下，没必要一定要传切面，可以直接传参
    // 先用着吧，出bug就懂了
    @PostMapping(value = "/signup", produces = "application/json")
    @ApiOperation(value = "注册", response = UserInfoResponse.class) //swagger 注解（自己去看看）
    @ApiImplicitParams({ //swagger 注解（自己去看看）； 里面的东西再按自己的需求改
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "role", value = "角色", required = true, paramType = "query", dataType = "Integer")
    })
    // UserInfoResponse直接去定义，它指的是返回信息
    // 前端要给你UserDTO，你可以理解吗
    public ResponseMessage<UserInfoResponse> signup(@Validated @RequestBody UserDTO user) // addUser也太奇怪了吧
    {
        // 异常处理，异常捕捉；为什么要异常处理？让程序就算崩溃了也可以继续运行
        try {
            User userNew = userServiceImpl.addUser(user);
            return ResponseMessage.success(userNew);
        } catch (Exception e) {
            // MyException 自己定义的异常
            if (e instanceof MyException) {
                return Result.result(((MyException) e).getEnumExceptionType());
            }
            return Result.fail(e.getMessage());
        }
    }

    // 查询
    // Get是前端向后端获取信息，Post是前端向后端发送信息，Get的话前端的参数在url里，Post的话是在body里
    @GetMapping(value = "/getUserInfo", produces = "application/json")
    @ApiOperation(value = "查询用户信息", response = UserInfoResponse.class)
    public ResponseMessage getUser(@PathVariable Integer userId)
    {
        User userNew = userServiceImpl.getUser(userId);
        return ResponseMessage.success(userNew);
    }

    // 修改
    // 那就前端把UserId存到本地就行，前端的人应该知道的
    @PostMapping // Put和Delete一般不会用到的
    public ResponseMessage updateUser(@Validated @RequestBody UserDTO user)
    {
        User userNew = userServiceImpl.updateUser(user);
        return ResponseMessage.success(userNew);
    }

    // 删除
    @GetMapping("/{userId}")
    public ResponseMessage deleteUser(@PathVariable Integer userId)
    {
        userServiceImpl.deleteUser(userId);
        return ResponseMessage.success();
    }

    // 其他的你自己改吧
}
