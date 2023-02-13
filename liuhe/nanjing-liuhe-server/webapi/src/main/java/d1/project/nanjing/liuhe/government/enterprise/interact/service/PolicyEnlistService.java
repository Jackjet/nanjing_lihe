package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.PolicyEnlistDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.PolicyEnlistEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyEnlistInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyEnlistUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 政策体系-报名
 * @author wkk
 **/
@Service
public class PolicyEnlistService {

    private final PolicyEnlistDao policyEnlistDao;

    public PolicyEnlistService(PolicyEnlistDao policyEnlistDao) {
        this.policyEnlistDao = policyEnlistDao;
    }


    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<PolicyEnlistEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<PolicyEnlistEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sEqual("policyId", "policyId")
                .sEqual("enterpriseId", "enterpriseId")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .tBetween("createTime", "startTime" , "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return policyEnlistDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(PolicyEnlistInsetVm model, HttpServletRequest request) throws Exception {
        PolicyEnlistEntity entity = new PolicyEnlistEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        policyEnlistDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(PolicyEnlistUpdateVm model, HttpServletRequest request) throws DoValidException {
        PolicyEnlistEntity entity = policyEnlistDao.findById(model.getId()).orElseThrow(() -> new DoValidException("报名ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        policyEnlistDao.save(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public PolicyEnlistEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("报名ID不能为空");
        return policyEnlistDao.findById(id).orElseThrow(()-> new DoValidException("报名ID不存在"));
    }


}
