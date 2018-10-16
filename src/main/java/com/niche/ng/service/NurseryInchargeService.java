/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInchargeService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.NurseryInchargeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NurseryIncharge.
 */
public interface NurseryInchargeService {

    /**
     * Save a nurseryIncharge.
     *
     * @param nurseryInchargeDTO the entity to save
     * @return the persisted entity
     */
    NurseryInchargeDTO save(NurseryInchargeDTO nurseryInchargeDTO);

    /**
     * Get all the nurseryIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NurseryInchargeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nurseryIncharge.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NurseryInchargeDTO> findOne(Long id);

    /**
     * Delete the "id" nurseryIncharge.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
