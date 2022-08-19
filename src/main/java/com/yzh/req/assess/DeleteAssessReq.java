package com.yzh.req.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户删除评价
 *
 * @author yzh
 * @since 2022/8/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="DeleteAssessReq对象", description="商品表")
public class DeleteAssessReq implements Serializable {

    @ApiModelProperty(value = "评价id")
    @NotNull(message = "评价id不能为空")
    private List<Long> assessIds;
}
