/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GodownPurchaseDetails.
 */
public interface GodownPurchaseDetailsService {

    /**
     * Save a godownPurchaseDetails.
     *
     * @param godownPurchaseDetailsDTO the entity to save
     * @return the persisted entity
     */
    GodownPurchaseDetailsDTO save(GodownPurchaseDetailsDTO godownPurchaseDetailsDTO);

    /**
     * Get all the godownPurchaseDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GodownPurchaseDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" godownPurchaseDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GodownPurchaseDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" godownPurchaseDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

}
