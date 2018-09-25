/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.BatchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

/**
 * Service Interface for managing Batch.
 */
public interface BatchService {

    /**
     * Save a batch.
     *
     * @param batchDTO the entity to save
     * @return the persisted entity
     */
    BatchDTO save(BatchDTO batchDTO);

    /**
     * Get all the batches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BatchDTO> findAll(Pageable pageable);


    /**
     * Get the "id" batch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BatchDTO> findOne(Long id);

    /**
     * Delete the "id" batch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * softDelete the "id" batch
     * 
     * @param id the id of the entity
     */
    void softDelete(Long id);

    List<BatchDTO> findDateBetween(LocalDate fromDate, LocalDate toDate);
}
