package d1.project.nanjing.liuhe.quality.small.station.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.NewsAnnouncementEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementFindVm;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementReleaseVm;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementUpdateVm;
import d1.project.nanjing.liuhe.quality.small.station.service.NewsAnnouncementService;
import net.dcrun.component.file.server.IFileServerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 质量小站管理端-新闻公告
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/news/announcement")
@RequestMapping("/webadmin/news/announcement")
public class NewsAnnouncementController {

    private final NewsAnnouncementService newsAnnouncementService;
    private final IFileServerService fileServerService;

    public NewsAnnouncementController(NewsAnnouncementService newsAnnouncementService, IFileServerService fileServerService) {
        this.newsAnnouncementService = newsAnnouncementService;
        this.fileServerService = fileServerService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<NewsAnnouncementEntity>> findAll(NewsAnnouncementFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, newsAnnouncementService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<String> save(@Valid @RequestBody NewsAnnouncementInsetVm model, HttpServletRequest request) {
        try {
            newsAnnouncementService.save(model, request);
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
    public Result<String> update(@Valid @RequestBody NewsAnnouncementUpdateVm model, HttpServletRequest request) {
        try {
            newsAnnouncementService.update(model, request);
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
            newsAnnouncementService.delete(id);
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
            newsAnnouncementService.deleteBatch(ids, request);
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
    public Result<NewsAnnouncementEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, newsAnnouncementService.findById(id));
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
    public Result<String> release(@Valid @RequestBody NewsAnnouncementReleaseVm model, HttpServletRequest request) {
        try {
            newsAnnouncementService.release(model, request);
            return ResultUtil.success(Constants.RELEASE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.RELEASE_FAIL + e.getMessage());
        }
    }
}
