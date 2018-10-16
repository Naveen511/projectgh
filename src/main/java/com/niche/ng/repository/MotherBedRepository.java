/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MotherBedRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.MotherBed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MotherBed entity.
 *
 * MotherBedRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface MotherBedRepository extends JpaRepository<MotherBed, Long>, JpaSpecificationExecutor<MotherBed> {

}
