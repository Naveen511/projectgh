/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingQueryService
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

import com.niche.ng.domain.CoverFilling;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.CoverFillingRepository;
import com.niche.ng.service.dto.CoverFillingCriteria;

import com.niche.ng.service.dto.CoverFillingDTO;
import com.niche.ng.service.mapper.CoverFillingMapper;

/**
 * Service for executing complex queries for CoverFilling entities in the database.
 * The main input is a {@link CoverFillingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CoverFillingDTO} or a {@link Page} of {@link CoverFillingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CoverFillingQueryService extends QueryService<CoverFilling> {

    private final Logger log = LoggerFactory.getLogger(CoverFillingQueryService.class);

    private final CoverFillingRepository coverFillingRepository;

    private final CoverFillingMapper coverFillingMapper;

    public CoverFillingQueryService(CoverFillingRepository coverFillingRepository, CoverFillingMapper coverFillingMapper) {
        this.coverFillingRepository = coverFillingRepository;
        this.coverFillingMapper = coverFillingMapper;
    }

    /**
     * Return a {@link List} of {@link CoverFillingDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CoverFillingDTO> findByCriteria(CoverFillingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CoverFilling> specification = createSpecification(criteria);
        return coverFillingMapper.toDto(coverFillingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CoverFillingDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CoverFillingDTO> findByCriteria(CoverFillingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CoverFilling> specification = createSpecification(criteria);
        return coverFillingRepository.findAll(specification, page)
            .map(coverFillingMapper::toDto);
    }

    /**
     * Function to convert CoverFillingCriteria to a {@link Specification}
     */
    private Specification<CoverFilling> createSpecification(CoverFillingCriteria criteria) {
        Specification<CoverFilling> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CoverFilling_.id));
            }
            if (criteria.getNoOfCover() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNoOfCover(), CoverFilling_.noOfCover));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), CoverFilling_.date));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CoverFilling_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), CoverFilling_.status));
            }
            if (criteria.getDamageQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDamageQuantity(), CoverFilling_.damageQuantity));
            }
            if (criteria.getCoverFillingDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCoverFillingDetailsId(), CoverFilling_.coverFillingDetails, CoverFillingDetails_.id));
            }
        }
        return specification;
    }

}
