/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsQueryService
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

import com.niche.ng.domain.CommonSettings;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.CommonSettingsRepository;
import com.niche.ng.service.dto.CommonSettingsCriteria;

import com.niche.ng.service.dto.CommonSettingsDTO;
import com.niche.ng.service.mapper.CommonSettingsMapper;

/**
 * Service for executing complex queries for CommonSettings entities in the database.
 * The main input is a {@link CommonSettingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CommonSettingsDTO} or a {@link Page} of {@link CommonSettingsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CommonSettingsQueryService extends QueryService<CommonSettings> {

    private final Logger log = LoggerFactory.getLogger(CommonSettingsQueryService.class);

    private final CommonSettingsRepository commonSettingsRepository;

    private final CommonSettingsMapper commonSettingsMapper;

    public CommonSettingsQueryService(CommonSettingsRepository commonSettingsRepository, CommonSettingsMapper commonSettingsMapper) {
        this.commonSettingsRepository = commonSettingsRepository;
        this.commonSettingsMapper = commonSettingsMapper;
    }

    /**
     * Return a {@link List} of {@link CommonSettingsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommonSettingsDTO> findByCriteria(CommonSettingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CommonSettings> specification = createSpecification(criteria);
        return commonSettingsMapper.toDto(commonSettingsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommonSettingsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonSettingsDTO> findByCriteria(CommonSettingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CommonSettings> specification = createSpecification(criteria);
        return commonSettingsRepository.findAll(specification, page)
            .map(commonSettingsMapper::toDto);
    }

    /**
     * Function to convert CommonSettingsCriteria to a {@link Specification}
     */
    private Specification<CommonSettings> createSpecification(CommonSettingsCriteria criteria) {
        Specification<CommonSettings> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CommonSettings_.id));
            }
            if (criteria.getDaysToCloseBatch() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDaysToCloseBatch(), CommonSettings_.daysToCloseBatch));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), CommonSettings_.status));
            }
        }
        return specification;
    }

}
