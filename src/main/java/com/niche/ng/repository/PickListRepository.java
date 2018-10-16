/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.PickList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PickList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PickListRepository extends JpaRepository<PickList, Long>, JpaSpecificationExecutor<PickList> {

}
