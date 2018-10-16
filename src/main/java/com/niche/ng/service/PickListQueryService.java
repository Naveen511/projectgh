/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListQueryService
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

import com.niche.ng.domain.PickList;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.PickListRepository;
import com.niche.ng.service.dto.PickListCriteria;

import com.niche.ng.service.dto.PickListDTO;
import com.niche.ng.service.mapper.PickListMapper;

/**
 * Service for executing complex queries for PickList entities in the database.
 * The main input is a {@link PickListCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PickListDTO} or a {@link Page} of {@link PickListDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PickListQueryService extends QueryService<PickList> {

    private final Logger log = LoggerFactory.getLogger(PickListQueryService.class);

    private final PickListRepository pickListRepository;

    private final PickListMapper pickListMapper;

    public PickListQueryService(PickListRepository pickListRepository, PickListMapper pickListMapper) {
        this.pickListRepository = pickListRepository;
        this.pickListMapper = pickListMapper;
    }

    /**
     * Return a {@link List} of {@link PickListDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PickListDTO> findByCriteria(PickListCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PickList> specification = createSpecification(criteria);
        return pickListMapper.toDto(pickListRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PickListDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PickListDTO> findByCriteria(PickListCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PickList> specification = createSpecification(criteria);
        return pickListRepository.findAll(specification, page)
            .map(pickListMapper::toDto);
    }

    /**
     * Function to convert PickListCriteria to a {@link Specification}
     */
    private Specification<PickList> createSpecification(PickListCriteria criteria) {
        Specification<PickList> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PickList_.id));
            }
            if (criteria.getPickListName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPickListName(), PickList_.pickListName));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), PickList_.status));
            }
            if (criteria.getDisplayLabelName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDisplayLabelName(), PickList_.displayLabelName));
            }
            if (criteria.getPickListValuesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListValuesId(), PickList_.pickListValues, PickListValue_.id));
            }
        }
        return specification;
    }

}
