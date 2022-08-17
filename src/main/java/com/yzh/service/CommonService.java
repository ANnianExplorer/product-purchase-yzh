package com.yzh.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 公共服务
 *
 * @author yzh
 * @since 2022/8/16
 */
@Service
public interface CommonService {
    /**
     * 上传
     *
     * @param file 文件
     * @return {@link String}
     */
    String upLoad(MultipartFile file);

    /**
     * 下载
     *
     * @param name     名字
     * @param response 响应
     */
    void download(String name, HttpServletResponse response);

    /**
     * 删除文件
     *
     * @param name 名字
     * @return boolean
     */
    boolean deleteFile(String name);
}
