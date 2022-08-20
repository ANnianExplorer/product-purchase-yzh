package com.yzh.resp.buy;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yzh.resp.assess.UserAssessResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 购买商品响应
 *
 * @author yzh
 * @since 2022/8/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="buySaveResp对象", description="购买表")
public class buySaveResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购买id")
    private Long buyId;

    @ApiModelProperty(value = "用户信息")
    private UserAssessResp user;

    @ApiModelProperty(value = "商品信息")
    private ProductBuyResp product;

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
}
