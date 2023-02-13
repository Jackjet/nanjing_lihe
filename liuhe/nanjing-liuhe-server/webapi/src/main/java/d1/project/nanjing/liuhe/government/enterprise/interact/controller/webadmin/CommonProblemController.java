package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonProblemEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.CommonProblemService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 常见问题
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/common/problem")
@RequestMapping("/webadmin/common/problem")
public class CommonProblemController {

    private final CommonProblemService commonProblemService;

    public CommonProblemController(CommonProblemService commonProblemService) {
        this.commonProblemService = commonProblemService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<CommonProblemEntity>> findAll(CommonProblemFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, commonProblemService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<String> save(@Valid @RequestBody CommonProblemInsetVm model, HttpServletRequest request) {
        try {
            commonProblemService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody CommonProblemUpdateVm model, HttpServletRequest request) {
        try {
            commonProblemService.update(model, request);
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
            commonProblemService.delete(id);
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
            commonProblemService.deleteBatch(ids, request);
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
    public Result<CommonProblemEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, commonProblemService.findById(id));
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
    public Result<String> release(@Valid @RequestBody CommonProblemReleaseVm model, HttpServletRequest request) {
        try {
            commonProblemService.release(model, request);
            return ResultUtil.success(Constants.RELEASE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.RELEASE_FAIL + e.getMessage());
        }
    }
}
