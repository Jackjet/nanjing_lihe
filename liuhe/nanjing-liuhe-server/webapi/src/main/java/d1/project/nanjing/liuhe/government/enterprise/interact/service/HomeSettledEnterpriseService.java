package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.HomeSettledEnterpriseDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.HomeSettledEnterpriseEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.HomeSettledEnterprise.HomeSettledEnterpriseInsetVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页入驻企业
 * @author wkk
 **/
@Service
public class HomeSettledEnterpriseService {

    private final HomeSettledEnterpriseDao homeSettledEnterpriseDao;
    private final EnterpriseInfoDao enterpriseInfoDao;

    public HomeSettledEnterpriseService(HomeSettledEnterpriseDao homeSettledEnterpriseDao, EnterpriseInfoDao enterpriseInfoDao) {
        this.homeSettledEnterpriseDao = homeSettledEnterpriseDao;
        this.enterpriseInfoDao = enterpriseInfoDao;
    }


    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<HomeSettledEnterpriseEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<HomeSettledEnterpriseEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enterpriseName", "enterpriseName")
                .tBetween("createTime", "startTime", "startTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return homeSettledEnterpriseDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(HomeSettledEnterpriseInsetVm model, HttpServletRequest request) throws Exception {
        String enterpriseId = model.getEnterpriseId();
        EnterpriseInfoEntity enterpriseInfo = enterpriseInfoDao.findById(model.getEnterpriseId()).orElseThrow(() -> new DoValidException("您选择的企业不存在"));
        if (Constants.ENTERPRISE_IS_SETTLED_1 != enterpriseInfo.getIsSettled()) throw new DoValidException("您选择的企业还未正式入驻");

        HomeSettledEnterpriseEntity entity = new HomeSettledEnterpriseEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        homeSettledEnterpriseDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("园区入驻企业ID不能为空");
        HomeSettledEnterpriseEntity entity = homeSettledEnterpriseDao.findById(id).orElseThrow(() -> new DoValidException("删除失败常用链接ID不存在"));
        homeSettledEnterpriseDao.delete(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public HomeSettledEnterpriseEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("园区入驻企业ID不能为空");
        return homeSettledEnterpriseDao.findById(id).orElseThrow(()-> new DoValidException("园区入驻企业ID不存在"));
    }
}
