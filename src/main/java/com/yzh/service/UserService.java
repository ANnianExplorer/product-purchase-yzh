package com.yzh.service;

import com.yzh.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzh.req.user.UserLoginReq;
import com.yzh.req.user.UserQueryReq;
import com.yzh.req.user.UserRegisterReq;
import com.yzh.req.user.UserUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.user.UserQueryResp;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-13
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param req 要求事情
     */
    void register(UserRegisterReq req);

    /**
     * 登录
     *
     * @param req 要求事情
     * @return {@link UserQueryResp}
     */
    UserQueryResp login(UserLoginReq req, HttpSession session);

    /**
     * 注销
     *
     * @param session 会话
     */
    void logOut(HttpSession session);

    /**
     * 查询所有
     *
     * @param req     要求事情
     * @return {@link PageResp}<{@link UserQueryResp}>
     */
    PageResp<UserQueryResp> queryAll(UserQueryReq req);

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link UserQueryResp}
     */
    UserQueryResp queryById(Long id);

    /**
     * 更新用户
     *
     * @param req 要求事情
     */
    void updateUser(UserUpdateReq req,HttpSession session);

    /**
     * 根据ids批量删除
     *
     * @param ids id
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据session注销用户
     *
     * @param session 会话
     */
    void deleteBySession(HttpSession session);
}
