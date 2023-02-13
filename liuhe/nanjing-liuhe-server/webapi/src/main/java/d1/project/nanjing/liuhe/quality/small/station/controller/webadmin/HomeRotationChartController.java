package d1.project.nanjing.liuhe.quality.small.station.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.HomeRotationChartEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart.HomeRotationChartFindVm;
import d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart.HomeRotationChartInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart.HomeRotationChartUpdateVm;
import d1.project.nanjing.liuhe.quality.small.station.service.HomeRotationChartService;
import net.dcrun.component.file.server.IFileServerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 质量小站管理端-首页轮播图
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/home/rotationChart")
@RequestMapping("/webadmin/home/rotationChart")
public class HomeRotationChartController {

    private final HomeRotationChartService homeRotationCharTService;
    private final IFileServerService fileServerService;

    public HomeRotationChartController(HomeRotationChartService homeRotationCharTService, IFileServerService fileServerService) {
        this.homeRotationCharTService = homeRotationCharTService;
        this.fileServerService = fileServerService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<HomeRotationChartEntity>> findAll(HomeRotationChartFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, homeRotationCharTService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<String> save(@Valid @RequestBody HomeRotationChartInsetVm model, HttpServletRequest request) {
        try {
            homeRotationCharTService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody HomeRotationChartUpdateVm model, HttpServletRequest request) {
        try {
            homeRotationCharTService.update(model, request);
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
            homeRotationCharTService.delete(id);
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
     * @param request
     **/
    @DeleteMapping("deleteBatch")
    public Result<String> deleteBatch(String ids, HttpServletRequest request) {
        try {
            homeRotationCharTService.deleteBatch(ids, request);
            return ResultUtil.success(Constants.DELETE_ALL_FAIL);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.DELETE_ALL_FAIL + e.getMessage());
        }
    }

    /**
     * 根据id查询实体
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<HomeRotationChartEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, homeRotationCharTService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
