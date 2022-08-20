package com.yzh.mapper;

import com.yzh.domain.Buy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购买表 Mapper 接口
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-20
 */
public interface BuyMapper extends BaseMapper<Buy> {

    List<Buy> getBuyUser(@Param("userId") Long userId);

    int selectAmount(@Param("buyId") Long buyId);
}
