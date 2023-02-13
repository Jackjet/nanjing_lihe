package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ActivityEnlistDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ActivityEnlistEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityEnlistInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityEnlistUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 动态资讯-报名
 * @author wkk
 **/
@Service
public class ActivityEnlistService {

    private final ActivityEnlistDao activityEnlistDao;

    public ActivityEnlistService(ActivityEnlistDao activityEnlistDao) {
        this.activityEnlistDao = activityEnlistDao;
    }


    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<ActivityEnlistEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<ActivityEnlistEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sEqual("activityId", "activityId")
                .sEqual("enterpriseId", "enterpriseId")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .tBetween("createTime", "startTime" , "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return activityEnlistDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(ActivityEnlistInsetVm model, HttpServletRequest request) throws Exception {
        ActivityEnlistEntity entity = new ActivityEnlistEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        activityEnlistDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(ActivityEnlistUpdateVm model, HttpServletRequest request) throws DoValidException {
        ActivityEnlistEntity entity = activityEnlistDao.findById(model.getId()).orElseThrow(() -> new DoValidException("报名ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        activityEnlistDao.save(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ActivityEnlistEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("报名ID不能为空");
        return activityEnlistDao.findById(id).orElseThrow(()-> new DoValidException("报名ID不存在"));
    }


}
