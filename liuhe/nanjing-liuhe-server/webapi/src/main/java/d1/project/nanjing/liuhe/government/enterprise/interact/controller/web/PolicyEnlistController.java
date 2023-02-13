package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy.PolicyEnlistInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.PolicyEnlistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 政策体系-报名
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/policy/enlist")
@RequestMapping("/zqhd/policy/enlist")
public class PolicyEnlistController {

    private final PolicyEnlistService policyEnlistService;

    public PolicyEnlistController(PolicyEnlistService policyEnlistService) {
        this.policyEnlistService = policyEnlistService;
    }

    /**
     * 报名
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
}
