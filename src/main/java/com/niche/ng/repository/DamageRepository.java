/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file intract with database to perform CRUD operation
 *                        and manual query
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Damage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data  repository for the Damage entity.
 *
 * DamageRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface DamageRepository extends JpaRepository<Damage, Long>, JpaSpecificationExecutor<Damage> {
    // Query damage entity using a field batchId to get sum of quantity.
    @Query("SELECT SUM(d.noOfQuantity) FROM Damage d WHERE d.batch.id=:batchId")
    public String getCountByBatchId(@Param("batchId") Long batchId);
}
