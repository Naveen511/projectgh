/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/05
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapSectorWithZonalService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.MapSectorWithZonalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * Service Interface for managing MapSectorWithZonal.
 */
public interface MapSectorWithZonalService {

    /**
     * Save a mapSectorWithZonal.
     *
     * @param mapSectorWithZonalDTO the entity to save
     * @return the persisted entity
     */
    MapSectorWithZonalDTO save(MapSectorWithZonalDTO mapSectorWithZonalDTO);

    /**
     * Get all the mapSectorWithZonals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MapSectorWithZonalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mapSectorWithZonal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MapSectorWithZonalDTO> findOne(Long id);

    /**
     * Delete the "id" mapSectorWithZonal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
