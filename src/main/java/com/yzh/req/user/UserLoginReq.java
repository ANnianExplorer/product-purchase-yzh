package com.yzh.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserLoginReq对象", description="用户表")
public class UserLoginReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电话")
    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "电话格式错误")
    private String phone;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;


}
