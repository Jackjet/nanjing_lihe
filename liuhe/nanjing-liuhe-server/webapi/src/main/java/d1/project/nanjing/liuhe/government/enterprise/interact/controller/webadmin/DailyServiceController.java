package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.DailyServiceService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 日常服务
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/daily/service")
@RequestMapping("/webadmin/daily/service")
public class DailyServiceController {

    private final DailyServiceService dailyServiceService;

    public DailyServiceController(DailyServiceService dailyServiceService) {
        this.dailyServiceService = dailyServiceService;
    }

    /**
     * 查询全部
     *
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<List<DailyServiceEntity>> findAll() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, dailyServiceService.findAll());
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
    public Result<String> save(@Valid @RequestBody DailyServiceInsetVm model, HttpServletRequest request) {
        try {
            dailyServiceService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody DailyServiceUpdateVm model, HttpServletRequest request) {
        try {
            dailyServiceService.update(model, request);
            return ResultUtil.success(Constants.UPDATE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.UPDATE_FAIL + e.getMessage());
        }
    }

    /**
     * 单条删除
     *
     * @param id
     **/
    @DeleteMapping("delete")
    public Result<String> delete(String id) {
        try {
            dailyServiceService.delete(id);
            return ResultUtil.success(Constants.DELETE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.DELETE_FAIL + e.getMessage());
        }
    }
}
