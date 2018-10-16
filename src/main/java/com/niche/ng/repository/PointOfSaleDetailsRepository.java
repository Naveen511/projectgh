package com.niche.ng.repository;

import com.niche.ng.domain.PointOfSaleDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PointOfSaleDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PointOfSaleDetailsRepository extends JpaRepository<PointOfSaleDetails, Long>, JpaSpecificationExecutor<PointOfSaleDetails> {

}
