/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.FinancialYearSettingsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * Service Interface for managing FinancialYearSettings.
 */
public interface FinancialYearSettingsService {

    /**
     * Save a financialYearSettings.
     *
     * @param financialYearSettingsDTO the entity to save
     * @return the persisted entity
     */
    FinancialYearSettingsDTO save(FinancialYearSettingsDTO financialYearSettingsDTO);

    /**
     * Get all the financialYearSettings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FinancialYearSettingsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" financialYearSettings.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FinancialYearSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" financialYearSettings.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
