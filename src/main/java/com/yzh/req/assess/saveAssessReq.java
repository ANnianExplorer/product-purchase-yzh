package com.yzh.req.assess;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 新增评价请求
 *
 * @author yzh
 * @since 2022/8/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="saveAssessReq对象", description="商品表")
public class saveAssessReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @NotBlank(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "评价内容")
    private String assessContent;

}
