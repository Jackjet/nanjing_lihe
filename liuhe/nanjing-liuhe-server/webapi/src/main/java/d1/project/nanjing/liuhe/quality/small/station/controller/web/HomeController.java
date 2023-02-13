package d1.project.nanjing.liuhe.quality.small.station.controller.web;

import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.HomeRotationChartEntity;
import d1.project.nanjing.liuhe.quality.small.station.entity.ServiceProviderInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.service.HomeRotationChartService;
import d1.project.nanjing.liuhe.quality.small.station.service.ServiceProviderInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 质量小站-首页
 * @author wkk
 **/
@RestController("/zlxz/home")
@RequestMapping("/zlxz/home")
public class HomeController {

    private final HomeRotationChartService homeRotationCharTService;
    private final ServiceProviderInfoService serviceProviderInfoService;

    public HomeController(HomeRotationChartService homeRotationCharTService, ServiceProviderInfoService serviceProviderInfoService) {
        this.homeRotationCharTService = homeRotationCharTService;
        this.serviceProviderInfoService = serviceProviderInfoService;
    }

    /**
     * 首页轮播图
     *
     * @throws Exception
     */
    @GetMapping("rotationChart/findAll")
    public Result<Page<HomeRotationChartEntity>> findAll() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, homeRotationCharTService.findAll());
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 首页服务商信息
     *
     * @throws Exception
     */
    @GetMapping("service/provider/findAll")
    public Result<Page<ServiceProviderInfoEntity>> findAllByProvider() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, serviceProviderInfoService.findAllByProvider());
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 服务商信息-详情
     *
     * @param id
     **/
    @GetMapping("service/provider/findById")
    public Result<ServiceProviderInfoEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, serviceProviderInfoService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
