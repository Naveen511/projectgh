/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/05
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.GodownStockDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GodownStock.
 */
public interface GodownStockService {

    /**
     * Save a godownStock.
     *
     * @param godownStockDTO the entity to save
     * @return the persisted entity
     */
    GodownStockDTO save(GodownStockDTO godownStockDTO);

    /**
     * Get all the godownStocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GodownStockDTO> findAll(Pageable pageable);


    /**
     * Get the "id" godownStock.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GodownStockDTO> findOne(Long id);

    /**
     * Delete the "id" godownStock.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
