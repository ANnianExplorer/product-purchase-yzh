package com.yzh.resp.buy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品购买表
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductBuyResp对象", description="购买表")
public class ProductBuyResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "如果上传了图片，则使用；如果没传，则默认")
    private String productPicture;

    @ApiModelProperty(value = "商品介绍")
    private String productDes;

    @ApiModelProperty(value = "商品价格")
    private String productPrice;

    @ApiModelProperty(value = "商品产地")
    private String productAddress;

}
