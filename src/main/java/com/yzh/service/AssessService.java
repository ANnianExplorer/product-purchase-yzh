package com.yzh.service;

import com.yzh.domain.Assess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzh.req.assess.DeleteAssessReq;
import com.yzh.req.assess.QueryAssessReq;
import com.yzh.req.assess.UpdateAssessReq;
import com.yzh.req.assess.saveAssessReq;
import com.yzh.resp.PageResp;
import com.yzh.resp.assess.AssessQueryResp;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 评价表 服务类
 * </p>
 *
 * @author 杨振华
 * @since 2022-08-18
 */
public interface AssessService extends IService<Assess> {

    /**
     * 新增评估
     *
     * @param req 要求事情
     */
    void saveAssess(saveAssessReq req, HttpSession session);

    /**
     * 更新评估
     *
     * @param req     要求事情
     * @param session 会话
     */
    void updateAssess(UpdateAssessReq req,HttpSession session);

    /**
     * 查询评估
     *
     * @param req 要求事情
     * @return {@link PageResp}<{@link AssessQueryResp}>
     */
    PageResp<AssessQueryResp> queryAssess(QueryAssessReq req);

    /**
     * 管理员删除评估
     *
     * @param ids id
     */
    void deleteAssess(List<Long> ids);

    /**
     * 删除用户评估
     *
     * @param ids     id
     * @param session 会话
     */
    void deleteUserAssess(DeleteAssessReq req, HttpSession session);
}
