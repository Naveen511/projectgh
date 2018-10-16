/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.PickListValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PickListValue entity.
 *
 * PickListValueRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface PickListValueRepository extends JpaRepository<PickListValue, Long>, JpaSpecificationExecutor<PickListValue> {

}
