package d1.project.nanjing.liuhe.quality.small.station.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.ServiceProviderInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoFindVm;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoReleaseVm;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoUpdateVm;
import d1.project.nanjing.liuhe.quality.small.station.service.ServiceProviderInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 质量小站管理端-服务商信息
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/service/provider")
@RequestMapping("/webadmin/service/provider")
public class ServiceProviderInfoController {

    private final ServiceProviderInfoService serviceProviderInfoService;

    public ServiceProviderInfoController(ServiceProviderInfoService serviceProviderInfoService) {
        this.serviceProviderInfoService = serviceProviderInfoService;
    }


    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<ServiceProviderInfoEntity>> findAll(ServiceProviderInfoFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, serviceProviderInfoService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<String> save(@Valid @RequestBody ServiceProviderInfoInsetVm model, HttpServletRequest request) {
        try {
            serviceProviderInfoService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody ServiceProviderInfoUpdateVm model, HttpServletRequest request) {
        try {
            serviceProviderInfoService.update(model, request);
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
            serviceProviderInfoService.delete(id);
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
            serviceProviderInfoService.deleteBatch(ids, request);
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
    public Result<ServiceProviderInfoEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, serviceProviderInfoService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return
     */
    @PutMapping("release")
    public Result<String> release(@Valid @RequestBody ServiceProviderInfoReleaseVm model, HttpServletRequest request) {
        try {
            serviceProviderInfoService.release(model, request);
            return ResultUtil.success(Constants.RELEASE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.RELEASE_FAIL + e.getMessage());
        }
    }
}
