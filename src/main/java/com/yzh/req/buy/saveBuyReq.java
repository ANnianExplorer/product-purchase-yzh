package com.yzh.req.buy;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 购买请求
 *
 * @author yzh
 * @since 2022/8/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="saveBuyReq对象", description="购买表")
public class saveBuyReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "购买数量")
    @NotNull(message = "购买数量不能为空")
    private int buyAmount;

    @ApiModelProperty(value = "用户地址")
    private String userAddress;

}