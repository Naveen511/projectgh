/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
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
import java.util.List;
/**
 * Service Implementation for managing NurseryStockDetails.
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

    /**
     * Get all the nurseryStockDetails by nurseryStockId.
     *
     * @param nurseryStockId the nurseryStockId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryStockDetailsDTO> findParticularStockDetails(Long nurseryStockId) {
        log.debug("Request to get nurseryStockDetails : {}", nurseryStockId);
        List<NurseryStockDetails> nurseryStockDetails = nurseryStockDetailsRepository.findByNurseryStockId(nurseryStockId);
        return nurseryStockDetailsMapper.toDto(nurseryStockDetails);
    }

    /**
     * Get all the nurseryStockDetails by status.
     *
     * @param status the status of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryStockDetailsDTO> findParticularStatusRecord(Integer status) {
        log.debug("Request to get nurseryStockDetails : {}", status);
        List<NurseryStockDetails> nurseryStockDetails = nurseryStockDetailsRepository.findByStatus(status);
        return nurseryStockDetailsMapper.toDto(nurseryStockDetails);
    }

    /**
     * Get all the nurseryStockDetails by itNurseryId.
     *
     * @param itNurseryId the nurseryStockId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryStockDetailsDTO> findParticularNurseryStockDetails(Long itNurseryId) {
        log.debug("Request to get nurseryStockDetails : {}", itNurseryId);
        List<NurseryStockDetails> itNurseryStockDetails = nurseryStockDetailsRepository.findByItNurseryId(itNurseryId);
        return nurseryStockDetailsMapper.toDto(itNurseryStockDetails);
    }
}
