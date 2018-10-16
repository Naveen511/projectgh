/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Damage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Spring Data  repository for the Damage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DamageRepository extends JpaRepository<Damage, Long>, JpaSpecificationExecutor<Damage> {
    List<Damage> findByBatchId(Long batchId);

    List<Damage> findByStatus(Integer status);

    @Query("SELECT SUM(d.noOfQuantity) FROM Damage d WHERE d.batch.id=:batchId")
    public String getCountByBatchId(@Param("batchId") Long batchId);
}
