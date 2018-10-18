/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file intract with database to perform CRUD operation
 *                        and manual query
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Quantity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Quantity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long>, JpaSpecificationExecutor<Quantity> {

}
