package com.yzh.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应
 *
 * @author 杨振华
 * @date 2022/08/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResp<T> implements Serializable {

    private static final long serialVersionUID = 135145132L;

    /**
     * 内容
     */
    private List<T> content;

    /**
     * 总计
     */
    private long total;


}