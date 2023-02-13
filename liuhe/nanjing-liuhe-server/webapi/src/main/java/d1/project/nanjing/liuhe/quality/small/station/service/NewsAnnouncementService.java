package d1.project.nanjing.liuhe.quality.small.station.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.quality.small.station.dao.NewsAnnouncementDao;
import d1.project.nanjing.liuhe.quality.small.station.entity.NewsAnnouncementEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementReleaseVm;
import d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement.NewsAnnouncementUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 新闻公告
 * @author wkk
 **/
@Service
public class NewsAnnouncementService {

    private final NewsAnnouncementDao newsAnnouncementDao;

    public NewsAnnouncementService(NewsAnnouncementDao newsAnnouncementDao) {
        this.newsAnnouncementDao = newsAnnouncementDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<NewsAnnouncementEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<NewsAnnouncementEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("type", "type")
                .sContain("enable", "enable")
                .tBetween("releaseTime", "startDate", "endDate" , "yyyy-MM-dd")
                .dOrder("createTime")
                .build();
        return newsAnnouncementDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(NewsAnnouncementInsetVm model, HttpServletRequest request) throws Exception {
        NewsAnnouncementEntity entity = new NewsAnnouncementEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        newsAnnouncementDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void update(NewsAnnouncementUpdateVm model, HttpServletRequest request) throws DoValidException {
        NewsAnnouncementEntity entity = newsAnnouncementDao.findById(model.getId()).orElseThrow(() -> new DoValidException("新闻公告ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        newsAnnouncementDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("新闻公告ID不能为空");
        NewsAnnouncementEntity entity = newsAnnouncementDao.findById(id).orElseThrow(() -> new DoValidException("删除失败新闻公告ID不存在"));
        newsAnnouncementDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("新闻公告IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        newsAnnouncementDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public NewsAnnouncementEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("新闻公告ID不能为空");
        return newsAnnouncementDao.findById(id).orElseThrow(()-> new DoValidException("新闻公告ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void release(NewsAnnouncementReleaseVm model, HttpServletRequest request) throws DoValidException {
        if (model.getId().isEmpty()) throw new DoValidException("新闻公告ID不能为空");
        NewsAnnouncementEntity entity = newsAnnouncementDao.findById(model.getId()).orElseThrow(() -> new DoValidException("新闻公告ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        newsAnnouncementDao.save(entity);
    }
}
