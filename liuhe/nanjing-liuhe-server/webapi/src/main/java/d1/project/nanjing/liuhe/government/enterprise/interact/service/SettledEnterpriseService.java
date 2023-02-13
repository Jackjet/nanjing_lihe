package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.SettledEnterpriseDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.SettledEnterpriseEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise.SettledEnterpriseExamineVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise.SettledEnterpriseInsetVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 入驻企业
 * @author wkk
 **/
@Service
public class SettledEnterpriseService {

    private final SettledEnterpriseDao settledEnterpriseDao;
    private final EnterpriseInfoDao enterpriseInfoDao;

    public SettledEnterpriseService(SettledEnterpriseDao settledEnterpriseDao, EnterpriseInfoDao enterpriseInfoDao) {
        this.settledEnterpriseDao = settledEnterpriseDao;
        this.enterpriseInfoDao = enterpriseInfoDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<SettledEnterpriseEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<SettledEnterpriseEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sEqual("enterpriseId", "enterpriseId")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sEqual("type", "type")
                .sEqual("status", "status")
                .tBetween("createTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return settledEnterpriseDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(SettledEnterpriseInsetVm model, HttpServletRequest request) throws Exception {
        SettledEnterpriseEntity entity = new SettledEnterpriseEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setStatus(Constants.SETTLED_ENTERPRISE_0);
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        settledEnterpriseDao.save(entity);
    }

    /**
     * 审批
     *
     * @param model
     * @param request
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void examine(SettledEnterpriseExamineVm model, HttpServletRequest request) throws DoValidException {
        SettledEnterpriseEntity entity = settledEnterpriseDao.findById(model.getId()).orElseThrow(() -> new DoValidException("入驻企业ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        settledEnterpriseDao.save(entity);

        if (Constants.SETTLED_ENTERPRISE_2.equals(entity.getStatus())) {
            EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.findById(entity.getEnterpriseId()).orElseThrow(() -> new DoValidException("企业ID不存在：" + entity.getEnterpriseId()));
            enterpriseInfoEntity.setIsSettled(Constants.ENTERPRISE_IS_SETTLED_1);
            TokenManager.getInstance().updateUpdateIdAndTime(request, enterpriseInfoEntity);
            enterpriseInfoDao.save(enterpriseInfoEntity);
        }
    }

    /**
     * 详情
     * @param id
     **/
    public SettledEnterpriseEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("入驻企业ID不能为空");
        return settledEnterpriseDao.findById(id).orElseThrow(()-> new DoValidException("入驻企业ID不存在"));
    }
}
