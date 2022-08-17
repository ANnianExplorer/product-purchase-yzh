package com.yzh.service.impl;

import com.yzh.exception.BusinessCode;
import com.yzh.exception.BusinessException;
import com.yzh.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 公共服务实现类
 *
 * @author yzh
 * @since 2022/8/16
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Value("${file.path}")
    private String BASE_PATH;

    /**
     * 上传
     *
     * @param file 文件
     * @return {@link String}
     */
    @Override
    public String upLoad(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (ObjectUtils.isEmpty(originalFilename)){
            throw new BusinessException(BusinessCode.FILE_ERROR,"文件名为空！");
        }
        File catalogue = new File(BASE_PATH);
        if (!catalogue.exists()){
            if (!catalogue.mkdirs()){
                throw new BusinessException(BusinessCode.FILE_ERROR,"创建根目录异常！");
            }
            throw new BusinessException(BusinessCode.FILE_ERROR,"根目录不存在！");
        }

        // 构建新的文件名
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 构建新路径
        String pathName = BASE_PATH + fileName;

        try {
            file.transferTo(new File(pathName));
        }catch (IOException e){
            throw new BusinessException(BusinessCode.FILE_ERROR,"文件上传异常，请检查网络！");
        }
        return fileName;
    }

    /**
     * 下载
     *
     * @param name     名字
     * @param response 响应
     */
    @Override
    public void download(String name, HttpServletResponse response) {
        try (FileInputStream inputStream = new FileInputStream(BASE_PATH + name)) {
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("/image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

        } catch (IOException e) {
            throw new BusinessException(BusinessCode.FILE_ERROR, "文件读取时异常！");
        }
    }

    /**
     * 删除文件
     *
     * @param name 名字
     * @return boolean
     */
    @Override
    public boolean deleteFile(String name) {
        return new File(BASE_PATH + name).delete();
    }
}
