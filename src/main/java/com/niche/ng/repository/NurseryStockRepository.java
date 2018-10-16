/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.NurseryStock;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data  repository for the NurseryStock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryStockRepository extends JpaRepository<NurseryStock, Long>, JpaSpecificationExecutor<NurseryStock> {
    
    /**
     * Finds a stock by using the variety, category and nursery as a search criteria.
     * @param nurseryId as nursery entity id
     * param pickListVarietyId as pickListValue entity id for variety
     * @param pickListCategoryId as pickListValue entity id for category
     * @return  A list of nurseryStock which is exact match with the given params.
     *          If no record is found, this method returns an empty list.
     */
    // @Query("SELECT s FROM NurseryStock s WHERE (s.nursery_id=:nurseryId) AND ((s.pick_list_category_id=:pickListCategoryId) AND (s.pick_list_variety_id=:pickListVarietyId))")
    // , @Param("pickListCategoryId") Long pickListCategoryId
    // public List<NurseryStock> findByNurseryIdAndPickListVarietyIdAndPickListCategoryIdIn(@Param("nurseryId") Long nurseryId, @Param("pickListVarietyId") Long pickListVarietyId, @Param("pickListCategoryId") Long pickListCategoryId);
    // public List<NurseryStock> findByNurseryIdAndPickListVarietyIdInAndPickListCategoryIdIn(@Param("nurseryId") Long nurseryId, @Param("pickListVarietyId") Long pickListVarietyId, @Param("pickListCategoryId") Long pickListCategoryId);
    
    // public List<NurseryStock> findByNurseryIdAndPickListVarietyIdIn(@Param("nurseryId") Long nurseryId, @Param("pickListVarietyId") Long pickListVarietyId);
    public List<NurseryStock> findByNurseryIdAndPickListCategoryId(@Param("nurseryId") Long nurseryId, @Param("pickListCategoryId") Long pickListCategoryId);
    List<NurseryStock> findByNurseryId(Long nurseryId);
}
