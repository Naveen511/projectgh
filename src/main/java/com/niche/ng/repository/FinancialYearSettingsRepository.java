/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettings
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.FinancialYearSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the FinancialYearSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinancialYearSettingsRepository extends JpaRepository<FinancialYearSettings, Long>, JpaSpecificationExecutor<FinancialYearSettings> {
    
    List<FinancialYearSettings> findByStatus(Integer status);
}
