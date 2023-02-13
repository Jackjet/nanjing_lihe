package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.PolicyDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.PolicyEnlistDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.PolicyViewDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.PolicyEnlistEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.PolicyEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.PolicyDetailView;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.PolicyView;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 政策体系
 * @author wkk
 **/
@Service
public class PolicyService {

    private final PolicyDao policyDao;
    private final PolicyViewDao policyViewDao;
    private final PolicyEnlistDao policyEnlistDao;

    public PolicyService(PolicyDao policyDao, PolicyViewDao policyViewDao, PolicyEnlistDao policyEnlistDao) {
        this.policyDao = policyDao;
        this.policyViewDao = policyViewDao;
        this.policyEnlistDao = policyEnlistDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<PolicyView> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<PolicyView> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sEqual("organizationId", "organizationId")
                .sContain("organizationName", "organizationName")
                .sContain("type", "type")
                .tBetween("releaseTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return policyViewDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(PolicyInsetVm model, HttpServletRequest request) throws Exception {
        PolicyEntity entity = new PolicyEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        policyDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(PolicyUpdateVm model, HttpServletRequest request) throws DoValidException {
        PolicyEntity entity = policyDao.findById(model.getId()).orElseThrow(() -> new DoValidException("政策体系ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        policyDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("政策体系ID不能为空");
        PolicyEntity entity = policyDao.findById(id).orElseThrow(() -> new DoValidException("删除失败政策体系ID不存在"));
        policyDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("政策体系IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        policyDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public PolicyEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("政策体系ID不能为空");
        return policyDao.findById(id).orElseThrow(()-> new DoValidException("政策体系ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(PolicyReleaseVm model, HttpServletRequest request) throws DoValidException {
        if (model.getId().isEmpty()) throw new DoValidException("政策体系ID不能为空");
        PolicyEntity entity = policyDao.findById(model.getId()).orElseThrow(() -> new DoValidException("政策体系ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        policyDao.save(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public PolicyDetailView findByIdAndEnlistByPolicyId(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("政策体系ID不能为空");
        PolicyDetailView vm = new PolicyDetailView();
        PolicyEntity policyEntity = policyDao.findById(id).orElseThrow(() -> new DoValidException("政策体系ID不存在"));
        BaseUtils.copyProperties(policyEntity, vm);
        PolicyEnlistEntity policyEnlistEntity = policyEnlistDao.findByPolicyId(policyEntity.getId()).orElse(new PolicyEnlistEntity());
        if (!StringUtils.isEmpty(policyEnlistEntity)) {
            vm.setEnterpriseName(policyEnlistEntity.getEnterpriseName());
            vm.setLiaisonName(policyEnlistEntity.getLiaisonName());
            vm.setLiaisonTel(policyEnlistEntity.getLiaisonTel());
            vm.setEnlistAttachmentPath(policyEnlistEntity.getAttachmentPath());
            vm.setEnlistContent(policyEnlistEntity.getContent());
            vm.setReplyContent(policyEnlistEntity.getReplyContent());
            vm.setReplyTime(policyEnlistEntity.getUpdateTime());
        }
        return vm;
    }
}
