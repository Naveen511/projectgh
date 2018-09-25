/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.NurseryStockDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

/**
 * Service Interface for managing NurseryStockDetails.
 */
public interface NurseryStockDetailsService {

    /**
     * Save a nurseryStockDetails.
     *
     * @param nurseryStockDetailsDTO the entity to save
     * @return the persisted entity
     */
    NurseryStockDetailsDTO save(NurseryStockDetailsDTO nurseryStockDetailsDTO);

    /**
     * Get all the nurseryStockDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NurseryStockDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nurseryStockDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NurseryStockDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" nurseryStockDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "nurseryStockId" NurseryStockDetails.
     *
     * @param nurseryStockId the nurseryStockId of the entity
     * @return the list of entity
     */
    List<NurseryStockDetailsDTO> findParticularStockDetails(Long nurseryStockId);

    /**
     * Get the "status" NurseryStockDetails.
     *
     * @param status the status of the entity
     * @return the list of entity
     */
    List<NurseryStockDetailsDTO> findParticularStatusRecord(Integer status);

    /**
     * Get the "itNurseryId" NurseryStockDetails.
     *
     * @param itNurseryId the nurseryStockId of the entity
     * @return the list of entity
     */
    List<NurseryStockDetailsDTO> findParticularNurseryStockDetails(Long itNurseryId);
}
