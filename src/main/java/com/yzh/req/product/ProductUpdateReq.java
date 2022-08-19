package com.yzh.req.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * 产品更新要求
 *
 * @author yzh
 * @since 2022/8/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductUpdateReq对象", description="商品表")
public class ProductUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空")
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
