package com.yzh.service;

import com.yzh.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzh.req.product.ProductQueryReq;
import com.yzh.req.product.ProductSaveReq;
import com.yzh.req.product.ProductUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.product.ProductQueryResp;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-17
 */
public interface ProductService extends IService<Product> {

    /**
     * 新增产品
     *
     * @param req 要求事情
     */
    void saveProduct(ProductSaveReq req);

    /**
     * 更新产品
     *
     * @param req 要求事情
     */
    void updateProduct(ProductUpdateReq req);

    /**
     * 查询产品
     *
     * @param req 要求事情
     * @return {@link PageResp}<{@link ProductQueryResp}>
     */
    PageResp<ProductQueryResp> queryProduct(ProductQueryReq req);

    /**
     * 删除产品
     *
     * @param productIds 产品id
     */
    void deleteProduct(List<Long> productIds);

}
