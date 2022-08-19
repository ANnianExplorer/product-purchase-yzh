package com.yzh.resp.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserAssessResp对象", description="用户表")
public class UserAssessResp implements Serializable {

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "如果用户传入头像，则用用户的；如果没传，则默认")
    private String avatar;
}