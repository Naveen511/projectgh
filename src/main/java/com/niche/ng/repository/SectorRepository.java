/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Sector;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sector entity.
 *
 * SectorRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>, JpaSpecificationExecutor<Sector> {
    // Query the sector using a field status to get count.
    long countByStatus(Integer status);
}
