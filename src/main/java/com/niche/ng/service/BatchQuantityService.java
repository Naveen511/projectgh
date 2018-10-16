/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.BatchQuantityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BatchQuantity.
 */
public interface BatchQuantityService {

    /**
     * Save a batchQuantity.
     *
     * @param batchQuantityDTO the entity to save
     * @return the persisted entity
     */
    BatchQuantityDTO save(BatchQuantityDTO batchQuantityDTO);

    /**
     * Get all the batchQuantities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BatchQuantityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" batchQuantity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BatchQuantityDTO> findOne(Long id);

    /**
     * Delete the "id" batchQuantity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
