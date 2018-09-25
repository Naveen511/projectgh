package com.niche.ng.repository;

import com.niche.ng.domain.NurseryIncharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NurseryIncharge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryInchargeRepository extends JpaRepository<NurseryIncharge, Long>, JpaSpecificationExecutor<NurseryIncharge> {

}
