package com.yzh.controller;


import com.yzh.annotation.AdminRole;
import com.yzh.common.CommonResponse;
import com.yzh.req.assess.QueryAssessReq;
import com.yzh.req.assess.UpdateAssessReq;
import com.yzh.req.assess.saveAssessReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.assess.AssessQueryResp;
import com.yzh.service.AssessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 评价表 前端控制器
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
@RestController
@RequestMapping("/assess")
@Api(tags = "评价接口")
public class AssessController {

    @Resource
    private AssessService assessService;

    @PostMapping("/assess")
    @ApiOperation("新增评价")
    public CommonResponse<String> save(@RequestBody @Valid saveAssessReq req, HttpSession session){
        assessService.saveAssess(req,session);
        return CommonResponse.success("评价成功！");
    }

    @PostMapping("/update")
    @ApiOperation("更新评价")
    public CommonResponse<String> update(@RequestBody @Valid UpdateAssessReq req, HttpSession session){
        assessService.updateAssess(req,session);
        return CommonResponse.success("更新成功！");
    }

    @GetMapping("/query")
    @ApiOperation("查询评价")
    public CommonResponse<PageResp<AssessQueryResp>> queryAll(QueryAssessReq req){
        return CommonResponse.success(assessService.queryAssess(req),"查询成功！");
    }

    @DeleteMapping("delete/{ids}")
    @ApiOperation("批量删除评价")
    @AdminRole
    public CommonResponse<String> delete(@PathVariable List<Long> ids){
        assessService.deleteAssess(ids);
        return CommonResponse.success("删除成功！");
    }
}
