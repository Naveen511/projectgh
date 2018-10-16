/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.SectorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Sector.
 */
public interface SectorService {

    /**
     * Save a sector.
     *
     * @param sectorDTO the entity to save
     * @return the persisted entity
     */
    SectorDTO save(SectorDTO sectorDTO);

    /**
     * Get all the sectors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SectorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sector.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SectorDTO> findOne(Long id);

    /**
     * Delete the "id" sector.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get count by "status" sectors.
     *
     * @param status the status of the entity
     * @return the count of sector
     */
    Long findActiveCount(Integer status);
}
