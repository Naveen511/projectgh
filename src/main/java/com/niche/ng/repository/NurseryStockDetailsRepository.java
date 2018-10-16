/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.NurseryStockDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NurseryStockDetails entity.
 *
 * NurseryStockDetailsRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryStockDetailsRepository extends JpaRepository<NurseryStockDetails, Long>, JpaSpecificationExecutor<NurseryStockDetails> {

}
