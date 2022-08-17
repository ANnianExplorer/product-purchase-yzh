package com.yzh.mapper;

import com.yzh.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-13
 */
public interface UserMapper extends BaseMapper<User> {
    int countByPhone(@Param("phone") String phone);

    void updateUser(@Param("user")User user);
}
