/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PointOfSaleDetailsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.PointOfSaleDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PointOfSaleDetails.
 */
public interface PointOfSaleDetailsService {

    /**
     * Save a pointOfSaleDetails.
     *
     * @param pointOfSaleDetailsDTO the entity to save
     * @return the persisted entity
     */
    PointOfSaleDetailsDTO save(PointOfSaleDetailsDTO pointOfSaleDetailsDTO);

    /**
     * Get all the pointOfSaleDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PointOfSaleDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pointOfSaleDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PointOfSaleDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" pointOfSaleDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
