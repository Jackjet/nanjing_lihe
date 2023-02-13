package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.DailyServiceDetailService;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.DailyServiceItemsService;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.DailyServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日常服务
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/daily/service")
@RequestMapping("/zqhd/daily/service")
public class DailyServiceController {

    private final DailyServiceService dailyServiceService;
    private final DailyServiceItemsService dailyServiceItemsService;
    private final DailyServiceDetailService dailyServiceDetailService;

    public DailyServiceController(DailyServiceService dailyServiceService, DailyServiceItemsService dailyServiceItemsService, DailyServiceDetailService dailyServiceDetailService) {
        this.dailyServiceService = dailyServiceService;
        this.dailyServiceItemsService = dailyServiceItemsService;
        this.dailyServiceDetailService = dailyServiceDetailService;
    }

    /**
     * 日常服务-查询全部
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
     * 日常服务项-根据服务ID查询
     *
     * @throws Exception
     */
    @GetMapping("findAllByServiceId")
    public Result<List<DailyServiceEntity>> findAllByServiceId(String serviceId) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, dailyServiceItemsService.findAllByServiceId(serviceId));
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 日常服务详情-根据服务项ID查询
     *
     * @throws Exception
     */
    @GetMapping("findDetailByItemId")
    public Result<List<DailyServiceEntity>> findDetailByItemId(String serviceId) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, dailyServiceDetailService.findDetailByServiceId(serviceId));
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }


}
