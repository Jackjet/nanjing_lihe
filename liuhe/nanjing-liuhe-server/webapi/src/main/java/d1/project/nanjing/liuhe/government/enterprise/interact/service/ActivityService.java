package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ActivityDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ActivityEnlistDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ActivityViewDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ActivityEnlistEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ActivityEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.ActivityDetailView;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.ActivityView;
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
 * 动态资讯
 * @author wkk
 **/
@Service
public class ActivityService {

    private final ActivityDao activityDao;
    private final ActivityViewDao activityViewDao;
    private final ActivityEnlistDao activityEnlistDao;

    public ActivityService(ActivityDao activityDao, ActivityViewDao activityViewDao, ActivityEnlistDao activityEnlistDao) {
        this.activityDao = activityDao;
        this.activityViewDao = activityViewDao;
        this.activityEnlistDao = activityEnlistDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<ActivityView> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<ActivityView> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sEqual("organizationId", "organizationId")
                .sContain("organizationName", "organizationName")
                .sContain("type", "type")
                .sEqual("stage", "stage")
                .tBetween("releaseTime", "startDate", "endDate" , "yyyy-MM-dd HH:mm:ss")
                .tLessEqual("startTime", "startTime","yyyy-MM-dd HH:mm:ss")
                .tThanEqual("endTime", "startTime","yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return activityViewDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(ActivityInsetVm model, HttpServletRequest request) throws Exception {
        ActivityEntity entity = new ActivityEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        activityDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(ActivityUpdateVm model, HttpServletRequest request) throws DoValidException {
        ActivityEntity entity = activityDao.findById(model.getId()).orElseThrow(() -> new DoValidException("动态资讯ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        activityDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("动态资讯ID不能为空");
        ActivityEntity entity = activityDao.findById(id).orElseThrow(() -> new DoValidException("删除失败动态资讯ID不存在"));
        activityDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("动态资讯IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        activityDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ActivityEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("动态资讯ID不能为空");
        return activityDao.findById(id).orElseThrow(()-> new DoValidException("动态资讯ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(ActivityReleaseVm model, HttpServletRequest request) throws DoValidException {
        if (model.getId().isEmpty()) throw new DoValidException("动态资讯ID不能为空");
        ActivityEntity entity = activityDao.findById(model.getId()).orElseThrow(() -> new DoValidException("动态资讯ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        activityDao.save(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ActivityDetailView findByIdAndEnlistByActivityId(String id, HttpServletRequest request) throws Exception {
        if (id.isEmpty()) throw new DoValidException("动态资讯ID不能为空");
        ActivityDetailView vm = new ActivityDetailView();
        ActivityEntity activityEntity = activityDao.findById(id).orElseThrow(() -> new DoValidException("动态资讯ID不存在"));
        BaseUtils.copyProperties(activityEntity, vm);

        String userId = TokenManager.getInstance().getUserByToken(request).getString("id");
        ActivityEnlistEntity activityEnlistEntity = activityEnlistDao.findFirstByActivityIdAndEnterpriseId(activityEntity.getId(), userId).orElse(new ActivityEnlistEntity());
        if (!StringUtils.isEmpty(activityEnlistEntity)) {
            vm.setEnterpriseName(activityEnlistEntity.getEnterpriseName());
            vm.setLiaisonName(activityEnlistEntity.getLiaisonName());
            vm.setLiaisonTel(activityEnlistEntity.getLiaisonTel());
            vm.setEnlistAttachmentPath(activityEnlistEntity.getAttachmentPath());
            vm.setEnlistContent(activityEnlistEntity.getContent());
            vm.setReplyContent(activityEnlistEntity.getReplyContent());
            vm.setReplyTime(activityEnlistEntity.getUpdateTime());
        }
        return vm;
    }
}
