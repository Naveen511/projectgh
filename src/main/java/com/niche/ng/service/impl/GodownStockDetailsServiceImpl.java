/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.GodownStockDetailsService;
import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.repository.GodownStockDetailsRepository;
import com.niche.ng.service.dto.GodownStockDetailsDTO;
import com.niche.ng.service.mapper.GodownStockDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing GodownStockDetails.
 */
@Service
@Transactional
public class GodownStockDetailsServiceImpl implements GodownStockDetailsService {

    private final Logger log = LoggerFactory.getLogger(GodownStockDetailsServiceImpl.class);

    private final GodownStockDetailsRepository godownStockDetailsRepository;

    private final GodownStockDetailsMapper godownStockDetailsMapper;

    public GodownStockDetailsServiceImpl(GodownStockDetailsRepository godownStockDetailsRepository, GodownStockDetailsMapper godownStockDetailsMapper) {
        this.godownStockDetailsRepository = godownStockDetailsRepository;
        this.godownStockDetailsMapper = godownStockDetailsMapper;
    }

    /**
     * Save a godownStockDetails.
     *
     * @param godownStockDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GodownStockDetailsDTO save(GodownStockDetailsDTO godownStockDetailsDTO) {
        log.debug("Request to save GodownStockDetails : {}", godownStockDetailsDTO);
        GodownStockDetails godownStockDetails = godownStockDetailsMapper.toEntity(godownStockDetailsDTO);
        godownStockDetails = godownStockDetailsRepository.save(godownStockDetails);
        return godownStockDetailsMapper.toDto(godownStockDetails);
    }

    /**
     * Get all the godownStockDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GodownStockDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GodownStockDetails");
        return godownStockDetailsRepository.findAll(pageable)
            .map(godownStockDetailsMapper::toDto);
    }

    /**
     * Get one godownStockDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GodownStockDetailsDTO> findOne(Long id) {
        log.debug("Request to get GodownStockDetails : {}", id);
        return godownStockDetailsRepository.findById(id)
            .map(godownStockDetailsMapper::toDto);
    }

    /**
     * Delete the godownStockDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GodownStockDetails : {}", id);
        godownStockDetailsRepository.deleteById(id);
    }

    /**
     * Get one godownStockDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public List<GodownStockDetailsDTO> findStockList(Long godownStockId) {
        List<GodownStockDetails> stockList = godownStockDetailsRepository.findByGodownStockId(godownStockId);
        return godownStockDetailsMapper.toDto(stockList);
    }
}
