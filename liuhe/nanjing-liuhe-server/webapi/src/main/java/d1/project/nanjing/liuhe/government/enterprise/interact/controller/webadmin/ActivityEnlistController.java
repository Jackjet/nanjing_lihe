package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ActivityEnlistEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityEnlistFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityEnlistInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity.ActivityEnlistUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.ActivityEnlistService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 动态资讯-报名
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/activity/enlist")
@RequestMapping("/webadmin/activity/enlist")
public class ActivityEnlistController {

    private final ActivityEnlistService activityEnlistService;

    public ActivityEnlistController(ActivityEnlistService activityEnlistService) {
        this.activityEnlistService = activityEnlistService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<ActivityEnlistEntity>> findAll(ActivityEnlistFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, activityEnlistService.findAll((JSONObject) JSONObject.toJSON(model)));
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

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return
     */
    @PutMapping("update")
    public Result<String> update(@Valid @RequestBody ActivityEnlistUpdateVm model, HttpServletRequest request) {
        try {
            activityEnlistService.update(model, request);
            return ResultUtil.success(Constants.UPDATE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.UPDATE_FAIL + e.getMessage());
        }
    }

    /**
     * 根据id查询实体
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<ActivityEnlistEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, activityEnlistService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
