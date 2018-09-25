/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.GodownPurchaseDetailsService;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.repository.GodownPurchaseDetailsRepository;
import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;
import com.niche.ng.service.mapper.GodownPurchaseDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing GodownPurchaseDetails.
 */
@Service
@Transactional
public class GodownPurchaseDetailsServiceImpl implements GodownPurchaseDetailsService {

    private final Logger log = LoggerFactory.getLogger(GodownPurchaseDetailsServiceImpl.class);

    private final GodownPurchaseDetailsRepository godownPurchaseDetailsRepository;

    private final GodownPurchaseDetailsMapper godownPurchaseDetailsMapper;

    public GodownPurchaseDetailsServiceImpl(GodownPurchaseDetailsRepository godownPurchaseDetailsRepository, GodownPurchaseDetailsMapper godownPurchaseDetailsMapper) {
        this.godownPurchaseDetailsRepository = godownPurchaseDetailsRepository;
        this.godownPurchaseDetailsMapper = godownPurchaseDetailsMapper;
    }

    /**
     * Save a godownPurchaseDetails.
     *
     * @param godownPurchaseDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GodownPurchaseDetailsDTO save(GodownPurchaseDetailsDTO godownPurchaseDetailsDTO) {
        log.debug("Request to save GodownPurchaseDetails : {}", godownPurchaseDetailsDTO);
        GodownPurchaseDetails godownPurchaseDetails = godownPurchaseDetailsMapper.toEntity(godownPurchaseDetailsDTO);
        godownPurchaseDetails = godownPurchaseDetailsRepository.save(godownPurchaseDetails);
        return godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);
    }

    /**
     * Get all the godownPurchaseDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GodownPurchaseDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GodownPurchaseDetails");
        return godownPurchaseDetailsRepository.findAll(pageable)
            .map(godownPurchaseDetailsMapper::toDto);
    }


    /**
     * Get one godownPurchaseDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GodownPurchaseDetailsDTO> findOne(Long id) {
        log.debug("Request to get GodownPurchaseDetails : {}", id);
        return godownPurchaseDetailsRepository.findById(id)
            .map(godownPurchaseDetailsMapper::toDto);
    }

    /**
     * Delete the godownPurchaseDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GodownPurchaseDetails : {}", id);
        godownPurchaseDetailsRepository.deleteById(id);
    }

    /**
     * softDelete the Zonal by id
     * 
     * @param id the id of the entity
     */
    @Override
    public void softDelete(Long id) {
        log.debug("Request to delete Zonal : {}", id);
        godownPurchaseDetailsRepository.findById(id);
    }
}
