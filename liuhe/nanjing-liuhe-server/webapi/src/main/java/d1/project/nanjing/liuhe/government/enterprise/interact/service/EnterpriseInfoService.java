package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserUpdateAllEnableVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserUpdateEnableVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserUpdatePasswordResetVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnterpriseInfoService {

    private final EnterpriseInfoDao enterpriseInfoDao;
    private final ZqhdWebUserService zqhdWebUserService;

    public EnterpriseInfoService(EnterpriseInfoDao enterpriseInfoDao, ZqhdWebUserService zqhdWebUserService) {
        this.enterpriseInfoDao = enterpriseInfoDao;
        this.zqhdWebUserService = zqhdWebUserService;
    }


    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<EnterpriseInfoEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<EnterpriseInfoEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("unifiedCreditCode", "unifiedCreditCode")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sContain("email", "email")
                .sEqual("industry", "industry")
                .sEqual("scale", "scale")
                .sEqual("employeesSize", "employeesSize")
                .sEqual("registeredCapital", "registeredCapital")
                .sEqual("isSettled", "isSettled")
                .sEqual("enable", "enable")
                .dOrder("createTime")
                .build();
        return enterpriseInfoDao.findAll(specification, builder.getPageable());
    }

    /**
     * 详情
     * @param id
     * @author wkk
     **/
    public EnterpriseInfoEntity find(String id) throws DoValidException {
        return enterpriseInfoDao.findById(id).orElseThrow(() ->new DoValidException("用户ID不存在"));
    }

    /**
     * 重置密码列表
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePasswordReset(WebUserUpdatePasswordResetVm model, HttpServletRequest request) throws DoValidException {
        List<String> ids = model.getIds();
        List<EnterpriseInfoEntity> webUserInfos = enterpriseInfoDao.findAllById(ids);
        ids.clear();
        webUserInfos.forEach(webUser -> ids.add(webUser.getWebUserId()));
        zqhdWebUserService.updatePasswordReset(ids, request);
    }

    /**
     * 批量更新所有启用
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAllEnable(WebUserUpdateAllEnableVm model, HttpServletRequest request) throws DoValidException {
        List<String> ids = new ArrayList<>();
        List<EnterpriseInfoEntity> webUserInfoEntitys = enterpriseInfoDao.findAllById(model.getIds()).stream().peek(webUserInfo -> webUserInfo.setEnable(model.getEnable())).collect(Collectors.toList());
        enterpriseInfoDao.saveAll(webUserInfoEntitys);

        webUserInfoEntitys.forEach(webUserInfo -> ids.add(webUserInfo.getId()));
        zqhdWebUserService.updateAllEnable(ids, model.getEnable(), request);
    }

    /**
     * 更新启用禁用
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEnable(WebUserUpdateEnableVm model, HttpServletRequest request) throws DoValidException {
        EnterpriseInfoEntity webUserInfoEntity = enterpriseInfoDao.findById(model.getId()).orElseThrow(() -> new DoValidException("用户ID不存在"));
        webUserInfoEntity.setEnable(model.getEnable());
        enterpriseInfoDao.save(webUserInfoEntity);
        zqhdWebUserService.updateEnable(webUserInfoEntity.getWebUserId(), model.getEnable() , request);
    }

    /**
     * 新增用户信息
     */
    public void insertUserInfo(EnterpriseInfoEntity entity) throws DoValidException {
        entity.setId(BaseUtils.generate32Id());
        entity.setCreateTime(Calendar.getInstance());
        entity.setIdentification(Constants.IDENTIFICATION_0);//未认证
        entity.setEnable(Constants.ENABLE_1);//启用
        enterpriseInfoDao.save(entity);
    }

    /**
     * 根据用户id检查用户认证状态
     * @param userId
     * @return
     */
    public Integer checkWebUserIdentification(String userId){
        try {
            return enterpriseInfoDao.findFirstByWebUserId(userId).orElseThrow(() -> new DoValidException("用户ID不存在")).getIdentification();
        } catch (Exception e) {
            return 0;
        }

    }
}
