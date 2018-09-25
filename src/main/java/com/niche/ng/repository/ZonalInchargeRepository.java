package com.niche.ng.repository;

import com.niche.ng.domain.ZonalIncharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ZonalIncharge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZonalInchargeRepository extends JpaRepository<ZonalIncharge, Long>, JpaSpecificationExecutor<ZonalIncharge> {

}
