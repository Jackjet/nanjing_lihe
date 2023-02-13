package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonProblemEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.CommonProblemView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommonProblemDao extends JpaRepository<CommonProblemEntity, String>, JpaSpecificationExecutor<CommonProblemEntity> {
    @Modifying
    @Transactional
    @Query("delete from CommonProblemEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);

    @Query(value = "select new d1.project.nanjing.liuhe.government.enterprise.interact.view.CommonProblemView(waue.id, waue.title) from  CommonProblemEntity as waue where waue.enable = ?1 ORDER BY waue.createTime desc")
    List<CommonProblemView> findAllByEnableOrderByCreateTime(String enable);
}
