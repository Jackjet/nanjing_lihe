package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.DailyServiceDetailDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.DailyServiceItemsDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceDetailEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceItemsEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceDetailInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceDetailUpdateVm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 日常服务详情
 * @author wkk
 **/
@Service
public class DailyServiceDetailService {

    private final DailyServiceDetailDao dailyServiceDetailDao;
    private final DailyServiceItemsDao dailyServiceItemsDao;

    public DailyServiceDetailService(DailyServiceDetailDao dailyServiceDetailDao, DailyServiceItemsDao dailyServiceItemsDao) {
        this.dailyServiceDetailDao = dailyServiceDetailDao;
        this.dailyServiceItemsDao = dailyServiceItemsDao;
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(DailyServiceDetailInsetVm model, HttpServletRequest request) throws Exception {
        DailyServiceDetailEntity entity = new DailyServiceDetailEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        dailyServiceDetailDao.save(entity);

        DailyServiceItemsEntity itemsEntity = dailyServiceItemsDao.findById(model.getItemId()).orElseThrow(() -> new DoValidException("未找到服务项信息"));
        itemsEntity.setDetailId(entity.getId());
        dailyServiceItemsDao.save(itemsEntity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(DailyServiceDetailUpdateVm model, HttpServletRequest request) throws DoValidException {
        DailyServiceDetailEntity entity = dailyServiceDetailDao.findById(model.getId()).orElseThrow(() -> new DoValidException("日常服务详情ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        dailyServiceDetailDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("日常服务详情ID不能为空");
        DailyServiceDetailEntity entity = dailyServiceDetailDao.findById(id).orElseThrow(() -> new DoValidException("删除失日常服务详情ID不存在"));
        dailyServiceDetailDao.delete(entity);

        if (!StringUtils.isEmpty(entity.getItemId())) {
            DailyServiceItemsEntity itemsEntity = dailyServiceItemsDao.findById(entity.getItemId()).orElseThrow(() -> new DoValidException("未找到服务项信息"));
            itemsEntity.setDetailId(null);
            dailyServiceItemsDao.save(itemsEntity);
        }
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public DailyServiceDetailEntity findById(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("日常服务详情ID不能为空");
        return dailyServiceDetailDao.findById(id).orElseThrow(()-> new DoValidException("日常服务详情ID不存在"));
    }

    /**
     * 根据服务项ID查询详情
     * @param itemId
     * @author wkk
     **/
    public DailyServiceDetailEntity findDetailByServiceId(String itemId) throws DoValidException {
        return dailyServiceDetailDao.findFirstByItemId(itemId).orElseThrow(() -> new DoValidException("未找到详情信息"));
    }
}
