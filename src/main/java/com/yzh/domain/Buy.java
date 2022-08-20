package com.yzh.domain;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 购买表
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Buy对象", description="购买表")
public class Buy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购买id")
    @TableId
    private Long buyId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "购买数量")
    private int buyAmount;

    @ApiModelProperty(value = "购买金额")
    private int lumpSum;

    @ApiModelProperty(value = "用户地址")
    private String userAddress;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除字段：0 未删除，1 删除")
    private Integer isDelete;


}
