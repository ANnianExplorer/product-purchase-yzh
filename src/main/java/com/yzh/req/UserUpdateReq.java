package com.yzh.req;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户更新请求
 *
 * @author yzh
 * @since 2022/8/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId
    @NotNull(message = "id不能为空")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "电话")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "电话格式错误")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "如果用户传入头像，则用用户的；如果没传，则默认")
    private String avatar;

    @ApiModelProperty(value = "用户介绍")
    private String description;
}
