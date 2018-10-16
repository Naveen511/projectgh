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

import java.util.List;
/**
 * Spring Data  repository for the PickListValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PickListValueRepository extends JpaRepository<PickListValue, Long>, JpaSpecificationExecutor<PickListValue> {
    List<PickListValue> findByPickListId(Long pickListId);
    List<PickListValue> findByPickValueId(Long pickValueId);
}
