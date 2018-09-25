/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.service.dto.DamageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing Damage.
 */
public interface DamageService {

    /**
     * Save a damage.
     *
     * @param damageDTO the entity to save
     * @return the persisted entity
     */
    DamageDTO save(DamageDTO damageDTO);

    /**
     * Get all the damages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DamageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" damage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DamageDTO> findOne(Long id);

    /**
     * Delete the "id" damage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "batchId" damage.
     *
     * @param batchId the batchId of the entity
     * @return the list of entity
     */
    List<DamageDTO> findParticularBatch(Long batchId);

    /**
     * Get the "status" damage.
     *
     * @param status the batchId of the entity
     * @return the list of entity
     */
    List<DamageDTO> findParticularStatus(Integer status);

    /**
     * Get the count of damage based on "batchId".
     *
     * @param batchId the batchId of the entity
     * @return string value as damage count
     */
    String getDamageCount(Long batchId);
}
