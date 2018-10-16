/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/05
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHeadRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.OperationalHead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OperationalHead entity.
 *
 * OperationalHeadRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationalHeadRepository extends JpaRepository<OperationalHead, Long>, JpaSpecificationExecutor<OperationalHead> {
    // Query the operationalhead using a field status to get count.
    long countByStatus(Integer status);
}
