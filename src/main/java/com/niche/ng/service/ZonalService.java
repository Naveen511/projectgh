/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
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
     * Filter : based on the from date and to date
     * @param fromDate From date of the zonal created date
     * @param toDate From date of the zonal created date
     */
    // List<ZonalDTO> findDateBetween(Instant fromDate, Instant toDate);

    /**
     * Get count of "status" operationalHead.
     *
     * @param status the status of the entity
     * @return count of the zonal
     */
    Long findActiveCount(Integer status);
}
