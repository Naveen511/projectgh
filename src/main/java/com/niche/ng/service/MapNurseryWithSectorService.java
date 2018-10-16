/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/05
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSectorService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.MapNurseryWithSectorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * Service Interface for managing MapNurseryWithSector.
 */
public interface MapNurseryWithSectorService {

    /**
     * Save a mapNurseryWithSector.
     *
     * @param mapNurseryWithSectorDTO the entity to save
     * @return the persisted entity
     */
    MapNurseryWithSectorDTO save(MapNurseryWithSectorDTO mapNurseryWithSectorDTO);

    /**
     * Get all the mapNurseryWithSectors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MapNurseryWithSectorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mapNurseryWithSector.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MapNurseryWithSectorDTO> findOne(Long id);

    /**
     * Delete the "id" mapNurseryWithSector.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
