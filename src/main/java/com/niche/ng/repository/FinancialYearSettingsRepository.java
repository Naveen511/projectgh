/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file intract with database to perform CRUD operation
 *                        and manual query
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.FinancialYearSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FinancialYearSettings entity.
 *
 * FinancialYearSettingsRepository Extends JpaRepository to handle 
 * the CRUD operation and querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface FinancialYearSettingsRepository extends JpaRepository<FinancialYearSettings, Long>, JpaSpecificationExecutor<FinancialYearSettings> {

}
