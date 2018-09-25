/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.ZonalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

/**
 * Service Interface for managing Zonal.
 */
public interface ZonalService {

    /**
     * Save a zonal.
     *
     * @param zonalDTO the entity to save
     * @return the persisted entity
     */
    ZonalDTO save(ZonalDTO zonalDTO);

    /**
     * Get all the zonals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ZonalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" zonal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ZonalDTO> findOne(Long id);

    /**
     * Delete the "id" zonal.
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

    /**
     * Get the "operationalHeadId" Zoanl.
     *
     * @param operationalHeadId the operationalHeadId of the entity
     * @param status of the entity
     * @return the list of entity
     */
    List<ZonalDTO> findParticularHeadOfficeZonal(Long operationalHeadId, Integer status);
}
