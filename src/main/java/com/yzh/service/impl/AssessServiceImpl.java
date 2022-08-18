package com.yzh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzh.domain.Assess;
import com.yzh.domain.Product;
import com.yzh.domain.ProductAssess;
import com.yzh.domain.User;
import com.yzh.exception.BusinessCode;
import com.yzh.exception.BusinessException;
import com.yzh.mapper.AssessMapper;
import com.yzh.mapper.ProductAssessMapper;
import com.yzh.mapper.ProductMapper;
import com.yzh.mapper.UserMapper;
import com.yzh.req.assess.QueryAssessReq;
import com.yzh.req.assess.UpdateAssessReq;
import com.yzh.req.assess.saveAssessReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.assess.AssessQueryResp;
import com.yzh.resp.assess.UserAssessResp;
import com.yzh.service.AssessService;
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
 * 评价表 服务实现类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
@Service
@Slf4j
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements AssessService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductAssessMapper productAssessMapper;

    @Resource
    private AssessMapper assessMapper;

    @Resource
    private ProductMapper productMapper;



    /**
     * 新增评估
     *
     * @param req     要求事情
     * @param session 会话
     */
    @Override
    public void saveAssess(saveAssessReq req, HttpSession session) {

        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);

        Assess assess = CopyUtil.copy(req, Assess.class);
        Long productId = req.getProductId();
        assess.setUserId(sessionUser.getUserId());
        this.save(assess);

        if (!ObjectUtils.isEmpty(productId)){
            getProductAssess(assess, productId);
        }
    }



    /**
     * 更新评估
     *
     * @param req     要求事情
     * @param session 会话
     */
    @Override
    public void updateAssess(UpdateAssessReq req, HttpSession session) {

        User sessionUser = (User) session.getAttribute(SESSION_KEYWORDS);
        Assess assess = this.getById(req.getAssessId());

        if (ObjectUtils.isEmpty(assess)){
            throw new BusinessException(BusinessCode.ASSESS_ERROR,"该评价不存在！");
        }

        if (!assess.getUserId().equals(sessionUser.getUserId())){
            throw new BusinessException(BusinessCode.ASSESS_ERROR,"无法修改他人评价");
        }
        this.updateById(CopyUtil.copy(req,Assess.class));

        if (!ObjectUtils.isEmpty(req.getProductId())){
            Product selectById = productMapper.selectById(req.getProductId());
            log.info("selectById is:{}",selectById);
            if (ObjectUtils.isEmpty(selectById)){
                throw new BusinessException(BusinessCode.ASSESS_ERROR,"当前评价不存在，无法更新！");
            }
            productAssessMapper.updateAssessContent(req.getProductId(), req.getAssessContent());
        }
    }

    /**
     * 查询评估
     *
     * @param req 要求事情
     * @return {@link PageResp}<{@link AssessQueryResp}>
     */
    @Override
    public PageResp<AssessQueryResp> queryAssess(QueryAssessReq req) {

        LambdaQueryWrapper<Assess> queryWrapper = Wrappers.lambdaQuery(Assess.class);

        //region 模糊查询
        queryWrapper
                .like(!ObjectUtils.isEmpty(req.getAssessContent()), Assess::getAssessContent, req.getAssessContent())
                .orderByDesc(Assess::getUpdateTime);
        //endregion

        //region 预期时间在某某范围内的查询
        queryWrapper.between(
                !ObjectUtils.isEmpty(req.getStartTime()) && !ObjectUtils.isEmpty(req.getEndTime()),
                Assess::getUpdateTime,
                req.getStartTime(),
                req.getEndTime()
        );
        //endregion

        Page<Assess> page = this.page(new Page<>(req.getPage(), req.getSize()), queryWrapper);

        List<AssessQueryResp> assessQueryResps = page.getRecords().stream().map(record -> {
            AssessQueryResp resp = CopyUtil.copy(record, AssessQueryResp.class);
            resp.setUser(CopyUtil.copy(userMapper.selectById(record.getUserId()), UserAssessResp.class));
            return resp;
        }).collect(Collectors.toList());


        return new PageResp<>(assessQueryResps,page.getTotal());
    }

    /**
     * 管理员删除评估
     *
     * @param ids id
     */
    @Override
    public void deleteAssess(List<Long> ids) {
        this.removeByIds(ids);
    }

    /**
     * 得到产品评估
     *
     * @param assess    评估
     * @param productId 产品id
     */
    private void getProductAssess(Assess assess, Long productId) {
        Product product;
        product = productMapper.selectById(productId);
        if (ObjectUtils.isEmpty(product)){
            throw new BusinessException(BusinessCode.PARAM_ERROR,"当前商品不存在，无法进行评价！");
        }
        ProductAssess productAssess = new ProductAssess();
        productAssess.setProductId(productId);
        productAssess.setAssessId(assess.getAssessId());
        productAssess.setAssessContent(assess.getAssessContent());
        log.info("productAssess:{}",productAssess);
        productAssessMapper.insert(productAssess);
    }
}
