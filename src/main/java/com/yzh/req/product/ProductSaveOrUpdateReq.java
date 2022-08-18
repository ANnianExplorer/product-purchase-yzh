package com.yzh.req.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 商品新增请求
 *
 * @author yzh
 * @since 2022/8/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductSaveReq对象", description="商品表")
public class ProductSaveOrUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "名称不能为空")
    private String productName;

    @ApiModelProperty(value = "如果上传了图片，则使用；如果没传，则默认")
//    @NotBlank(message = "图片不能为空")
    private String productPicture;

    @ApiModelProperty(value = "商品介绍")
    @NotBlank(message = "介绍不能为空")
    private String productDes;

    @ApiModelProperty(value = "商品价格")
    @NotBlank(message = "价格不能为空")
    private String productPrice;
}
