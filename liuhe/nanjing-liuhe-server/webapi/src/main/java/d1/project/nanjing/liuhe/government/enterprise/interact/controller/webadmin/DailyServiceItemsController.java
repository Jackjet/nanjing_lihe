package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceItemsEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceItemsInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceItemsUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.DailyServiceItemsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 日常服务项
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/daily/service/items")
@RequestMapping("/webadmin/daily/service/items")
public class DailyServiceItemsController {

    private final DailyServiceItemsService dailyServiceItemsService;

    public DailyServiceItemsController(DailyServiceItemsService dailyServiceItemsService) {
        this.dailyServiceItemsService = dailyServiceItemsService;
    }

    /**
     * 根据服务ID查询
     *
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<List<DailyServiceItemsEntity>> findAll(String serviceId) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, dailyServiceItemsService.findAllByServiceId(serviceId));
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
    public Result<String> save(@Valid @RequestBody DailyServiceItemsInsetVm model, HttpServletRequest request) {
        try {
            dailyServiceItemsService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody DailyServiceItemsUpdateVm model, HttpServletRequest request) {
        try {
            dailyServiceItemsService.update(model, request);
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
            dailyServiceItemsService.delete(id);
            return ResultUtil.success(Constants.DELETE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.DELETE_FAIL + e.getMessage());
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     **/
    @DeleteMapping("deleteBatch")
    public Result<String> deleteBatch(String ids) {
        try {
            dailyServiceItemsService.deleteBatch(ids);
            return ResultUtil.success(Constants.DELETE_ALL_FAIL);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.DELETE_ALL_FAIL + e.getMessage());
        }
    }
}
