package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.view.OnlineClassroomView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OnlineClassroomViewDao extends JpaRepository<OnlineClassroomView, String>, JpaSpecificationExecutor<OnlineClassroomView> {

}
