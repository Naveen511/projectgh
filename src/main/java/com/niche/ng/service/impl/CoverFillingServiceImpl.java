/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.CoverFillingService;
import com.niche.ng.domain.CoverFilling;
import com.niche.ng.repository.CoverFillingRepository;
import com.niche.ng.service.dto.CoverFillingDTO;
import com.niche.ng.service.mapper.CoverFillingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CoverFilling.
 *
 * Implementing CoverFillingService with IMPL suffix class as CoverFillingServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class CoverFillingServiceImpl implements CoverFillingService {

    private final Logger log = LoggerFactory.getLogger(CoverFillingServiceImpl.class);

    private final CoverFillingRepository coverFillingRepository;

    private final CoverFillingMapper coverFillingMapper;

    public CoverFillingServiceImpl(CoverFillingRepository coverFillingRepository, CoverFillingMapper coverFillingMapper) {
        this.coverFillingRepository = coverFillingRepository;
        this.coverFillingMapper = coverFillingMapper;
    }

    /**
     * Save a coverFilling.
     *
     * @param coverFillingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CoverFillingDTO save(CoverFillingDTO coverFillingDTO) {
        log.debug("Request to save CoverFilling : {}", coverFillingDTO);
        CoverFilling coverFilling = coverFillingMapper.toEntity(coverFillingDTO);
        coverFilling = coverFillingRepository.save(coverFilling);
        return coverFillingMapper.toDto(coverFilling);
    }

    /**
     * Get all the coverFillings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CoverFillingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoverFillings");
        return coverFillingRepository.findAll(pageable)
            .map(coverFillingMapper::toDto);
    }


    /**
     * Get one coverFilling by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoverFillingDTO> findOne(Long id) {
        log.debug("Request to get CoverFilling : {}", id);
        return coverFillingRepository.findById(id)
            .map(coverFillingMapper::toDto);
    }

    /**
     * Delete the coverFilling by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoverFilling : {}", id);
        coverFillingRepository.deleteById(id);
    }
}
