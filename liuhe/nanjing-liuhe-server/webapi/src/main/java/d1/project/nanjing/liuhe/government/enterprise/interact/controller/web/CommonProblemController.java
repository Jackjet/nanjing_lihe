package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonProblemEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.CommonProblemService;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.CommonProblemView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 常见问题
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/common/problem")
@RequestMapping("/zqhd/common/problem")
public class CommonProblemController {

    private final CommonProblemService commonProblemService;

    public CommonProblemController(CommonProblemService commonProblemService) {
        this.commonProblemService = commonProblemService;
    }

    /**
     * 列表查询
     *
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<List<CommonProblemView>> findAll() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, commonProblemService.findAll());
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
    public Result<CommonProblemEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, commonProblemService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
