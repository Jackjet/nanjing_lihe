package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityEnlistInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.ActivityEnlistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 动态资讯-报名
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/activity/enlist")
@RequestMapping("/zqhd/activity/enlist")
public class ActivityEnlistController {

    private final ActivityEnlistService activityEnlistService;

    public ActivityEnlistController(ActivityEnlistService activityEnlistService) {
        this.activityEnlistService = activityEnlistService;
    }

    /**
     * 报名
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody ActivityEnlistInsetVm model, HttpServletRequest request) {
        try {
            activityEnlistService.save(model, request);
            return ResultUtil.success(Constants.ADD_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.ADD_FAIL + e.getMessage());
        }
    }
}
