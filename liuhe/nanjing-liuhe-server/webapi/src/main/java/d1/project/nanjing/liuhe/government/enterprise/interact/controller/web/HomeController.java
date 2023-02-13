package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonlyUsedUrlEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ImportantNoticeEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.RotationChartEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.HomeSettledEnterprise.HomeSettledEnterpriseFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 政企互动-首页
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/home")
@RequestMapping("/zqhd/home")
public class HomeController {

    private final RotationChartService rotationCharTService;
    private final ImportantNoticeService importantNoticeService;
    private final CommonlyUsedUrlService commonlyUsedUrlService;
    private final HomeService homeService;
    private final HomeSettledEnterpriseService homeSettledEnterpriseService;

    public HomeController(RotationChartService rotationCharTService, ImportantNoticeService importantNoticeService, CommonlyUsedUrlService commonlyUsedUrlService, HomeService homeService, HomeSettledEnterpriseService homeSettledEnterpriseService) {
        this.rotationCharTService = rotationCharTService;
        this.importantNoticeService = importantNoticeService;
        this.commonlyUsedUrlService = commonlyUsedUrlService;
        this.homeService = homeService;
        this.homeSettledEnterpriseService = homeSettledEnterpriseService;
    }

    /**
     * 首页轮播图
     *
     * @throws Exception
     */
    @GetMapping("rotation/chart/findAll")
    public Result<List<RotationChartEntity>> rotationChartFindAll() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, rotationCharTService.findAll());
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 首页重要通知
     *
     * @throws Exception
     */
    @GetMapping("important/notice/findAll")
    public Result<List<ImportantNoticeEntity>> importantNoticeFindAll() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, importantNoticeService.findAll());
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 首页重要通知-详情
     *
     * @param id
     **/
    @GetMapping("important/notice/findById")
    public Result<ImportantNoticeEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, importantNoticeService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 首页常用链接-分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("commonly/usedurl/findAll")
    public Result<Page<CommonlyUsedUrlEntity>> findAll(CommonlyUsedUrlFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, commonlyUsedUrlService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 内容检索
     *
     * @param title
     * @throws Exception
     */
    @GetMapping("content/search")
    public Result<List<Object>> search(String title){
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, homeService.search(title));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 园区入驻企业-分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("settled/enterprise/findAll")
    public Result<Page<CommonlyUsedUrlEntity>> findAll(HomeSettledEnterpriseFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, homeSettledEnterpriseService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }


}
