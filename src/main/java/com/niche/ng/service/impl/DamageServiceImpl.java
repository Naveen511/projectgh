/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.DamageService;
import com.niche.ng.domain.Damage;
import com.niche.ng.repository.DamageRepository;
import com.niche.ng.service.dto.DamageDTO;
import com.niche.ng.service.mapper.DamageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Damage.
 *
 * Implementing DamageService with IMPL suffix class as DamageServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class DamageServiceImpl implements DamageService {

    private final Logger log = LoggerFactory.getLogger(DamageServiceImpl.class);

    private final DamageRepository damageRepository;

    private final DamageMapper damageMapper;

    public DamageServiceImpl(DamageRepository damageRepository, DamageMapper damageMapper) {
        this.damageRepository = damageRepository;
        this.damageMapper = damageMapper;
    }

    /**
     * Save a damage.
     *
     * @param damageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DamageDTO save(DamageDTO damageDTO) {
        log.debug("Request to save Damage : {}", damageDTO);
        Damage damage = damageMapper.toEntity(damageDTO);
        damage = damageRepository.save(damage);
        return damageMapper.toDto(damage);
    }

    /**
     * Get all the damages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DamageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Damages");
        return damageRepository.findAll(pageable)
            .map(damageMapper::toDto);
    }


    /**
     * Get one damage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DamageDTO> findOne(Long id) {
        log.debug("Request to get Damage : {}", id);
        return damageRepository.findById(id)
            .map(damageMapper::toDto);
    }

    /**
     * Delete the damage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Damage : {}", id);
        damageRepository.deleteById(id);
    }

    /**
     * Get the count of damage by batchId
     *
     * @param batchId the batchId of the entity
     * @return string value of damage count
     */
    public String getDamageCount(Long batchId) {
        // log.debug("Request to get patrticular batch damage : {}", batchId);
        // log.debug("Damage Count IMPL : ", damageRepository.getCountByBatchId(batchId));
        return damageRepository.getCountByBatchId(batchId);
        // log.debug("Damage Count IMPL : ", damageCount);
        // return damageMapper.toDto(damageCount);
    }
}
