package com.yzh.controller;


import com.yzh.annotation.AdminRole;
import com.yzh.common.CommonResponse;
import com.yzh.domain.User;
import com.yzh.req.user.UserLoginReq;
import com.yzh.req.user.UserQueryReq;
import com.yzh.req.user.UserRegisterReq;
import com.yzh.req.user.UserUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.user.UserQueryResp;
import com.yzh.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-13
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public CommonResponse<User> register(@RequestBody @Valid UserRegisterReq req){
        userService.register(req);
        return CommonResponse.success("用户注册成功！");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public CommonResponse<UserQueryResp> login(@RequestBody @Valid UserLoginReq req, HttpSession session){
        return CommonResponse.success(userService.login(req,session),"用户登录成功！");
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public CommonResponse<String> logOut(HttpSession session){
        userService.logOut(session);
        return CommonResponse.success("用户退出登录成功！");
    }

    @GetMapping("/queryAll")
    @ApiOperation("查询所有用户信息")
    @AdminRole
    public CommonResponse<PageResp<UserQueryResp>> queryAll(UserQueryReq req){
        return CommonResponse.success(userService.queryAll(req),"查询成功！");
    }

    @GetMapping("/query/{id}")
    @ApiOperation("通过id查询用户信息")
    @AdminRole
    public CommonResponse<UserQueryResp> queryById(@PathVariable Long id){
        return CommonResponse.success(userService.queryById(id),"查询成功！");
    }

    @PostMapping("/update")
    @ApiOperation("更新用户信息" +
            "注意这里是规定了userId不能为空，即通过id更新用户信息")
    public CommonResponse<String> update(@RequestBody @Valid UserUpdateReq req,HttpSession session){
        userService.updateUser(req,session);
        return CommonResponse.success("更新成功！");
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("根据ids批量删除用户")
    @AdminRole
    public CommonResponse<String> deleteByIds(@PathVariable List<Long> ids){
        userService.deleteByIds(ids);
        return CommonResponse.success("删除成功！");
    }

    @DeleteMapping("/delete-session")
    @ApiOperation("注销当前用户")
    public CommonResponse<String> delSession(HttpSession session){
        userService.deleteBySession(session);
        userService.logOut(session);
        return CommonResponse.success("注销成功！");
    }
}
