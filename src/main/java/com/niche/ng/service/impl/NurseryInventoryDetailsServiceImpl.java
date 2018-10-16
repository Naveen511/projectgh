/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/25
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

import java.util.Optional;
/**
 * Service Implementation for managing NurseryInventoryDetails.
 *
 * Implementing NurseryInventoryDetailsService with IMPL suffix class
 * as NurseryInventoryDetailsServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
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
}
