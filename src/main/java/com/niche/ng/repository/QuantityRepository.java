package com.niche.ng.repository;

import java.util.List;

import com.niche.ng.domain.Quantity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Quantity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long>, JpaSpecificationExecutor<Quantity> {
    List<Quantity> findByPickListVarietyIdAndPickListCategoryId(Long pickListQuantityId, Long quantityTypeId);
}
