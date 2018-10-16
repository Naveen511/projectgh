/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/20
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs QuantityQueryService
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

import com.niche.ng.domain.Quantity;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.QuantityRepository;
import com.niche.ng.service.dto.QuantityCriteria;

import com.niche.ng.service.dto.QuantityDTO;
import com.niche.ng.service.mapper.QuantityMapper;

/**
 * Service for executing complex queries for Quantity entities in the database.
 * The main input is a {@link QuantityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QuantityDTO} or a {@link Page} of {@link QuantityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuantityQueryService extends QueryService<Quantity> {

    private final Logger log = LoggerFactory.getLogger(QuantityQueryService.class);

    private final QuantityRepository quantityRepository;

    private final QuantityMapper quantityMapper;

    public QuantityQueryService(QuantityRepository quantityRepository, QuantityMapper quantityMapper) {
        this.quantityRepository = quantityRepository;
        this.quantityMapper = quantityMapper;
    }

    /**
     * Return a {@link List} of {@link QuantityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QuantityDTO> findByCriteria(QuantityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Quantity> specification = createSpecification(criteria);
        return quantityMapper.toDto(quantityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QuantityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuantityDTO> findByCriteria(QuantityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Quantity> specification = createSpecification(criteria);
        return quantityRepository.findAll(specification, page)
            .map(quantityMapper::toDto);
    }

    /**
     * Function to convert QuantityCriteria to a {@link Specification}
     */
    private Specification<Quantity> createSpecification(QuantityCriteria criteria) {
        Specification<Quantity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Quantity_.id));
            }
            if (criteria.getApproxQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getApproxQuantity(), Quantity_.approxQuantity));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), Quantity_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), Quantity_.pickListCategory, PickListValue_.id));
            }
        }
        return specification;
    }

}
