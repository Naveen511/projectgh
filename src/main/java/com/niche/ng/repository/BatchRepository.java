/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Batch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

/**
 * Spring Data  repository for the Batch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long>, JpaSpecificationExecutor<Batch> {
    
    List<Batch> findBySowingDateBetween(LocalDate fromDate, LocalDate toDate);
}
