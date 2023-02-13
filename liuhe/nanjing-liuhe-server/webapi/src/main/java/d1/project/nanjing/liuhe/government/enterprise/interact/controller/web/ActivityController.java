package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.ActivityService;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.ActivityDetailView;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.ActivityView;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 动态资讯
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/activity")
@RequestMapping("/zqhd/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<ActivityView>> findAll(ActivityFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, activityService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<ActivityDetailView> findById(String id, HttpServletRequest request) {
        try {
            return ResultUtil.success(Constants.SUCCESS, activityService.findByIdAndEnlistByActivityId(id,request));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
