/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.SectorIncharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SectorIncharge entity.
 *
 * SectorInchargeRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface SectorInchargeRepository extends JpaRepository<SectorIncharge, Long>, JpaSpecificationExecutor<SectorIncharge> {

}
