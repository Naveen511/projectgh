/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.BatchService;
import com.niche.ng.domain.Batch;
import com.niche.ng.repository.BatchRepository;
import com.niche.ng.service.dto.BatchDTO;
import com.niche.ng.service.mapper.BatchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

/**
 * Service Implementation for managing Batch.
 *
 * Implementing BatchService with IMPL suffix class as BatchImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class BatchServiceImpl implements BatchService {

    private final Logger log = LoggerFactory.getLogger(BatchServiceImpl.class);

    private final BatchRepository batchRepository;

    private final BatchMapper batchMapper;

    public BatchServiceImpl(BatchRepository batchRepository, BatchMapper batchMapper) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
    }

    /**
     * Save a batch.
     *
     * @param batchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BatchDTO save(BatchDTO batchDTO) {
        log.debug("Request to save Batch : {}", batchDTO);
        Batch batch = batchMapper.toEntity(batchDTO);
        batch = batchRepository.save(batch);
        return batchMapper.toDto(batch);
    }

    /**
     * Get all the batches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BatchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Batches");
        return batchRepository.findAll(pageable)
            .map(batchMapper::toDto);
    }

    /**
     * Get one batch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BatchDTO> findOne(Long id) {
        log.debug("Request to get Batch : {}", id);
        return batchRepository.findById(id)
            .map(batchMapper::toDto);
    }

    /**
     * Delete the batch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Batch : {}", id);
        batchRepository.deleteById(id);
    }

    /**
     * Get the list of batches between a date
     *
     * @param fromDate the sowingDate of the entity
     * @param toDate the sowingDate of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BatchDTO> findDateBetween(LocalDate fromDate, LocalDate toDate) {
        // log.debug("Request to get get Report : {}", fromDate + " " + toDate);
        List<Batch> list = batchRepository.findBySowingDateBetween(fromDate, toDate);
        return batchMapper.toDto(list);
    }
}
