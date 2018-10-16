/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsServiceImplementaion
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.NurseryStockDetailsService;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.repository.NurseryStockDetailsRepository;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;
import com.niche.ng.service.mapper.NurseryStockDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing NurseryStockDetails.
 *
 * Implementing NurseryStockDetailsService with IMPL suffix class
 * as NurseryStockDetailsServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class NurseryStockDetailsServiceImpl implements NurseryStockDetailsService {

    private final Logger log = LoggerFactory.getLogger(NurseryStockDetailsServiceImpl.class);

    private final NurseryStockDetailsRepository nurseryStockDetailsRepository;

    private final NurseryStockDetailsMapper nurseryStockDetailsMapper;

    public NurseryStockDetailsServiceImpl(NurseryStockDetailsRepository nurseryStockDetailsRepository, NurseryStockDetailsMapper nurseryStockDetailsMapper) {
        this.nurseryStockDetailsRepository = nurseryStockDetailsRepository;
        this.nurseryStockDetailsMapper = nurseryStockDetailsMapper;
    }

    /**
     * Save a nurseryStockDetails.
     *
     * @param nurseryStockDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NurseryStockDetailsDTO save(NurseryStockDetailsDTO nurseryStockDetailsDTO) {
        log.debug("Request to save NurseryStockDetails : {}", nurseryStockDetailsDTO);
        NurseryStockDetails nurseryStockDetails = nurseryStockDetailsMapper.toEntity(nurseryStockDetailsDTO);
        nurseryStockDetails = nurseryStockDetailsRepository.save(nurseryStockDetails);
        return nurseryStockDetailsMapper.toDto(nurseryStockDetails);
    }

    /**
     * Get all the nurseryStockDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NurseryStockDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NurseryStockDetails");
        return nurseryStockDetailsRepository.findAll(pageable)
            .map(nurseryStockDetailsMapper::toDto);
    }


    /**
     * Get one nurseryStockDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NurseryStockDetailsDTO> findOne(Long id) {
        log.debug("Request to get NurseryStockDetails : {}", id);
        return nurseryStockDetailsRepository.findById(id)
            .map(nurseryStockDetailsMapper::toDto);
    }

    /**
     * Delete the nurseryStockDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NurseryStockDetails : {}", id);
        nurseryStockDetailsRepository.deleteById(id);
    }
}
