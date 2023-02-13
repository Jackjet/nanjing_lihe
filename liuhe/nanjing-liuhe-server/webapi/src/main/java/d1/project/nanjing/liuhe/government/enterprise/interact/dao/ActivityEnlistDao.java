package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ActivityEnlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ActivityEnlistDao extends JpaRepository<ActivityEnlistEntity, String>, JpaSpecificationExecutor<ActivityEnlistEntity> {
    @Modifying
    @Transactional
    @Query("delete from ActivityEnlistEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);

    Optional<ActivityEnlistEntity> findFirstByActivityIdAndEnterpriseId(String activityId, String enterpriseId);
}
