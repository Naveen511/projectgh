/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsQueryService
 *
 *******************************************************************************/
package com.niche.ng.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.GodownStockDetailsRepository;
import com.niche.ng.service.dto.GodownStockDetailsCriteria;

import com.niche.ng.service.dto.GodownStockDetailsDTO;
import com.niche.ng.service.mapper.GodownStockDetailsMapper;

/**
 * Service for executing complex queries for GodownStockDetails entities in the database.
 * The main input is a {@link GodownStockDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GodownStockDetailsDTO} or a {@link Page} of {@link GodownStockDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GodownStockDetailsQueryService extends QueryService<GodownStockDetails> {

    private final Logger log = LoggerFactory.getLogger(GodownStockDetailsQueryService.class);

    private final GodownStockDetailsRepository godownStockDetailsRepository;

    private final GodownStockDetailsMapper godownStockDetailsMapper;

    public GodownStockDetailsQueryService(GodownStockDetailsRepository godownStockDetailsRepository, GodownStockDetailsMapper godownStockDetailsMapper) {
        this.godownStockDetailsRepository = godownStockDetailsRepository;
        this.godownStockDetailsMapper = godownStockDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link GodownStockDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GodownStockDetailsDTO> findByCriteria(GodownStockDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GodownStockDetails> specification = createSpecification(criteria);
        return godownStockDetailsMapper.toDto(godownStockDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GodownStockDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GodownStockDetailsDTO> findByCriteria(GodownStockDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GodownStockDetails> specification = createSpecification(criteria);
        return godownStockDetailsRepository.findAll(specification, page)
            .map(godownStockDetailsMapper::toDto);
    }

    /**
     * Function to convert GodownStockDetailsCriteria to a {@link Specification}
     */
    private Specification<GodownStockDetails> createSpecification(GodownStockDetailsCriteria criteria) {
        Specification<GodownStockDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), GodownStockDetails_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), GodownStockDetails_.date));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), GodownStockDetails_.quantity));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), GodownStockDetails_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), GodownStockDetails_.status));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), GodownStockDetails_.price));
            }
            if (criteria.getGodownStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockId(), GodownStockDetails_.godownStock, GodownStock_.id));
            }
            if (criteria.getFinancialYearGodownStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearGodownStockDetailsId(), GodownStockDetails_.financialYearGodownStockDetails, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
