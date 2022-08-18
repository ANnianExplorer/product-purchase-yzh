package com.yzh.service.impl;

import com.yzh.domain.ProductAssess;
import com.yzh.mapper.ProductAssessMapper;
import com.yzh.service.ProductAssessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品和评价的中间表 服务实现类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
@Service
public class ProductAssessServiceImpl extends ServiceImpl<ProductAssessMapper, ProductAssess> implements ProductAssessService {

}
