/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.NurseryInventoryDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Spring Data  repository for the NurseryInventoryDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryInventoryDetailsRepository extends JpaRepository<NurseryInventoryDetails, Long>, JpaSpecificationExecutor<NurseryInventoryDetails> {
    List<NurseryInventoryDetails> findByNurseryInventoryId(Long nurseryInventoryId);
    List<NurseryInventoryDetails> findByNurseryInventoryIdAndStatus(Long nurseryInventoryId, Integer status);
}
