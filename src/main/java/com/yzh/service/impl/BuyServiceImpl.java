package com.yzh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzh.domain.Buy;
import com.yzh.domain.Product;
import com.yzh.domain.User;
import com.yzh.exception.BusinessCode;
import com.yzh.exception.BusinessException;
import com.yzh.mapper.BuyMapper;
import com.yzh.mapper.ProductMapper;
import com.yzh.mapper.UserMapper;
import com.yzh.req.PageReq;
import com.yzh.req.buy.orderReq;
import com.yzh.req.buy.orderUpdateReq;
import com.yzh.req.buy.saveBuyReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.assess.UserAssessResp;
import com.yzh.resp.buy.ProductBuyResp;
import com.yzh.resp.buy.buySaveResp;
import com.yzh.resp.user.UserQueryResp;
import com.yzh.service.BuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzh.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

import static com.yzh.constant.UserConstant.SESSION_KEYWORDS;

/**
 * <p>
 * 购买表 服务实现类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-20
 */
@Service
@Slf4j
public class BuyServiceImpl extends ServiceImpl<BuyMapper, Buy> implements BuyService {

    @Resource
    private BuyMapper buyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    /**
     * 新增购买
     *
     * @param req 要求事情
     */
    @Override
    public void saveBuy(saveBuyReq req , HttpSession session) {

        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);

        Buy buy = CopyUtil.copy(req, Buy.class);
        buy.setUserId(sessionUser.getUserId());

        Product product = productMapper.selectById(req.getProductId());

        List<Buy> buys = buyMapper.getBuyUser(sessionUser.getUserId());
        List<Buy> buyUsers = CopyUtil.copyList(buys, Buy.class);

        if (!ObjectUtils.isEmpty(buyUsers)){
            for (int i = 0; i < buyUsers.size(); i++) {
                buy.setUserAddress(buyUsers.get(0).getUserAddress());
            }
        }
        buy.setBuyAmount(req.getBuyAmount());
        buy.setLumpSum(Integer.parseInt(product.getProductPrice()));
        this.save(buy);

    }

    /**
     * 提交订单
     *
     * @param req 要求事情
     * @return {@link PageResp}<{@link buySaveResp}>
     */
    @Override
    public PageResp<buySaveResp> SubmitOrder(orderReq req) {

        Page<Buy> page = this.page(
                new Page<>(req.getPage(), req.getSize()),
                Wrappers
                        .lambdaQuery(Buy.class)
                        .eq(Buy::getBuyId,req.getBuyId())
        );

        return new PageResp<>(getUserAndProduct(page),page.getTotal());

    }

    @Override
    public PageResp<buySaveResp> SubmitOrderAllBySession(PageReq req,HttpSession session) {
        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);
        Page<Buy> page = this.page(
                new Page<>(req.getPage(), req.getSize()),
                Wrappers
                        .lambdaQuery(Buy.class)
                        .eq(Buy::getUserId,sessionUser.getUserId())
        );

        return new PageResp<>(getUserAndProduct(page),page.getTotal());
    }

    /**
     * 更新订单
     *
     * @param req 要求事情
     */
    @Override
    public void updateOrder(orderUpdateReq req,HttpSession session) {
        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);
        if (!req.getUserId().equals(sessionUser.getUserId())){
            throw new BusinessException(BusinessCode.BUY_ERROR,"无法修改他人订单！");
        }
        Buy buy = CopyUtil.copy(req, Buy.class);
        buy.setUserId(sessionUser.getUserId());

        Buy buysession = buyMapper.selectOne(Wrappers
                .lambdaQuery(Buy.class)
                .eq(Buy::getBuyId, req.getBuyId()));

        Product product = productMapper.selectById(buysession.getProductId());

        buy.setBuyAmount(req.getBuyAmount());
        buy.setLumpSum(Integer.parseInt(product.getProductPrice()) * req.getBuyAmount());

        this.updateById(buy);
    }

    /**
     * 删除订单
     *
     * @param buyIds 买id
     */
    @Override
    public void deleteOrder(List<Long> buyIds) {
        this.removeByIds(buyIds);
    }

    /**
     * 获取用户和产品
     *
     * @param page 页面
     * @return {@link List}<{@link buySaveResp}>
     */
    private List<buySaveResp> getUserAndProduct(Page<Buy> page) {
        return page.getRecords().stream().map(record -> {
            buySaveResp resp = CopyUtil.copy(record, buySaveResp.class);
            //设置user
            resp.setUser(CopyUtil.copy(userMapper.selectById(record.getUserId()), UserAssessResp.class));

            //设置标签
            resp.setProduct(CopyUtil.copy(productMapper.selectById(record.getProductId()), ProductBuyResp.class));
            return resp;
        }).collect(Collectors.toList());
    }
}
