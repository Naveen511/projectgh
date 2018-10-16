/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.BatchQuantityService;
import com.niche.ng.domain.BatchQuantity;
import com.niche.ng.repository.BatchQuantityRepository;
import com.niche.ng.service.dto.BatchQuantityDTO;
import com.niche.ng.service.mapper.BatchQuantityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing BatchQuantity.
 *
 * Implementing BatchQuantityService with IMPL suffix class as BatchQuantityImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class BatchQuantityServiceImpl implements BatchQuantityService {

    private final Logger log = LoggerFactory.getLogger(BatchQuantityServiceImpl.class);

    private final BatchQuantityRepository batchQuantityRepository;

    private final BatchQuantityMapper batchQuantityMapper;

    public BatchQuantityServiceImpl(BatchQuantityRepository batchQuantityRepository, BatchQuantityMapper batchQuantityMapper) {
        this.batchQuantityRepository = batchQuantityRepository;
        this.batchQuantityMapper = batchQuantityMapper;
    }

    /**
     * Save a batchQuantity.
     *
     * @param batchQuantityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BatchQuantityDTO save(BatchQuantityDTO batchQuantityDTO) {
        log.debug("Request to save BatchQuantity : {}", batchQuantityDTO);
        BatchQuantity batchQuantity = batchQuantityMapper.toEntity(batchQuantityDTO);
        batchQuantity = batchQuantityRepository.save(batchQuantity);
        return batchQuantityMapper.toDto(batchQuantity);
    }

    /**
     * Get all the batchQuantities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BatchQuantityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BatchQuantities");
        return batchQuantityRepository.findAll(pageable)
            .map(batchQuantityMapper::toDto);
    }


    /**
     * Get one batchQuantity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BatchQuantityDTO> findOne(Long id) {
        log.debug("Request to get BatchQuantity : {}", id);
        return batchQuantityRepository.findById(id)
            .map(batchQuantityMapper::toDto);
    }

    /**
     * Delete the batchQuantity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BatchQuantity : {}", id);
        batchQuantityRepository.deleteById(id);
    }
}
