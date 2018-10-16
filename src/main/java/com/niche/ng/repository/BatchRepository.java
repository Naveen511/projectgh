/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
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
 * Spring Data repository for the Batch entity.
 *
 * BatchRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long>, JpaSpecificationExecutor<Batch> {
    // Query the list of batch between 2 date using a field sowingDate.
    List<Batch> findBySowingDateBetween(LocalDate fromDate, LocalDate toDate);
}
