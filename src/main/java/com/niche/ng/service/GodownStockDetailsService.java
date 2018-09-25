/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.GodownStockDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing GodownStockDetails.
 */
public interface GodownStockDetailsService {

    /**
     * Save a godownStockDetails.
     *
     * @param godownStockDetailsDTO the entity to save
     * @return the persisted entity
     */
    GodownStockDetailsDTO save(GodownStockDetailsDTO godownStockDetailsDTO);

    /**
     * Get all the godownStockDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GodownStockDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" godownStockDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GodownStockDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" godownStockDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Display the list of values in godown stock details 
     * 
     * @param id the id of the entity
     */
    List<GodownStockDetailsDTO> findStockList(Long godownStockId);
}
