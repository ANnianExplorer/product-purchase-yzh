package com.yzh.mapper;

import com.yzh.domain.Assess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 评价表 Mapper 接口
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
public interface AssessMapper extends BaseMapper<Assess> {
    void deleteUserAssess(@Param("assessId") Long assessId);
}
