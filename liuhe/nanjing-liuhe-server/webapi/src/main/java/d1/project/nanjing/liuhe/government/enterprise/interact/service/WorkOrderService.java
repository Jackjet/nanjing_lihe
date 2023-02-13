package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.WorkOrderDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.WorkOrderViewDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.WorkOrderEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder.WorkOrderEvaluateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder.WorkOrderInsertVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder.WorkOrderReplyVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.WorkOrderView;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * 工单管理
 * @author wkk
 **/
@Service
public class WorkOrderService {

    private final WorkOrderDao workOrderDao;
    private final WorkOrderViewDao workOrderViewDao;
    private final EnterpriseInfoDao enterpriseInfoDao;

    public WorkOrderService(WorkOrderDao workOrderDao, WorkOrderViewDao workOrderViewDao, EnterpriseInfoDao enterpriseInfoDao) {
        this.workOrderDao = workOrderDao;
        this.workOrderViewDao = workOrderViewDao;
        this.enterpriseInfoDao = enterpriseInfoDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<WorkOrderView> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<WorkOrderView> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sEqual("enterpriseType", "enterpriseType")
                .sEqual("status", "status")
                .sEqual("serviceName", "serviceName")
                .sEqual("serviceItemName", "serviceItemName")
                .nEqual("urge", "urge")
                .tBetween("createTime", "startDate", "endDate" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return workOrderViewDao.findAll(specification, builder.getPageable());
    }

    /**
     * 申请
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(WorkOrderInsertVm model, HttpServletRequest request) throws Exception {
        String userId = TokenManager.getInstance().getUserByToken(request).getString("id");
        EnterpriseInfoEntity enterpriseInfo = enterpriseInfoDao.findFirstByWebUserId(userId).orElseThrow(() -> new DoValidException("企业信息不存在"));

        WorkOrderEntity entity = new WorkOrderEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setEnterpriseId(enterpriseInfo.getId());
        entity.setEnterpriseName(enterpriseInfo.getEnterpriseName());
        entity.setEnterpriseType(enterpriseInfo.getType());
        entity.setUrge(Constants.URGE_0);
        entity.setStatus(Constants.WORK_STATUS_0);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        workOrderDao.save(entity);
    }

    /**
     * 回复
     *
     * @param model
     * @param request
     * @return void
     **/
    public void reply(WorkOrderReplyVm model, HttpServletRequest request) throws DoValidException {
        WorkOrderEntity entity = workOrderDao.findById(model.getId()).orElseThrow(() -> new DoValidException("工单ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        entity.setStatus(Constants.WORK_STATUS_1);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        workOrderDao.save(entity);
    }

    /**
     * 评价
     *
     * @param model
     * @param request
     * @return void
     **/
    public void evaluate(WorkOrderEvaluateVm model, HttpServletRequest request) throws DoValidException {
        WorkOrderEntity entity = workOrderDao.findById(model.getId()).orElseThrow(() -> new DoValidException("工单ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        entity.setStatus(Constants.WORK_STATUS_2);
        entity.setUpdateTime(Calendar.getInstance());
        workOrderDao.save(entity);
    }

    /**
     * 催办
     *
     * @param id
     * @throws Exception
     */
    public void urge(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("工单ID不能为空");
        WorkOrderEntity entity = workOrderDao.findById(id).orElseThrow(() -> new DoValidException("工单ID不存在：" + id));
        entity.setUpdateTime(Calendar.getInstance());
        entity.setUrge(Constants.URGE_1);
        workOrderDao.save(entity);
    }

    /**
     * 详情
     * @param id
     **/
    public WorkOrderEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("工单ID不能为空");
        return workOrderDao.findById(id).orElseThrow(()-> new DoValidException("工单ID不存在"));
    }
}
