/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingDetailsService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.CoverFillingDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CoverFillingDetails.
 */
public interface CoverFillingDetailsService {

    /**
     * Save a coverFillingDetails.
     *
     * @param coverFillingDetailsDTO the entity to save
     * @return the persisted entity
     */
    CoverFillingDetailsDTO save(CoverFillingDetailsDTO coverFillingDetailsDTO);

    /**
     * Get all the coverFillingDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CoverFillingDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coverFillingDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoverFillingDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" coverFillingDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
