package d1.project.nanjing.liuhe.quality.small.station.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.NewsAnnouncementEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementFindVm;
import d1.project.nanjing.liuhe.quality.small.station.service.NewsAnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 质量小站-新闻公告
 * @author wkk
 **/
@RestController("/zlxz/news/announcement")
@RequestMapping("/zlxz/news/announcement")
public class NewsAnnouncementController {

    private final NewsAnnouncementService newsAnnouncementService;

    public NewsAnnouncementController(NewsAnnouncementService newsAnnouncementService) {
        this.newsAnnouncementService = newsAnnouncementService;
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
     * 详情
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
}
