package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ContactYellowPageEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.ContactYellowPageService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 联系人黄页
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/contact/yellow/page")
@RequestMapping("/webadmin/contact/yellow/page")
public class ContactYellowPageController {

    private final ContactYellowPageService contactYellowPageService;

    public ContactYellowPageController(ContactYellowPageService contactYellowPageService) {
        this.contactYellowPageService = contactYellowPageService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<ContactYellowPageEntity>> findAll(ContactYellowPageFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, contactYellowPageService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<String> save(@Valid @RequestBody ContactYellowPageInsetVm model, HttpServletRequest request) {
        try {
            contactYellowPageService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody ContactYellowPageUpdateVm model, HttpServletRequest request) {
        try {
            contactYellowPageService.update(model, request);
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
            contactYellowPageService.delete(id);
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
            contactYellowPageService.deleteBatch(ids, request);
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
    public Result<ContactYellowPageEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, contactYellowPageService.findById(id));
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
    public Result<String> release(@Valid @RequestBody ContactYellowPageReleaseVm model, HttpServletRequest request) {
        try {
            contactYellowPageService.release(model, request);
            return ResultUtil.success(Constants.RELEASE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.RELEASE_FAIL + e.getMessage());
        }
    }
}
