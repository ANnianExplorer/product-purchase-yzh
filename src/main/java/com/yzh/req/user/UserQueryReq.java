package com.yzh.req.user;

import com.yzh.req.PageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yzh
 * @since 2022/8/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryReq extends PageReq implements Serializable {
    private static final long serialVersionUID = 123524L;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "普通用户是0，管理员是1")
    private Integer roles;
}
