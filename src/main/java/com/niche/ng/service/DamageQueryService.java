/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageQueryService
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

import com.niche.ng.domain.Damage;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.DamageRepository;
import com.niche.ng.service.dto.DamageCriteria;

import com.niche.ng.service.dto.DamageDTO;
import com.niche.ng.service.mapper.DamageMapper;

/**
 * Service for executing complex queries for Damage entities in the database.
 * The main input is a {@link DamageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DamageDTO} or a {@link Page} of {@link DamageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DamageQueryService extends QueryService<Damage> {

    private final Logger log = LoggerFactory.getLogger(DamageQueryService.class);

    private final DamageRepository damageRepository;

    private final DamageMapper damageMapper;

    public DamageQueryService(DamageRepository damageRepository, DamageMapper damageMapper) {
        this.damageRepository = damageRepository;
        this.damageMapper = damageMapper;
    }

    /**
     * Return a {@link List} of {@link DamageDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DamageDTO> findByCriteria(DamageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Damage> specification = createSpecification(criteria);
        return damageMapper.toDto(damageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DamageDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DamageDTO> findByCriteria(DamageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Damage> specification = createSpecification(criteria);
        return damageRepository.findAll(specification, page)
            .map(damageMapper::toDto);
    }

    /**
     * Function to convert DamageCriteria to a {@link Specification}
     */
    private Specification<Damage> createSpecification(DamageCriteria criteria) {
        Specification<Damage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Damage_.id));
            }
            if (criteria.getNoOfQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNoOfQuantity(), Damage_.noOfQuantity));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Damage_.date));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Damage_.status));
            }
            if (criteria.getBatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchId(), Damage_.batch, Batch_.id));
            }
            if (criteria.getDescriptionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDescriptionId(), Damage_.description, PickListValue_.id));
            }
            if (criteria.getDamageAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDamageAreaId(), Damage_.damageArea, PickListValue_.id));
            }
            if (criteria.getFinancialYearDamageId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearDamageId(), Damage_.financialYearDamage, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
