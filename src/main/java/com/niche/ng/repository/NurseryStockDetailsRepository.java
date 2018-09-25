/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.NurseryStockDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the NurseryStockDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryStockDetailsRepository extends JpaRepository<NurseryStockDetails, Long>, JpaSpecificationExecutor<NurseryStockDetails> {
    List<NurseryStockDetails> findByNurseryStockId(Long nurseryStockId);
    List<NurseryStockDetails> findByItNurseryId(Long itNurseryId);
    List<NurseryStockDetails> findByStatus(Integer status);
}
