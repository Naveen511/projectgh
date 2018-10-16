/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.NurseryInventoryDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NurseryInventoryDetails.
 */
public interface NurseryInventoryDetailsService {

    /**
     * Save a nurseryInventoryDetails.
     *
     * @param nurseryInventoryDetailsDTO the entity to save
     * @return the persisted entity
     */
    NurseryInventoryDetailsDTO save(NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO);

    /**
     * Get all the nurseryInventoryDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NurseryInventoryDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nurseryInventoryDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NurseryInventoryDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" nurseryInventoryDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
