/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingDetailsQueryService
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

import com.niche.ng.domain.CoverFillingDetails;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.CoverFillingDetailsRepository;
import com.niche.ng.service.dto.CoverFillingDetailsCriteria;

import com.niche.ng.service.dto.CoverFillingDetailsDTO;
import com.niche.ng.service.mapper.CoverFillingDetailsMapper;

/**
 * Service for executing complex queries for CoverFillingDetails entities in the database.
 * The main input is a {@link CoverFillingDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CoverFillingDetailsDTO} or a {@link Page} of {@link CoverFillingDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CoverFillingDetailsQueryService extends QueryService<CoverFillingDetails> {

    private final Logger log = LoggerFactory.getLogger(CoverFillingDetailsQueryService.class);

    private final CoverFillingDetailsRepository coverFillingDetailsRepository;

    private final CoverFillingDetailsMapper coverFillingDetailsMapper;

    public CoverFillingDetailsQueryService(CoverFillingDetailsRepository coverFillingDetailsRepository, CoverFillingDetailsMapper coverFillingDetailsMapper) {
        this.coverFillingDetailsRepository = coverFillingDetailsRepository;
        this.coverFillingDetailsMapper = coverFillingDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link CoverFillingDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CoverFillingDetailsDTO> findByCriteria(CoverFillingDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CoverFillingDetails> specification = createSpecification(criteria);
        return coverFillingDetailsMapper.toDto(coverFillingDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CoverFillingDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CoverFillingDetailsDTO> findByCriteria(CoverFillingDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CoverFillingDetails> specification = createSpecification(criteria);
        return coverFillingDetailsRepository.findAll(specification, page)
            .map(coverFillingDetailsMapper::toDto);
    }

    /**
     * Function to convert CoverFillingDetailsCriteria to a {@link Specification}
     */
    private Specification<CoverFillingDetails> createSpecification(CoverFillingDetailsCriteria criteria) {
        Specification<CoverFillingDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CoverFillingDetails_.id));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), CoverFillingDetails_.quantity));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), CoverFillingDetails_.date));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), CoverFillingDetails_.status));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CoverFillingDetails_.description));
            }
            if (criteria.getCoverFillingId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCoverFillingId(), CoverFillingDetails_.coverFilling, CoverFilling_.id));
            }
            if (criteria.getDamageTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDamageTypeId(), CoverFillingDetails_.damageType, PickListValue_.id));
            }
        }
        return specification;
    }

}
