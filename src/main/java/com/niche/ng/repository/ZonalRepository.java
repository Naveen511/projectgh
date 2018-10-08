/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Zonal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the Zonal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZonalRepository extends JpaRepository<Zonal, Long>, JpaSpecificationExecutor<Zonal> {
    List<Zonal> findByOperationalHeadIdAndStatus(Long operationalHeadId, Integer status);
    long countByStatus(Integer status);
}
