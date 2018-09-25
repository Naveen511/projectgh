/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
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
import java.util.List;

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
     * softDelete the "id" Sector
     * 
     * @param id the id of the entity
     */
    void softDelete(Long id);

    /**
     * Get the "zonalId" sector.
     *
     * @param zonalId the zonalId of the entity
     * @return the list of entity
     */
    List<SectorDTO> findZonalSectors(Long zonalId);
}
