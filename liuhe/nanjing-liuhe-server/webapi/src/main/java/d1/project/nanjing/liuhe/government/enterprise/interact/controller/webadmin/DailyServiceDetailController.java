package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceDetailEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceDetailInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceDetailUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.DailyServiceDetailService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 日常服务详情
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/daily/service/detail")
@RequestMapping("/webadmin/daily/service/detail")
public class DailyServiceDetailController {

    private final DailyServiceDetailService dailyServiceDetailService;

    public DailyServiceDetailController(DailyServiceDetailService dailyServiceDetailService) {
        this.dailyServiceDetailService = dailyServiceDetailService;
    }

    /**
     * 新增
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody DailyServiceDetailInsetVm model, HttpServletRequest request) {
        try {
            dailyServiceDetailService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody DailyServiceDetailUpdateVm model, HttpServletRequest request) {
        try {
            dailyServiceDetailService.update(model, request);
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
            dailyServiceDetailService.delete(id);
            return ResultUtil.success(Constants.DELETE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.DELETE_FAIL + e.getMessage());
        }
    }

    /**
     * 服务详情
     *
     * @param itemId
     **/
    @GetMapping("findByItemId")
    public Result<DailyServiceDetailEntity> findByItemId(String itemId) {
        try {
            return ResultUtil.success(Constants.SUCCESS, dailyServiceDetailService.findDetailByServiceId(itemId));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
