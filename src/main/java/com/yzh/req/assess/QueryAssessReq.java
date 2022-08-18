package com.yzh.req.assess;

import com.yzh.req.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询评价
 *
 * @author yzh
 * @since 2022/8/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="QueryAssessReq对象", description="评价表")
public class QueryAssessReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @NotBlank(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "评价内容")
    private String assessContent;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

}
