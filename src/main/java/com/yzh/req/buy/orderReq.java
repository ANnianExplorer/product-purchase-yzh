package com.yzh.req.buy;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yzh.req.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单请求
 *
 * @author yzh
 * @since 2022/8/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="orderReq对象", description="购买表")
public class orderReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购买id")
    @NotNull(message = "id不能为空")
    private Long buyId;
}
