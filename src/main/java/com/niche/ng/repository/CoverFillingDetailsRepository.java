package com.niche.ng.repository;

import com.niche.ng.domain.CoverFillingDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the CoverFillingDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoverFillingDetailsRepository extends JpaRepository<CoverFillingDetails, Long>, JpaSpecificationExecutor<CoverFillingDetails> {
    List<CoverFillingDetails> findByCoverFillingId(Long coverFillingId);
}
