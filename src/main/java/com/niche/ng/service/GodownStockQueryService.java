/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockQueryService
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

import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.GodownStockRepository;
import com.niche.ng.service.dto.GodownStockCriteria;

import com.niche.ng.service.dto.GodownStockDTO;
import com.niche.ng.service.mapper.GodownStockMapper;

/**
 * Service for executing complex queries for GodownStock entities in the database.
 * The main input is a {@link GodownStockCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GodownStockDTO} or a {@link Page} of {@link GodownStockDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GodownStockQueryService extends QueryService<GodownStock> {

    private final Logger log = LoggerFactory.getLogger(GodownStockQueryService.class);

    private final GodownStockRepository godownStockRepository;

    private final GodownStockMapper godownStockMapper;

    public GodownStockQueryService(GodownStockRepository godownStockRepository, GodownStockMapper godownStockMapper) {
        this.godownStockRepository = godownStockRepository;
        this.godownStockMapper = godownStockMapper;
    }

    /**
     * Return a {@link List} of {@link GodownStockDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GodownStockDTO> findByCriteria(GodownStockCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GodownStock> specification = createSpecification(criteria);
        return godownStockMapper.toDto(godownStockRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GodownStockDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GodownStockDTO> findByCriteria(GodownStockCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GodownStock> specification = createSpecification(criteria);
        return godownStockRepository.findAll(specification, page)
            .map(godownStockMapper::toDto);
    }

    /**
     * Function to convert GodownStockCriteria to a {@link Specification}
     */
    private Specification<GodownStock> createSpecification(GodownStockCriteria criteria) {
        Specification<GodownStock> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), GodownStock_.id));
            }
            if (criteria.getCurrentQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrentQuantity(), GodownStock_.currentQuantity));
            }
            if (criteria.getAddedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAddedQuantity(), GodownStock_.addedQuantity));
            }
            if (criteria.getConsumedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumedQuantity(), GodownStock_.consumedQuantity));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), GodownStock_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), GodownStock_.status));
            }
            if (criteria.getGodownStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockDetailsId(), GodownStock_.godownStockDetails, GodownStockDetails_.id));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), GodownStock_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), GodownStock_.pickListCategory, PickListValue_.id));
            }
            if (criteria.getPickListQuantityTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListQuantityTypeId(), GodownStock_.pickListQuantityType, PickListValue_.id));
            }
            if (criteria.getGodownId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownId(), GodownStock_.godown, Godown_.id));
            }
            if (criteria.getFinancialYearGodownStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearGodownStockId(), GodownStock_.financialYearGodownStock, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
