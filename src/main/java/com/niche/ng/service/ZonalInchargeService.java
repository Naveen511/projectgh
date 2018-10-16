/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalInchargeService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.ZonalInchargeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ZonalIncharge.
 */
public interface ZonalInchargeService {

    /**
     * Save a zonalIncharge.
     *
     * @param zonalInchargeDTO the entity to save
     * @return the persisted entity
     */
    ZonalInchargeDTO save(ZonalInchargeDTO zonalInchargeDTO);

    /**
     * Get all the zonalIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ZonalInchargeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" zonalIncharge.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ZonalInchargeDTO> findOne(Long id);

    /**
     * Delete the "id" zonalIncharge.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
