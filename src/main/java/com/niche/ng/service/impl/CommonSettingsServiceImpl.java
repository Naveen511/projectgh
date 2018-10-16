/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.CommonSettingsService;
import com.niche.ng.domain.CommonSettings;
import com.niche.ng.repository.CommonSettingsRepository;
import com.niche.ng.service.dto.CommonSettingsDTO;
import com.niche.ng.service.mapper.CommonSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CommonSettings.
 *
 * Implementing CommonSettingsService with IMPL suffix class
 * as CommonSettingsServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class CommonSettingsServiceImpl implements CommonSettingsService {

    private final Logger log = LoggerFactory.getLogger(CommonSettingsServiceImpl.class);

    private final CommonSettingsRepository commonSettingsRepository;

    private final CommonSettingsMapper commonSettingsMapper;

    public CommonSettingsServiceImpl(CommonSettingsRepository commonSettingsRepository, CommonSettingsMapper commonSettingsMapper) {
        this.commonSettingsRepository = commonSettingsRepository;
        this.commonSettingsMapper = commonSettingsMapper;
    }

    /**
     * Save a commonSettings.
     *
     * @param commonSettingsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommonSettingsDTO save(CommonSettingsDTO commonSettingsDTO) {
        log.debug("Request to save CommonSettings : {}", commonSettingsDTO);
        CommonSettings commonSettings = commonSettingsMapper.toEntity(commonSettingsDTO);
        commonSettings = commonSettingsRepository.save(commonSettings);
        return commonSettingsMapper.toDto(commonSettings);
    }

    /**
     * Get all the commonSettings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommonSettingsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonSettings");
        return commonSettingsRepository.findAll(pageable)
            .map(commonSettingsMapper::toDto);
    }


    /**
     * Get one commonSettings by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommonSettingsDTO> findOne(Long id) {
        log.debug("Request to get CommonSettings : {}", id);
        return commonSettingsRepository.findById(id)
            .map(commonSettingsMapper::toDto);
    }

    /**
     * Delete the commonSettings by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommonSettings : {}", id);
        commonSettingsRepository.deleteById(id);
    }
}
