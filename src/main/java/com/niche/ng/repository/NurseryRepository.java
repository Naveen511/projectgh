/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Nursery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the Nursery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryRepository extends JpaRepository<Nursery, Long>, JpaSpecificationExecutor<Nursery> {
    List<Nursery> findBySectorId(Long sectorId);
}
