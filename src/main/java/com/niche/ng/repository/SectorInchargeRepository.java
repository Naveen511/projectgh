package com.niche.ng.repository;

import com.niche.ng.domain.SectorIncharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SectorIncharge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectorInchargeRepository extends JpaRepository<SectorIncharge, Long>, JpaSpecificationExecutor<SectorIncharge> {

}
