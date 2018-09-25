/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.ShadeArea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Spring Data  repository for the ShadeArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShadeAreaRepository extends JpaRepository<ShadeArea, Long>, JpaSpecificationExecutor<ShadeArea> {
    List<ShadeArea> findByBatchId(Long batchId);

    @Query("SELECT SUM(s.noOfSeedlings) FROM ShadeArea s WHERE s.batch.id=:batchId")
    public String getCountByBatchId(@Param("batchId") Long batchId);
}
