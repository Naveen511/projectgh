/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingsQueryService
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

import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.FinancialYearSettingsRepository;
import com.niche.ng.service.dto.FinancialYearSettingsCriteria;

import com.niche.ng.service.dto.FinancialYearSettingsDTO;
import com.niche.ng.service.mapper.FinancialYearSettingsMapper;

/**
 * Service for executing complex queries for FinancialYearSettings entities in the database.
 * The main input is a {@link FinancialYearSettingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FinancialYearSettingsDTO} or a {@link Page} of {@link FinancialYearSettingsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FinancialYearSettingsQueryService extends QueryService<FinancialYearSettings> {

    private final Logger log = LoggerFactory.getLogger(FinancialYearSettingsQueryService.class);

    private final FinancialYearSettingsRepository financialYearSettingsRepository;

    private final FinancialYearSettingsMapper financialYearSettingsMapper;

    public FinancialYearSettingsQueryService(FinancialYearSettingsRepository financialYearSettingsRepository, FinancialYearSettingsMapper financialYearSettingsMapper) {
        this.financialYearSettingsRepository = financialYearSettingsRepository;
        this.financialYearSettingsMapper = financialYearSettingsMapper;
    }

    /**
     * Return a {@link List} of {@link FinancialYearSettingsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FinancialYearSettingsDTO> findByCriteria(FinancialYearSettingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FinancialYearSettings> specification = createSpecification(criteria);
        return financialYearSettingsMapper.toDto(financialYearSettingsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FinancialYearSettingsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FinancialYearSettingsDTO> findByCriteria(FinancialYearSettingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FinancialYearSettings> specification = createSpecification(criteria);
        return financialYearSettingsRepository.findAll(specification, page)
            .map(financialYearSettingsMapper::toDto);
    }

    /**
     * Function to convert FinancialYearSettingsCriteria to a {@link Specification}
     */
    private Specification<FinancialYearSettings> createSpecification(FinancialYearSettingsCriteria criteria) {
        Specification<FinancialYearSettings> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FinancialYearSettings_.id));
            }
            if (criteria.getBatchName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBatchName(), FinancialYearSettings_.batchName));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), FinancialYearSettings_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), FinancialYearSettings_.endDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), FinancialYearSettings_.status));
            }
            if (criteria.getFinancialYearId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearId(), FinancialYearSettings_.financialYear, PickListValue_.id));
            }
            if (criteria.getZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalId(), FinancialYearSettings_.zonals, Zonal_.id));
            }
            if (criteria.getSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSectorId(), FinancialYearSettings_.sectors, Sector_.id));
            }
            if (criteria.getNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryId(), FinancialYearSettings_.nurseries, Nursery_.id));
            }
            if (criteria.getBatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchId(), FinancialYearSettings_.batches, Batch_.id));
            }
            if (criteria.getDamageId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDamageId(), FinancialYearSettings_.damages, Damage_.id));
            }
            if (criteria.getShadeAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getShadeAreaId(), FinancialYearSettings_.shadeAreas, ShadeArea_.id));
            }
            if (criteria.getNurseryStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockId(), FinancialYearSettings_.nurseryStocks, NurseryStock_.id));
            }
            if (criteria.getNurseryStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockDetailsId(), FinancialYearSettings_.nurseryStockDetails, NurseryStockDetails_.id));
            }
            if (criteria.getGodownId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownId(), FinancialYearSettings_.godowns, Godown_.id));
            }
            if (criteria.getGodownStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockId(), FinancialYearSettings_.godownStocks, GodownStock_.id));
            }
            if (criteria.getGodownStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockDetailsId(), FinancialYearSettings_.godownStockDetails, GodownStockDetails_.id));
            }
            if (criteria.getGodownPurchaseDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownPurchaseDetailsId(), FinancialYearSettings_.godownPurchaseDetails, GodownPurchaseDetails_.id));
            }
        }
        return specification;
    }

}
