package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.PolicyEnlistEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyEnlistFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyEnlistInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyEnlistUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.PolicyEnlistService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 政策体系-报名
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/policy/enlist")
@RequestMapping("/webadmin/policy/enlist")
public class PolicyEnlistController {

    private final PolicyEnlistService policyEnlistService;

    public PolicyEnlistController(PolicyEnlistService policyEnlistService) {
        this.policyEnlistService = policyEnlistService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<PolicyEnlistEntity>> findAll(PolicyEnlistFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, policyEnlistService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 新增
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody PolicyEnlistInsetVm model, HttpServletRequest request) {
        try {
            policyEnlistService.save(model, request);
            return ResultUtil.success(Constants.ADD_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.ADD_FAIL + e.getMessage());
        }
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return
     */
    @PutMapping("update")
    public Result<String> update(@Valid @RequestBody PolicyEnlistUpdateVm model, HttpServletRequest request) {
        try {
            policyEnlistService.update(model, request);
            return ResultUtil.success(Constants.UPDATE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.UPDATE_FAIL + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<PolicyEnlistEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, policyEnlistService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
