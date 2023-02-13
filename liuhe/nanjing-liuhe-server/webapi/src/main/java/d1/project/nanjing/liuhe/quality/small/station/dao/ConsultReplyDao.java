package d1.project.nanjing.liuhe.quality.small.station.dao;

import d1.project.nanjing.liuhe.quality.small.station.entity.ConsultReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConsultReplyDao extends JpaRepository<ConsultReplyEntity, String>, JpaSpecificationExecutor<ConsultReplyEntity> {

    List<ConsultReplyEntity> findByConsultIdOrderByCreateTimeAsc(String consultId);
}
