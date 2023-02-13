package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.view.ActivityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityViewDao extends JpaRepository<ActivityView, String>, JpaSpecificationExecutor<ActivityView> {
}
