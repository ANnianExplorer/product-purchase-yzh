package com.yzh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzh.constant.ProductConstant;
import com.yzh.domain.Product;
import com.yzh.exception.BusinessCode;
import com.yzh.exception.BusinessException;
import com.yzh.mapper.ProductMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzh.req.product.ProductQueryReq;
import com.yzh.req.product.ProductSaveReq;
import com.yzh.req.product.ProductUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.product.ProductQueryResp;
import com.yzh.service.CommonService;
import com.yzh.service.ProductService;
import com.yzh.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-19
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private CommonService commonService;

    @Resource
    private ProductMapper productMapper;

    /**
     * 新增或更新产品
     *
     * @param req 要求事情
     */
    @Override
    public void saveProduct(ProductSaveReq req) {
        Product product = CopyUtil.copy(req, Product.class);
        product.setProductPicture(ProductConstant.DEFAULT_PICTURE);

        this.save(product);
    }

    @Override
    public void updateProduct(ProductUpdateReq req) {

        Product product = productMapper.selectById(req.getProductId());
        if (ObjectUtils.isEmpty(product)){
            throw new BusinessException(BusinessCode.PRODUCT_ERROR,"当前商品不存在，无法更新");
        }
        if (!ObjectUtils.isEmpty(req.getProductPicture()) && !product.getProductPicture().equals(req.getProductPicture())){
            commonService.deleteFile(product.getProductPicture());
        }

        this.updateById(CopyUtil.copy(req,Product.class));

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

    /**
     * 删除产品
     *
     * @param productIds 产品id
     */
    @Override
    public void deleteProduct(List<Long> productIds) {
        List<Product> products = this.listByIds(productIds);
        for (Product product : products) {
            commonService.deleteFile(product.getProductPicture());
        }
        this.removeByIds(productIds);
    }
}
