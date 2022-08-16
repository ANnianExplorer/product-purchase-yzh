package com.yzh.exception;


import com.yzh.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 控制器异常处理程序
 *
 * @author 杨振华
 * @date 2022/08/15
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public CommonResponse<Object> validExceptionHandler(BindException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn("parameter validation failed:{}", errorMessage);
        return CommonResponse.error(BusinessCode.PARAM_ERROR,errorMessage);
    }

    @ExceptionHandler(value = BusinessException.class)
    public CommonResponse<Object> businessExceptionHandler(BusinessException e) {
        log.warn("parameter validation failed:{}", e.getMessage());
        return CommonResponse.error(e.getBusinessCode(),e.getMessage());
    }

    @ExceptionHandler(value = {MaxUploadSizeExceededException.class, FileSizeLimitExceededException.class})
    public CommonResponse<Object> fileUploadExceptionHandler(MaxUploadSizeExceededException e) {
        log.warn("upload file is too big{}\n",e.getMessage());
        return CommonResponse.error(BusinessCode.FILE_ERROR,"文件大小不能超过10MB");
    }


    /*@ExceptionHandler(value = Exception.class)
    public CommonResponse<Object> ExceptionHandler(Exception e){
        if (e instanceof BindException){
            String errorMessage = ((BindException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            log.warn("parameter validation failed:{}", errorMessage);
            return CommonResponse.error(errorMessage);
        } else if (e instanceof BusinessException) {

            log.warn("parameter validation failed:{}", e.getMessage());
            return CommonResponse.error(e.getMessage());
        }
        return null;
    }*/
}
