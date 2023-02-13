package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.DailyServiceDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.DailyServiceItemsDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceUpdateVm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日常服务
 * @author wkk
 **/
@Service
public class DailyServiceService {

    private final DailyServiceDao dailyServiceDao;
    private final DailyServiceItemsDao dailyServiceItemsDao;

    public DailyServiceService(DailyServiceDao dailyServiceDao, DailyServiceItemsDao dailyServiceItemsDao) {
        this.dailyServiceDao = dailyServiceDao;
        this.dailyServiceItemsDao = dailyServiceItemsDao;
    }

    /**
     * 查询所有数据
     * @author wkk
     * @date 2021/9/7 9:54
     * @return java.util.List<d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceEntity>
     **/
    public List<DailyServiceEntity> findAll() {
        return dailyServiceDao.findAll();
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(DailyServiceInsetVm model, HttpServletRequest request) throws Exception {
        DailyServiceEntity entity = new DailyServiceEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        dailyServiceDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(DailyServiceUpdateVm model, HttpServletRequest request) throws DoValidException {
        DailyServiceEntity entity = dailyServiceDao.findById(model.getId()).orElseThrow(() -> new DoValidException("日常服务ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        dailyServiceDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("日常服务ID不能为空");
        if (dailyServiceItemsDao.existsByServiceId(id)) throw new DoValidException("请先删除服务项");
        dailyServiceDao.deleteById(id);
    }
}
