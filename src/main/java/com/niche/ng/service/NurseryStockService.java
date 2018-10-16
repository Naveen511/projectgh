/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.NurseryStockDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NurseryStock.
 */
public interface NurseryStockService {

    /**
     * Save a nurseryStock.
     *
     * @param nurseryStockDTO the entity to save
     * @return the persisted entity
     */
    NurseryStockDTO save(NurseryStockDTO nurseryStockDTO);

    /**
     * Get all the nurseryStocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NurseryStockDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nurseryStock.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NurseryStockDTO> findOne(Long id);

    /**
     * Delete the "id" nurseryStock.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
