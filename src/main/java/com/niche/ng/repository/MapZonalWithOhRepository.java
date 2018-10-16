package com.niche.ng.repository;

import com.niche.ng.domain.MapZonalWithOh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the MapZonalWithOh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapZonalWithOhRepository extends JpaRepository<MapZonalWithOh, Long>, JpaSpecificationExecutor<MapZonalWithOh> {
    
    public List<MapZonalWithOh> findByZonalIdAndStatus(Long zonalId, Integer status);

    public List<MapZonalWithOh> findByZonalId(Long zonalId);
}
