package com.yzh.controller;


import com.yzh.annotation.AdminRole;
import com.yzh.common.CommonResponse;
import com.yzh.req.product.ProductQueryReq;
import com.yzh.req.product.ProductSaveReq;
import com.yzh.req.product.ProductUpdateReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.product.ProductQueryResp;
import com.yzh.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-19
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品接口")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/save")
    @ApiOperation("新增商品")
    @AdminRole
    public CommonResponse<String> saveOrUpdate(@RequestBody @Valid ProductSaveReq req){
        productService.saveProduct(req);
        return CommonResponse.success("新增成功！");
    }

    @PostMapping("/update")
    @ApiOperation("更新商品信息")
    @AdminRole
    public CommonResponse<String> update(@RequestBody @Valid ProductUpdateReq req){
        productService.updateProduct(req);
        return CommonResponse.success("更新成功！");
    }

    @GetMapping("/query")
    @ApiOperation("查询商品")
    public CommonResponse<PageResp<ProductQueryResp>> queryProduct(ProductQueryReq req){
        return CommonResponse.success(productService.queryProduct(req),"查询成功！");
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除商品")
    @AdminRole
    public CommonResponse<String> deleteProduct(@PathVariable("ids") List<Long> ids){
        productService.deleteProduct(ids);
        return CommonResponse.success("删除成功！");
    }

}
