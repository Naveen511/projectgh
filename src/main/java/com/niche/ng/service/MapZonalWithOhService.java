/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/05
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOhService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.MapZonalWithOhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * Service Interface for managing MapZonalWithOh.
 */
public interface MapZonalWithOhService {

    /**
     * Save a mapZonalWithOh.
     *
     * @param mapZonalWithOhDTO the entity to save
     * @return the persisted entity
     */
    MapZonalWithOhDTO save(MapZonalWithOhDTO mapZonalWithOhDTO);

    /**
     * Get all the mapZonalWithOhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MapZonalWithOhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mapZonalWithOh.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MapZonalWithOhDTO> findOne(Long id);

    /**
     * Delete the "id" mapZonalWithOh.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
