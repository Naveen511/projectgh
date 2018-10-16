/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.BatchQuantity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data repository for the BatchQuantity entity.
 *
 * BatchQuantityRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchQuantityRepository extends JpaRepository<BatchQuantity, Long>, JpaSpecificationExecutor<BatchQuantity> {

}
