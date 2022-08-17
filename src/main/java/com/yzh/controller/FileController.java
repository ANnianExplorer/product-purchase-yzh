package com.yzh.controller;

import com.yzh.common.CommonResponse;
import com.yzh.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件控制器
 *
 * @author yzh
 * @since 2022/8/16
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件接口")
public class FileController {

    @Resource
    private CommonService commonService;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public CommonResponse<String> upload(MultipartFile file){
        return CommonResponse.success(commonService.upLoad(file),"上传成功！");
    }

    @ApiOperation("下载文件")
    @PostMapping("/download")
    public void downLoad(String name, HttpServletResponse response){
        commonService.download(name,response);
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/delete")
    public CommonResponse<Boolean> deleteFile(String name){
        return CommonResponse.success(commonService.deleteFile(name),"删除成功！");
    }
}
