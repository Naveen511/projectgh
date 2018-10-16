/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.NurseryInventory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data  repository for the NurseryInventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryInventoryRepository extends JpaRepository<NurseryInventory, Long>, JpaSpecificationExecutor<NurseryInventory> {
    public List<NurseryInventory> findByNurserysIdAndPickListCategoryId(@Param("nurserysId") Long nurserysId, @Param("pickListCategoryId") Long pickListCategoryId);
    public List<NurseryInventory> findByNurserysIdAndStatus(@Param("nurserysId") Long nurserysId, @Param("status") Integer status);
    public List<NurseryInventory> findByStatus(Integer status);
}
