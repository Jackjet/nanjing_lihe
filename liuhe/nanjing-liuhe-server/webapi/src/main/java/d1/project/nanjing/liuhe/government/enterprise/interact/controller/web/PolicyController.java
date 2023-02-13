package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.PolicyService;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.PolicyDetailView;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.PolicyView;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 政策体系
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/policy")
@RequestMapping("/zqhd/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<PolicyView>> findAll(PolicyFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, policyService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<PolicyDetailView> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, policyService.findByIdAndEnlistByPolicyId(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
