package com.yzh.req.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 更新评价
 *
 * @author yzh
 * @since 2022/8/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateAssessReq对象", description="商品表")
public class UpdateAssessReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价id")
    @NotNull(message = "评价id不能为空")
    private Long assessId;

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "评价内容")
    @NotNull(message = "评价内容不能为空")
    private String assessContent;
}
