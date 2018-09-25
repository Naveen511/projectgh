package com.niche.ng.repository;

import com.niche.ng.domain.MapNurseryWithSector;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the MapNurseryWithSector entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapNurseryWithSectorRepository extends JpaRepository<MapNurseryWithSector, Long>, JpaSpecificationExecutor<MapNurseryWithSector> {
    public List<MapNurseryWithSector> findByNurseryIdAndStatus(Long nurseryId, Integer status);

    public List<MapNurseryWithSector> findByNurseryId(Long nurseryId);
}
