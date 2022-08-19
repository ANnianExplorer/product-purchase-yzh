package com.yzh.resp.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评价查询响应
 *
 * @author yzh
 * @since 2022/8/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AssessQueryResp对象", description="评价表")
public class AssessQueryResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价内容")
    private String assessContent;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "用户")
    private UserAssessResp user;

}
