package com.yzh.req.product;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yzh.req.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductQueryReq对象", description="商品表")
public class ProductQueryReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private String productPrice;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
