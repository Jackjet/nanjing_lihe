package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.excel.util.StringUtils;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.DailyServiceDetailDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.DailyServiceItemsDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceItemsEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceItemsInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService.DailyServiceItemsUpdateVm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 日常服务项
 * @author wkk
 **/
@Service
public class DailyServiceItemsService {

    private final DailyServiceItemsDao dailyServiceItemsDao;
    private final DailyServiceDetailDao dailyServiceDetailDao;

    public DailyServiceItemsService(DailyServiceItemsDao dailyServiceItemsDao, DailyServiceDetailDao dailyServiceDetailDao) {
        this.dailyServiceItemsDao = dailyServiceItemsDao;
        this.dailyServiceDetailDao = dailyServiceDetailDao;
    }

    /**
     * 根据服务id查询
     * @author wkk
     **/
    public List<DailyServiceItemsEntity> findAllByServiceId(String findAllByServiceId) throws DoValidException {
        if (StringUtils.isEmpty(findAllByServiceId)) throw new DoValidException("服务ID不可为空");
        return dailyServiceItemsDao.findAllByServiceId(findAllByServiceId);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(DailyServiceItemsInsetVm model, HttpServletRequest request) throws Exception {
        DailyServiceItemsEntity entity = new DailyServiceItemsEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        dailyServiceItemsDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(DailyServiceItemsUpdateVm model, HttpServletRequest request) throws DoValidException {
        DailyServiceItemsEntity entity = dailyServiceItemsDao.findById(model.getId()).orElseThrow(() -> new DoValidException("日常服务项ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        dailyServiceItemsDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("日常服务项ID不能为空");
        DailyServiceItemsEntity entity = dailyServiceItemsDao.findById(id).orElseThrow(() -> new DoValidException("删除失日常服务项ID不存在"));
        dailyServiceItemsDao.delete(entity);

        if (!StringUtils.isEmpty(entity.getDetailId())) {
            dailyServiceDetailDao.deleteById(entity.getDetailId());
        }
    }

    /**
     * 批量删除
     * @param ids
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("日常服务项IDS不能为空");

        List<String> listId = Arrays.asList(ids.split(","));
        dailyServiceDetailDao.deleteBatchByItemId(listId);
        dailyServiceItemsDao.deleteBatch(listId);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public DailyServiceItemsEntity findById(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("日常服务项ID不能为空");
        return dailyServiceItemsDao.findById(id).orElseThrow(()-> new DoValidException("日常服务项ID不存在"));
    }


}
