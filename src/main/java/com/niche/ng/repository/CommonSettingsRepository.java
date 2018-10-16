/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.CommonSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CommonSettings entity.
 *
 * CommonSettingsRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonSettingsRepository extends JpaRepository<CommonSettings, Long>, JpaSpecificationExecutor<CommonSettings> {

}
