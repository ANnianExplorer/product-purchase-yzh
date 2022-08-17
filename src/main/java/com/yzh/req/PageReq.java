package com.yzh.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求
 *
 * @author yzh
 * @since 2022/8/16
 */
@Data
public class PageReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "页数")
    private int page = 1;

    @ApiModelProperty(value = "分页大小")
    private int size = 10;
}
