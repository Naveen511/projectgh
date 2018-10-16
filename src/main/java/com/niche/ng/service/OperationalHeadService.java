/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHeadService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.OperationalHeadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * Service Interface for managing OperationalHead.
 */
public interface OperationalHeadService {

    /**
     * Save a operationalHead.
     *
     * @param operationalHeadDTO the entity to save
     * @return the persisted entity
     */
    OperationalHeadDTO save(OperationalHeadDTO operationalHeadDTO);

    /**
     * Get all the operationalHeads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OperationalHeadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" operationalHead.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OperationalHeadDTO> findOne(Long id);

    /**
     * Delete the "id" operationalHead.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get count by "status" operationalHead.
     *
     * @param status the status of the entity
     * @return the count of opertionalHead
     */
    Long findActiveCount(Integer status);
}
