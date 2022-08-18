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
 * 商品和评价的中间表
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductAssess对象", description="商品和评价的中间表")
public class ProductAssess implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品和评价的中间表id")
    @TableId
    private Long recordId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "评价id")
    private Long assessId;

    @ApiModelProperty(value = "评价内容")
    private String assessContent;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除字段：0 未删除，1 删除")
    private Integer isDelete;


}
