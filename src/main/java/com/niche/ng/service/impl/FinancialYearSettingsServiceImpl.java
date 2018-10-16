/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingsServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.FinancialYearSettingsService;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.FinancialYearSettingsRepository;
import com.niche.ng.service.dto.FinancialYearSettingsDTO;
import com.niche.ng.service.mapper.FinancialYearSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing FinancialYearSettings.
 */
@Service
@Transactional
public class FinancialYearSettingsServiceImpl implements FinancialYearSettingsService {

    private final Logger log = LoggerFactory.getLogger(FinancialYearSettingsServiceImpl.class);

    private final FinancialYearSettingsRepository financialYearSettingsRepository;

    private final FinancialYearSettingsMapper financialYearSettingsMapper;

    public FinancialYearSettingsServiceImpl(FinancialYearSettingsRepository financialYearSettingsRepository, FinancialYearSettingsMapper financialYearSettingsMapper) {
        this.financialYearSettingsRepository = financialYearSettingsRepository;
        this.financialYearSettingsMapper = financialYearSettingsMapper;
    }

    /**
     * Save a financialYearSettings.
     *
     * @param financialYearSettingsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinancialYearSettingsDTO save(FinancialYearSettingsDTO financialYearSettingsDTO) {
        log.debug("Request to save FinancialYearSettings : {}", financialYearSettingsDTO);
        FinancialYearSettings financialYearSettings = financialYearSettingsMapper.toEntity(financialYearSettingsDTO);
        financialYearSettings = financialYearSettingsRepository.save(financialYearSettings);
        return financialYearSettingsMapper.toDto(financialYearSettings);
    }

    /**
     * Get all the financialYearSettings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FinancialYearSettingsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinancialYearSettings");
        return financialYearSettingsRepository.findAll(pageable)
            .map(financialYearSettingsMapper::toDto);
    }


    /**
     * Get one financialYearSettings by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinancialYearSettingsDTO> findOne(Long id) {
        log.debug("Request to get FinancialYearSettings : {}", id);
        return financialYearSettingsRepository.findById(id)
            .map(financialYearSettingsMapper::toDto);
    }

    /**
     * Delete the financialYearSettings by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinancialYearSettings : {}", id);
        financialYearSettingsRepository.deleteById(id);
    }

    /**
     * Get one financialYearSettings by status.
     *
     * @param status the status of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public List<FinancialYearSettingsDTO> findActiveRecord(Integer status) {
        log.debug("Request to get FinancialYearSettings : {}", status);
        List<FinancialYearSettings> list = financialYearSettingsRepository.findByStatus(status);
        return financialYearSettingsMapper.toDto(list);
    }
}
