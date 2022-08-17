package com.yzh.resp;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登录响应
 *
 * @author yzh
 * @since 2022/8/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserQueryResp对象", description="用户表")
public class UserQueryResp implements Serializable {
    @ApiModelProperty(value = "用户id")
    @TableId
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "如果用户传入头像，则用用户的；如果没传，则默认")
    private String avatar;

    @ApiModelProperty(value = "用户介绍")
    private String description;

    @ApiModelProperty(value = "普通用户是0，管理员是1")
    private Integer roles;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
