package com.yzh.interceptor;

import com.yzh.annotation.AdminRole;
import com.yzh.domain.User;
import com.yzh.exception.BusinessCode;
import com.yzh.exception.BusinessException;
import com.yzh.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.yzh.constant.UserConstant.ADMIN_ROLE;
import static com.yzh.constant.UserConstant.SESSION_KEYWORDS;

/**
 * 登录拦截器
 *
 * @author yzh
 * @since 2022/8/16
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;

    /**
     * 前处理  进行拦截处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-------start interceptor------");

        // 通过session得到当前用户，进行操作
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);
        if (ObjectUtils.isEmpty(sessionUser)){
            throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR,"此用户未登录！");
        }
        // 将处理程序封装为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // handlerMethod.hasMethodAnnotation(AdminRole.class)返回boolean值
        // 当返回 true 时，进行鉴别操作
        /*if (sessionUser.getIsDelete()==1){
            throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR,"此用户已被注销，请重试！");
        }*/

        if (handlerMethod.hasMethodAnnotation(AdminRole.class)){
            // 通过session传入的Id来匹配当前用户信息，得到当前用户对象
            User user = userMapper.selectById(sessionUser.getUserId());
            if (ObjectUtils.isEmpty(user)){
                throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR,"此用户不存在！");
            }
            if (!user.getRoles().equals(ADMIN_ROLE)){
                throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR,"此用户无权限！");
            }
        }
        return true;
    }
}
