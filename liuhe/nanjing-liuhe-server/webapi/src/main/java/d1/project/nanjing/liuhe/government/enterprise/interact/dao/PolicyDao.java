package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PolicyDao extends JpaRepository<PolicyEntity, String>, JpaSpecificationExecutor<PolicyEntity> {
    @Modifying
    @Transactional
    @Query("delete from PolicyEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);
}
