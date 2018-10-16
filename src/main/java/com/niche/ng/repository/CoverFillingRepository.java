package com.niche.ng.repository;

import com.niche.ng.domain.CoverFilling;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CoverFilling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoverFillingRepository extends JpaRepository<CoverFilling, Long>, JpaSpecificationExecutor<CoverFilling> {

}
