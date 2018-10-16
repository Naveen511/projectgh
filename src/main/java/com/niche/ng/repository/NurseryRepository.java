/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Nursery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Nursery entity.
 *
 * NurseryRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryRepository extends JpaRepository<Nursery, Long>, JpaSpecificationExecutor<Nursery> {
    // Query the nursery using a field status to get count.
    long countByStatus(Integer status);
}
