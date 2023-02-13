package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.DailyServiceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DailyServiceDetailDao extends JpaRepository<DailyServiceDetailEntity, String>, JpaSpecificationExecutor<DailyServiceDetailEntity> {
    @Modifying
    @Transactional
    @Query("delete from DailyServiceDetailEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);

    @Modifying
    @Transactional
    @Query("delete from DailyServiceDetailEntity s where s.itemId in (?1)")
    void deleteBatchByItemId(List<String> itemIds);

    Optional<DailyServiceDetailEntity> findFirstByItemId(String itemId);
}
