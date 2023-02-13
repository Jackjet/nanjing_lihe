package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ContactYellowPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactYellowPageDao extends JpaRepository<ContactYellowPageEntity, String>, JpaSpecificationExecutor<ContactYellowPageEntity> {
    @Modifying
    @Transactional
    @Query("delete from ContactYellowPageEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);
}
