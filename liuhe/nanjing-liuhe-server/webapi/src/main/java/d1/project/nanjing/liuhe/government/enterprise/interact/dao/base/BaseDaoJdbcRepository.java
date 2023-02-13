package d1.project.nanjing.liuhe.government.enterprise.interact.dao.base;

import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BaseDaoJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public BaseDaoJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 只查询一列数据类型对象。用于只有一行查询结果的数据
     *
     * @param sql
     * @param params
     * @param cla    Integer.class,Float.class,Double.Class,Long.class,Boolean.class,Char.class,Byte.class,Short.class
     * @return
     */
    public Object queryOneColumnForSigetonRow(String sql, Object[] params, Class cla) {
        Object result = null;
        try {
            if (params == null || params.length > 0) {
                result = jdbcTemplate.queryForObject(sql, params, cla);
            } else {
                result = jdbcTemplate.queryForObject(sql, cla);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 列表查询
     * @param sql
     * @param params
     * @param cla 集合内的对象类型
     * @return
     */
    public List queryForObjectListByClass(String sql, Object[] params,Class cla){
        try {
            if (params != null && params.length > 0) {
                return jdbcTemplate.queryForList(sql, params, cla);
            }
            return jdbcTemplate.queryForList(sql, params, cla);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 查询返回List<Map<String,Object>>格式数据,每一个Map代表一行数据，列名为key
     *
     * @param sql    sql语句
     * @param params 填充问号占位符数
     * @return
     */
    public List<Map<String, Object>> queryForMaps(String sql, Object[] params) {
        try {
            if (params != null && params.length > 0) {
                return jdbcTemplate.queryForList(sql, params);
            }
            return jdbcTemplate.queryForList(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 查询分页（PG数据库）
     *
     * @param sql     终执行查询的语句
     * @param params  填充sql语句中的问号占位符数
     * @param page    想要第几页的数据
     * @param pagerow 每页显示多少条数
     * @param cla     要封装成的实体元类型
     * @return pageList对象
     */
    public PageList queryByPageForPg(String sql, Object[] params, int page, int pagerow, Class cla) {
        String rowsql = "select count(*) from (" + sql + ") gmtxtabs_";   //查询总行数sql
        int pages = 0;   //总页数
        int rows = (Integer) queryOneColumnForSigetonRow(rowsql, params, Integer.class);  //查询总行数
        //判断页数,如果是页大小的整数倍就为rows/pageRow如果不是整数倍就为rows/pageRow+1
        if (rows % pagerow == 0) {
            pages = rows / pagerow;
        } else {
            pages = rows / pagerow + 1;
        }
        //判断最后一页
        if (rows < ((page - 1) * pagerow)) {
            if (rows % pagerow == 0) {
                page = rows / pagerow;
            } else {
                page = (rows / pagerow) + 1;
            }
        }
        //查询第page页的数据sql语句
        if (page <= 1) {
            sql += " limit " + pagerow;
        } else {
            sql += " limit " + pagerow + " offset " + ((page - 1) * pagerow);
        }
        //查询第page页数据
        List list = null;
        List result = new ArrayList();
        if (cla != null) {
            list = queryForMaps(sql, params);
            for (Object o : list) {
                result.add(BaseUtils.transformMapKey((Map<String, Object>) o));
            }
        } else {
            result = queryForMaps(sql, params);
        }

        //返回分页格式数据
        PageList pl = new PageList();
        pl.setPage(page);  //设置显示的当前页数
        pl.setPages(pages);  //设置总页数
        pl.setContent(list);   //设置当前页数据
        pl.setTotalElements(rows);    //设置总记录数
        return pl;
    }

    /**
     * 查询一条数据，返回Map
     * @param sql
     * @param params
     * @return
     */
    public Object queryForMap(String sql,Object[] params){
        Map<String,Object> result = null;
        try {
            if (params == null || params.length > 0) {
                result = jdbcTemplate.queryForMap(sql, params);
            } else {
                result = jdbcTemplate.queryForMap(sql);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 更新sql
     * @param sql
     * @author wkk
     * @date 2021/1/21 15:56
     * @return java.lang.Boolean
     **/
    public Boolean updateData(String sql){
        return jdbcTemplate.update(sql) == 0;
    }

}
