package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import d1.project.nanjing.liuhe.government.enterprise.interact.dao.base.BaseDaoJdbcRepository;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.base.PageList;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 首页Service
 * @author wkk
 **/
@Service
public class HomeService {

    private final BaseDaoJdbcRepository baseDaoJdbcRepository;

    public HomeService(BaseDaoJdbcRepository baseDaoJdbcRepository) {
        this.baseDaoJdbcRepository = baseDaoJdbcRepository;
    }

    /**
     * 查询100条检索数据
     *
     * @param title 查询参数
     * @author wkk
     **/
    public List<Object> search(String title) throws Exception {
        List<Object> result = new ArrayList<>();
        if (!StringUtils.isEmpty(title)) {
            List<String> tables = Arrays.asList(new String[]{"d1_zqhd_activity,动态资讯","d1_zqhd_policy,政策体系","d1_zqhd_important_notice,重要通知","d1_zqhd_commonly_used_url,常用链接","d1_zqhd_common_problem,常见问题","d1_zqhd_online_classroom,线上课堂"});
            for (String table : tables) {
                String[] t = table.split(",");
                PageList pageList = baseDaoJdbcRepository.queryByPageForPg(getSqlByTableName(t[0], t[1]), new String[]{"%" + title + "%"}, 1, 100 - result.size(), Object.class);
                if (pageList.getTotalElements() > 0) {
                    pageList.getContent().forEach(o -> result.add(o));
                }
                if (result.size() >= 100) return result;
            }
        }
        return result;
    }

    /**
     * 根据表名生成sql查询语句
     * @param tableName
     * @author wkk
     * @date 2021/9/1 16:40
     * @return java.lang.String
     **/
    private String getSqlByTableName(String  tableName, String type){
        return "SELECT t.id, t.title, t.content, t.release_time , '" + type + "' as type FROM " + tableName + " t WHERE t.enable = '启用' AND t.title LIKE ? ";
    }


}
