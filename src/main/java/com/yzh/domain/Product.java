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
 * 商品表
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Product对象", description="商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "如果上传了图片，则使用；如果没传，则默认")
    private String productPicture;

    @ApiModelProperty(value = "商品介绍")
    private String productDes;

    @ApiModelProperty(value = "商品价格")
    private String productPrice;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除字段：0 未删除，1 删除")
    private Integer isDelete;


}
