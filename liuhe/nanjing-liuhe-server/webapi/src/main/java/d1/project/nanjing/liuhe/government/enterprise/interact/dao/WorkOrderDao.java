package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.WorkOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkOrderDao extends JpaRepository<WorkOrderEntity, String>, JpaSpecificationExecutor<WorkOrderEntity> {
}
