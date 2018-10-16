/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.NurseryInventoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NurseryInventory.
 */
public interface NurseryInventoryService {

    /**
     * Save a nurseryInventory.
     *
     * @param nurseryInventoryDTO the entity to save
     * @return the persisted entity
     */
    NurseryInventoryDTO save(NurseryInventoryDTO nurseryInventoryDTO);

    /**
     * Get all the nurseryInventories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NurseryInventoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nurseryInventory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NurseryInventoryDTO> findOne(Long id);

    /**
     * Delete the "id" nurseryInventory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
