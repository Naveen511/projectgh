/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MOtherBedRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.MotherBed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the MotherBed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotherBedRepository extends JpaRepository<MotherBed, Long>, JpaSpecificationExecutor<MotherBed> {
    List<MotherBed> findByNurseryId(Long nurseryId);
    List<MotherBed> findByNurseryIdAndStatus(Long nurseryId, Integer status);
}
