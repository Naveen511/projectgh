/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.GodownStockDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the GodownStockDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GodownStockDetailsRepository extends JpaRepository<GodownStockDetails, Long>, JpaSpecificationExecutor<GodownStockDetails> {
    List<GodownStockDetails> findByGodownStockId(Long godownStockId);
}
