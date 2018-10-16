/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.NurseryInventoryDetailsService;
import com.niche.ng.domain.NurseryInventoryDetails;
import com.niche.ng.repository.NurseryInventoryDetailsRepository;
import com.niche.ng.service.dto.NurseryInventoryDetailsDTO;
import com.niche.ng.service.mapper.NurseryInventoryDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing NurseryInventoryDetails.
 */
@Service
@Transactional
public class NurseryInventoryDetailsServiceImpl implements NurseryInventoryDetailsService {

    private final Logger log = LoggerFactory.getLogger(NurseryInventoryDetailsServiceImpl.class);

    private final NurseryInventoryDetailsRepository nurseryInventoryDetailsRepository;

    private final NurseryInventoryDetailsMapper nurseryInventoryDetailsMapper;

    public NurseryInventoryDetailsServiceImpl(NurseryInventoryDetailsRepository nurseryInventoryDetailsRepository, NurseryInventoryDetailsMapper nurseryInventoryDetailsMapper) {
        this.nurseryInventoryDetailsRepository = nurseryInventoryDetailsRepository;
        this.nurseryInventoryDetailsMapper = nurseryInventoryDetailsMapper;
    }

    /**
     * Save a nurseryInventoryDetails.
     *
     * @param nurseryInventoryDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NurseryInventoryDetailsDTO save(NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO) {
        log.debug("Request to save NurseryInventoryDetails : {}", nurseryInventoryDetailsDTO);
        NurseryInventoryDetails nurseryInventoryDetails = nurseryInventoryDetailsMapper.toEntity(nurseryInventoryDetailsDTO);
        nurseryInventoryDetails = nurseryInventoryDetailsRepository.save(nurseryInventoryDetails);
        return nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetails);
    }

    /**
     * Get all the nurseryInventoryDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NurseryInventoryDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NurseryInventoryDetails");
        return nurseryInventoryDetailsRepository.findAll(pageable)
            .map(nurseryInventoryDetailsMapper::toDto);
    }


    /**
     * Get one nurseryInventoryDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NurseryInventoryDetailsDTO> findOne(Long id) {
        log.debug("Request to get NurseryInventoryDetails : {}", id);
        return nurseryInventoryDetailsRepository.findById(id)
            .map(nurseryInventoryDetailsMapper::toDto);
    }

    /**
     * Delete the nurseryInventoryDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NurseryInventoryDetails : {}", id);
        nurseryInventoryDetailsRepository.deleteById(id);
    }

    /**
     * Get all the nurseryInventoryDetails by nurseryInventoryId.
     *
     * @param nurseryInventoryId the nurseryInventoryId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryInventoryDetailsDTO> findParticularInventoryDetails(Long nurseryInventoryId) {
        log.debug("Request to get nurseryInventoryDetails : {}", nurseryInventoryId);
        List<NurseryInventoryDetails> nurseryInventoryDetails = nurseryInventoryDetailsRepository.findByNurseryInventoryId(nurseryInventoryId);
        return nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetails);
    }

    /**
     * Get all the nurseryInventoryDetails by nurseryInventoryId.
     *
     * @param nurseryInventoryId the nurseryInventoryId of the entity
     * @param status the nurseryInventoryId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryInventoryDetailsDTO> findParticularInventoryDamageDetails(Long nurseryInventoryId, Integer status) {
        log.debug("Request to get nurseryInventoryDetails : {}", nurseryInventoryId, status);
        List<NurseryInventoryDetails> nurseryInventoryDetails = nurseryInventoryDetailsRepository.findByNurseryInventoryIdAndStatus(nurseryInventoryId, status);
        return nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetails);
    }
}
