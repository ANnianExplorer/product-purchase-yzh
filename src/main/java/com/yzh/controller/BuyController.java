package com.yzh.controller;


import com.yzh.common.CommonResponse;
import com.yzh.req.PageReq;
import com.yzh.req.buy.orderReq;
import com.yzh.req.buy.orderUpdateReq;
import com.yzh.req.buy.saveBuyReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.buy.buySaveResp;
import com.yzh.service.BuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 购买表 前端控制器
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/buy")
@Api(tags = "购买接口")
public class BuyController {

    @Resource
    private BuyService buyService;

    @PostMapping("/save")
    @ApiOperation("新增购买")
    public CommonResponse<String> saveBuy(@RequestBody @Valid saveBuyReq req, HttpSession session){
        buyService.saveBuy(req,session);
        return CommonResponse.success("购买成功！");
    }

    @GetMapping("/order")
    @ApiOperation("提交订单")
    public CommonResponse<PageResp<buySaveResp>> SubmitOrder(@RequestBody @Valid orderReq req){
        return CommonResponse.success(buyService.SubmitOrder(req),"查看并核对订单！");
    }

    @GetMapping("/orderSession")
    @ApiOperation("查询当前用户的所有订单")
    public CommonResponse<PageResp<buySaveResp>> SubmitOrderAllBySession(PageReq req,HttpSession session){
        return CommonResponse.success(buyService.SubmitOrderAllBySession(req,session),"查看成功！");
    }

    @PostMapping("/update")
    @ApiOperation("更新订单信息")
    public CommonResponse<String> updateOrder(@RequestBody @Valid orderUpdateReq req,HttpSession session){
        buyService.updateOrder(req,session);
        return CommonResponse.success("修改成功，快点下单！");
    }

    @DeleteMapping("/delete/{buyIds}")
    @ApiOperation("删除订单详情")
    public CommonResponse<String> deleteOrder(@PathVariable List<Long> buyIds){
        buyService.deleteOrder(buyIds);
        return CommonResponse.success("删除成功！");
    }
}
