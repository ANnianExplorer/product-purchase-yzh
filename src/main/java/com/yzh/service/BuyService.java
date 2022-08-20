package com.yzh.service;

import com.yzh.domain.Buy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzh.req.PageReq;
import com.yzh.req.buy.orderReq;
import com.yzh.req.buy.orderUpdateReq;
import com.yzh.req.buy.saveBuyReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.buy.buySaveResp;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 购买表 服务类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-20
 */
public interface BuyService extends IService<Buy> {

    /**
     * 新增购买
     *
     * @param req 要求事情
     */
    void saveBuy(saveBuyReq req, HttpSession session);

    /**
     * 提交订单
     *
     * @return {@link buySaveResp}
     */
    PageResp<buySaveResp> SubmitOrder(orderReq req);

    /**
     * 提交订单所有
     *
     * @param req     要求事情
     * @param session 会话
     * @return {@link PageResp}<{@link buySaveResp}>
     */
    PageResp<buySaveResp> SubmitOrderAllBySession(PageReq req, HttpSession session);

    /**
     * 更新订单
     *
     * @param req 要求事情
     */
    void updateOrder(orderUpdateReq req,HttpSession session);

    /**
     * 删除订单
     *
     * @param buyIds 买id
     */
    void deleteOrder(List<Long> buyIds);

}
