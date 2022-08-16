package com.yzh.constant;

/**
 * 用户常量
 *
 * @author 杨振华
 * @date 2022/08/15
 */
public interface UserConstant {

        /**
         * 默认
         */
        String DEFAULT_AVATAR = "https://portrait.gitee.com/uploads/avatars/user/3579/10739323_AN-nian-gitee_1655433308.png!avatar100";

        String SALT = "ehdg2t781t36E8D%#%R";

        String SESSION_KEYWORDS = "current-user";



        /**
         * 普通用户
         */
        Integer COMMON_ROLE = 0;

        /**
         * 管理员
         */
        Integer ADMIN_ROLE = 1;
}
