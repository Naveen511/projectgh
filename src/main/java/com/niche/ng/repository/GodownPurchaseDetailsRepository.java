/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.GodownPurchaseDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GodownPurchaseDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GodownPurchaseDetailsRepository extends JpaRepository<GodownPurchaseDetails, Long>, JpaSpecificationExecutor<GodownPurchaseDetails> {

}
