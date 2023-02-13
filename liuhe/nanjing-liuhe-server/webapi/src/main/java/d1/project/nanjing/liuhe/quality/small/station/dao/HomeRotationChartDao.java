package d1.project.nanjing.liuhe.quality.small.station.dao;

import d1.project.nanjing.liuhe.quality.small.station.entity.HomeRotationChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HomeRotationChartDao extends JpaRepository<HomeRotationChartEntity, String>, JpaSpecificationExecutor<HomeRotationChartEntity> {
    @Modifying
    @Transactional
    @Query("delete from HomeRotationChartEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);
}
