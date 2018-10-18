/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file intract with database to perform CRUD operation
 *                        and manual query
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.MapSectorWithZonal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MapSectorWithZonal entity.
 *
 * MapSectorWithZonalRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface MapSectorWithZonalRepository extends JpaRepository<MapSectorWithZonal, Long>, JpaSpecificationExecutor<MapSectorWithZonal> {

}
