package com.yzh.controller;


import com.yzh.annotation.AdminRole;
import com.yzh.common.CommonResponse;
import com.yzh.req.product.ProductQueryReq;
import com.yzh.req.product.ProductSaveOrUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.product.ProductQueryResp;
import com.yzh.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品接口")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/save-update")
    @ApiOperation("新增或更新" +
            "传入id不为空则更新")
    @AdminRole
    public CommonResponse<String> saveOrUpdate(@RequestBody @Valid ProductSaveOrUpdateReq req){
        productService.saveOrUpdateProduct(req);
        return CommonResponse.success("操作成功！");
    }

    @GetMapping("/query")
    @ApiOperation("查询商品")
    public CommonResponse<PageResp<ProductQueryResp>> queryProduct(ProductQueryReq req){
        return CommonResponse.success(productService.queryProduct(req),"查询成功！");
    }


}
