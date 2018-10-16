/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownQueryService
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

import com.niche.ng.domain.Godown;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.GodownRepository;
import com.niche.ng.service.dto.GodownCriteria;

import com.niche.ng.service.dto.GodownDTO;
import com.niche.ng.service.mapper.GodownMapper;

/**
 * Service for executing complex queries for Godown entities in the database.
 * The main input is a {@link GodownCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GodownDTO} or a {@link Page} of {@link GodownDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GodownQueryService extends QueryService<Godown> {

    private final Logger log = LoggerFactory.getLogger(GodownQueryService.class);

    private final GodownRepository godownRepository;

    private final GodownMapper godownMapper;

    public GodownQueryService(GodownRepository godownRepository, GodownMapper godownMapper) {
        this.godownRepository = godownRepository;
        this.godownMapper = godownMapper;
    }

    /**
     * Return a {@link List} of {@link GodownDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GodownDTO> findByCriteria(GodownCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Godown> specification = createSpecification(criteria);
        return godownMapper.toDto(godownRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GodownDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GodownDTO> findByCriteria(GodownCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Godown> specification = createSpecification(criteria);
        return godownRepository.findAll(specification, page)
            .map(godownMapper::toDto);
    }

    /**
     * Function to convert GodownCriteria to a {@link Specification}
     */
    private Specification<Godown> createSpecification(GodownCriteria criteria) {
        Specification<Godown> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Godown_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Godown_.name));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Godown_.address));
            }
            if (criteria.getIncharge() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncharge(), Godown_.incharge));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Godown_.status));
            }
            if (criteria.getGodownPurchaseDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownPurchaseDetailsId(), Godown_.godownPurchaseDetails, GodownPurchaseDetails_.id));
            }
            if (criteria.getGodownStocksId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStocksId(), Godown_.godownStocks, GodownStock_.id));
            }
            if (criteria.getFinancialYearGodownId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearGodownId(), Godown_.financialYearGodown, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
