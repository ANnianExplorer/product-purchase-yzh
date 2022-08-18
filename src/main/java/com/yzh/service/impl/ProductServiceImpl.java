package com.yzh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzh.constant.ProductConstant;
import com.yzh.domain.Product;
import com.yzh.mapper.ProductMapper;
import com.yzh.req.product.ProductQueryReq;
import com.yzh.req.product.ProductSaveOrUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.product.ProductQueryResp;
import com.yzh.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzh.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-17
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    /**
     * 新增或更新产品
     *
     * @param req 要求事情
     */
    @Override
    public void saveOrUpdateProduct(ProductSaveOrUpdateReq req) {
        Product product = CopyUtil.copy(req, Product.class);
        product.setProductPicture(ProductConstant.DEFAULT_PICTURE);
        if (ObjectUtils.isEmpty(product.getProductId())){
            this.save(product);
            return;
        }
        this.updateById(product);
    }

    /**
     * 查询产品
     *
     * @param req 要求事情
     * @return {@link PageResp}<{@link ProductQueryResp}>
     */
    @Override
    public PageResp<ProductQueryResp> queryProduct(ProductQueryReq req) {

        Page<Product> ProductPage = this.page(new Page<>(req.getPage(), req.getSize())
                , Wrappers.lambdaQuery(Product.class)
                        .like(
                                !ObjectUtils.isEmpty(req.getProductName()),
                                Product::getProductName, req.getProductName())
                        .like(
                                !ObjectUtils.isEmpty(req.getProductPrice()),
                                Product::getProductPrice, req.getProductPrice())
                        .orderByDesc(
                                Product::getUpdateTime)
        );
        log.info("the ProductPage is :{}",ProductPage);

        PageResp<ProductQueryResp> pageResp = new PageResp<>();
        List<Product> records = ProductPage.getRecords();
        for (Product record : records) {
            System.out.println(record);
        }

        pageResp.setContent(CopyUtil.copyList(ProductPage.getRecords(),ProductQueryResp.class));
        pageResp.setTotal(ProductPage.getTotal());
        log.info("the pageResp is :{}",pageResp);
        return pageResp;
    }
}
