package d1.project.nanjing.liuhe.quality.small.station.dao;

import d1.project.nanjing.liuhe.quality.small.station.entity.ServiceProviderInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceProviderInfoDao extends JpaRepository<ServiceProviderInfoEntity, String>, JpaSpecificationExecutor<ServiceProviderInfoEntity> {
    @Modifying
    @Transactional
    @Query("delete from ServiceProviderInfoEntity s where s.id in (?1)")
    void deleteBatch(List<String> ids);
}
