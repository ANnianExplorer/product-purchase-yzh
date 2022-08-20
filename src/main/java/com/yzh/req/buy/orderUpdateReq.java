package com.yzh.req.buy;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 修改购买订单
 *
 * @author yzh
 * @since 2022/8/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="orderUpdateReq对象", description="购买表")
public class orderUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "购买id不能为空")
    private Long buyId;

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = "购买数量")
    private int buyAmount;

    @ApiModelProperty(value = "用户地址")
    private String userAddress;

}
