package com.yzh.mapper;

import com.yzh.domain.ProductAssess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品和评价的中间表 Mapper 接口
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
public interface ProductAssessMapper extends BaseMapper<ProductAssess> {
    void updateAssessContent(@Param("productId") Long productId,@Param("assessContent") String assessContent);

    void deleteUserAssess(@Param("assessId") Long assessId);
}
