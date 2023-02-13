package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonlyUsedUrlEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.CommonlyUsedUrlService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 常用链接
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/commonly/used/url")
@RequestMapping("/webadmin/commonly/used/url")
public class CommonlyUsedUrlController {

    private final CommonlyUsedUrlService commonlyUsedUrlService;

    public CommonlyUsedUrlController(CommonlyUsedUrlService commonlyUsedUrlService) {
        this.commonlyUsedUrlService = commonlyUsedUrlService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
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
     * 新增
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody CommonlyUsedUrlInsetVm model, HttpServletRequest request) {
        try {
            commonlyUsedUrlService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody CommonlyUsedUrlUpdateVm model, HttpServletRequest request) {
        try {
            commonlyUsedUrlService.update(model, request);
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
            commonlyUsedUrlService.delete(id);
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
            commonlyUsedUrlService.deleteBatch(ids, request);
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
    public Result<CommonlyUsedUrlEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, commonlyUsedUrlService.findById(id));
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
    public Result<String> release(@Valid @RequestBody CommonlyUsedUrlReleaseVm model, HttpServletRequest request) {
        try {
            commonlyUsedUrlService.release(model, request);
            return ResultUtil.success(Constants.RELEASE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.RELEASE_FAIL + e.getMessage());
        }
    }
}
