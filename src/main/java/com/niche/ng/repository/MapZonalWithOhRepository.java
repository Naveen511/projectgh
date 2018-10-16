/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOhRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.MapZonalWithOh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MapZonalWithOh entity.
 *
 * MapZonalWithOhRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface MapZonalWithOhRepository extends JpaRepository<MapZonalWithOh, Long>, JpaSpecificationExecutor<MapZonalWithOh> {

}
