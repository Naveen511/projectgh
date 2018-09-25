/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.GodownDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Godown.
 */
public interface GodownService {

    /**
     * Save a godown.
     *
     * @param godownDTO the entity to save
     * @return the persisted entity
     */
    GodownDTO save(GodownDTO godownDTO);

    /**
     * Get all the godowns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GodownDTO> findAll(Pageable pageable);


    /**
     * Get the "id" godown.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GodownDTO> findOne(Long id);

    /**
     * Delete the "id" godown.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * softDelete the "id" Zonal
     * 
     * @param id the id of the entity
     */
    void softDelete(Long id);
}
