package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseIdentificationDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoIdentificationEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise.EnterpriseIdentificationVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseIdentificationService {

    private final EnterpriseIdentificationDao enterpriseIdentificationDao;
    private final EnterpriseInfoDao enterpriseInfoDao;

    public EnterpriseIdentificationService(EnterpriseIdentificationDao enterpriseIdentificationDao, EnterpriseInfoDao enterpriseInfoDao) {
        this.enterpriseIdentificationDao = enterpriseIdentificationDao;
        this.enterpriseInfoDao = enterpriseInfoDao;
    }

    /**
     * 列表分页查询-未审核
     *
     * @param params 查询参数
     * @param request
     **/
    public Page<EnterpriseInfoIdentificationEntity> findAllByNotIdentification(JSONObject params, HttpServletRequest request) throws Exception {
        params.put("identification", 1);
        SpecificationBuilder<EnterpriseInfoIdentificationEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("unifiedCreditCode", "unifiedCreditCode")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sContain("email", "email")
                .sEqual("industry", "industry")
                .nEqual("identification", "identification")
                .dOrder("createTime")
                .build();
        return enterpriseIdentificationDao.findAll(specification, builder.getPageable());
    }

    /**
     * 列表分页查询-审核过
     *
     * @param params 查询参数
     * @param request
     **/
    public Page<EnterpriseInfoIdentificationEntity> findAllByExamine(JSONObject params, HttpServletRequest request) throws Exception {
        List<String> arr = new ArrayList<>();
        if (params.getInteger("identification") == null) {
            arr.add("2");
            arr.add("3");
        } else {
            arr.add(params.getString("identification"));
        }
        params.put("identification", arr);
        SpecificationBuilder<EnterpriseInfoIdentificationEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("unifiedCreditCode", "unifiedCreditCode")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sContain("email", "email")
                .sEqual("industry", "industry")
                .sIn("identification", "identification")
                .nThan("identification", "identification")
                .dOrder("createTime")
                .build();
        return enterpriseIdentificationDao.findAll(specification, builder.getPageable());
    }

    /**
     * 详情
     * @param id
     **/
    public EnterpriseInfoIdentificationEntity find(String id) throws DoValidException {
        return enterpriseIdentificationDao.findById(id).orElseThrow(() ->new DoValidException("企业认证ID不存在"));
    }

    /**
     * 认证审核
     * @param model
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void identificationExamine(EnterpriseIdentificationVm model, HttpServletRequest request) throws DoValidException {
        if (model.getIdentification() != 2 && model.getIdentification() != 3) {
            throw new DoValidException("认证审核状态非法，请确认审核状态。");
        }
        EnterpriseInfoIdentificationEntity enterpriseInfoIdentification = enterpriseIdentificationDao.findById(model.getId()).orElseThrow(() -> new DoValidException("认证审核ID不存在"));
        if (null != enterpriseInfoIdentification.getIdentification() && enterpriseInfoIdentification.getIdentification() != 1) {
            throw new DoValidException(enterpriseInfoIdentification.getEnterpriseName() + "，此企业未发起认证，请重新登录后查看审核状态。");
        }
        enterpriseInfoIdentification.setIdentification(model.getIdentification());
        enterpriseInfoIdentification.setRemarks(model.getRemarks());
        TokenManager.getInstance().updateUpdateIdAndTime(request, enterpriseInfoIdentification);
        enterpriseIdentificationDao.save(enterpriseInfoIdentification);

        EnterpriseInfoEntity entity = enterpriseInfoDao.findFirstByWebUserId(enterpriseInfoIdentification.getWebUserId()).orElseThrow(() -> new DoValidException("未找到企业用户：" + enterpriseInfoIdentification.getEnterpriseName()));
        entity.setIdentification(model.getIdentification());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        enterpriseInfoDao.save(entity);
    }
}
