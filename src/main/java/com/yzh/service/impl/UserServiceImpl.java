package com.yzh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzh.constant.UserConstant;
import com.yzh.domain.User;
import com.yzh.exception.BusinessCode;
import com.yzh.exception.BusinessException;
import com.yzh.mapper.UserMapper;
import com.yzh.req.UserLoginReq;
import com.yzh.req.UserQueryReq;
import com.yzh.req.UserRegisterReq;
import com.yzh.req.UserUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.UserQueryResp;
import com.yzh.service.CommonService;
import com.yzh.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzh.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.yzh.constant.UserConstant.SALT;
import static com.yzh.constant.UserConstant.SESSION_KEYWORDS;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-13
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CommonService commonService;


    /**
     * 注册
     *
     * @param req 要求事情
     */
    @Override
    public void register(UserRegisterReq req) {
        int count = userMapper.countByPhone(req.getPhone());
        User user = CopyUtil.copy(req, User.class);
        // 设置默认的头像
        user.setAvatar(UserConstant.DEFAULT_AVATAR);

        /**
         * 加密处理 密码
         */
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + SALT).getBytes()));

        if (count != 0){
            throw new BusinessException(BusinessCode.PARAM_ERROR,"此号码已经被注册，请重试！");
        }else {
            this.save(user);
        }
    }

    /**
     * 登录
     *
     * @param req     要求事情
     * @param session 会话
     * @return {@link UserQueryResp}
     */
    @Override
    public UserQueryResp login(UserLoginReq req, HttpSession session) {
        //1、 根据phone查询数据库是否有此用户
        User userDb = this.getOne(Wrappers.lambdaQuery(User.class).eq(User::getPhone, req.getPhone()));
        // 如果数据库中无此用户
        if (ObjectUtils.isEmpty(userDb)){
            // 前端显示错误原因
            throw new BusinessException(BusinessCode.LOGIN_ERROR,BusinessCode.LOGIN_ERROR.getMessage());
        }

        // 2、对比判断输入密码是否正确
        String reqPwd = DigestUtils.md5DigestAsHex((req.getPassword() + SALT).getBytes());
        if (!reqPwd.equals(userDb.getPassword())){
            throw new BusinessException(BusinessCode.LOGIN_ERROR,BusinessCode.LOGIN_ERROR.getMessage());
        }
        // 3、将当前用户信息存入session
        User userSession = new User();
        userSession.setUserId(userDb.getUserId());// 拦截器要用到
        session.setAttribute(SESSION_KEYWORDS,userSession);

        return CopyUtil.copy(userDb,UserQueryResp.class);

    }

    /**
     * 退出登录
     *
     * @param session 会话
     */
    @Override
    public void logOut(HttpSession session) {
        session.removeAttribute(SESSION_KEYWORDS);
    }


    /**
     * 查询所有
     *
     * @param req     要求事情
     * @param session 会话
     * @return {@link PageResp}<{@link UserQueryResp}>
     */
    @Override
    public PageResp<UserQueryResp> queryAll(UserQueryReq req) {
        // 模糊查询  添加顺序
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class)
                .like(!ObjectUtils.isEmpty(req.getUsername()), User::getUsername, req.getUsername())
                .eq(!ObjectUtils.isEmpty(req.getPhone()), User::getPhone, req.getPhone())
                .eq(!ObjectUtils.isEmpty(req.getRoles()), User::getRoles, req.getRoles())
                .orderByDesc(User::getUpdateTime);

        log.info("this is queryWrapper :{}",queryWrapper);

        Page<User> page = this.page(new Page<>(req.getPage(),req.getSize()),queryWrapper);

        // 查询后存进了 Page 对象中，我们才能getRecords获取记录
        return new PageResp<>(
                CopyUtil.copyList(
                        page.getRecords(),UserQueryResp.class),
                page.getTotal()
        );

    }

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link UserQueryResp}
     */
    @Override
    public UserQueryResp queryById(Long id) {
        User user = this.getById(id);
        return CopyUtil.copy(user,UserQueryResp.class);
    }

    /**
     * 更新用户信息
     *
     * @param req 要求事情
     */
    @Override
    public void updateUser(UserUpdateReq req,HttpSession session) {
        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);
        User reqUser = this.getById(req.getUserId());

        if (!reqUser.getUserId().equals(sessionUser.getUserId())){
            throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR,"修改失败，请使用本人用户的id");
        }

        // 对电话操作
        if (!ObjectUtils.isEmpty(req.getPhone())) {

            if (!sessionUser.getPhone().equals(req.getPhone())){
                if (this.count(Wrappers.lambdaQuery(User.class).eq(User::getPhone,req.getPhone()))!=0){
                    throw new BusinessException(BusinessCode.PARAM_ERROR,"该电话已被使用，请重试！");
                }
            }
        }
        // 对头像操作
        if (!ObjectUtils.isEmpty(req.getAvatar()) && !sessionUser.getAvatar().equals(req.getAvatar())){
            commonService.deleteFile(sessionUser.getAvatar());
        }
        // 对密码操作
        if (!ObjectUtils.isEmpty(req.getPassword())){
            String Md5Pwd = DigestUtils.md5DigestAsHex((req.getPassword() + SALT).getBytes());
            req.setPassword(Md5Pwd);
        }

        // 更新
        this.updateById(CopyUtil.copy(req,User.class));
    }

    /**
     * 根据ids批量删除
     *
     * @param ids id
     */
    @Override
    public void deleteByIds(List<Long> ids) {
        List<User> users = this.listByIds(ids);
        for (User user : users) {
            commonService.deleteFile(user.getAvatar());
        }
        this.removeByIds(ids);
    }

    /**
     * 根据session注销用户
     *
     * @param session 会话
     */
    @Override
    public void deleteBySession(HttpSession session) {
        User userSession = (User) session.getAttribute(SESSION_KEYWORDS);
        commonService.deleteFile(userSession.getAvatar());
        this.removeById(userSession.getUserId());
    }
}
