package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActivityDao extends JpaRepository<ActivityEntity, String>, JpaSpecificationExecutor<ActivityEntity> {
    @Modifying
    @Transactional
    @Query("delete from ActivityEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);
}
