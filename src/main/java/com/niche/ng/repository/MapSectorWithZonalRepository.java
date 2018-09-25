package com.niche.ng.repository;

import com.niche.ng.domain.MapSectorWithZonal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the MapSectorWithZonal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapSectorWithZonalRepository extends JpaRepository<MapSectorWithZonal, Long>, JpaSpecificationExecutor<MapSectorWithZonal> {
    public List<MapSectorWithZonal> findBySectorIdAndStatus(Long sectorId, Integer status);

    public List<MapSectorWithZonal> findBySectorId(Long sectorId);
}
