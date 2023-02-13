package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.WorkOrderEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder.WorkOrderEvaluateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder.WorkOrderFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder.WorkOrderInsertVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.WorkOrderService;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.WorkOrderView;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 门户-工单管理
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/work/order")
@RequestMapping("/zqhd/work/order")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }


    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<WorkOrderView>> findAll(WorkOrderFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, workOrderService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 申请
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody WorkOrderInsertVm model, HttpServletRequest request) {
        try {
            workOrderService.save(model, request);
            return ResultUtil.success(Constants.APPLY_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.APPLY_FAIL + e.getMessage());
        }
    }

    /**
     * 评价
     *
     * @param model
     * @param request
     * @return
     */
    @PutMapping("evaluate")
    public Result<String> evaluate(@Valid @RequestBody WorkOrderEvaluateVm model, HttpServletRequest request) {
        try {
            workOrderService.evaluate(model, request);
            return ResultUtil.success(Constants.EVALUATE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.EVALUATE_FAIL + e.getMessage());
        }
    }

    /**
     * 催办
     *
     * @param id
     * @return
     */
    @PutMapping("urge")
    public Result<String> urge(String id) {
        try {
            workOrderService.urge(id);
            return ResultUtil.success(Constants.EVALUATE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.EVALUATE_FAIL + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<WorkOrderEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, workOrderService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
