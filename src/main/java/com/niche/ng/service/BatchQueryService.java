/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQueryService
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

import com.niche.ng.domain.Batch;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.BatchRepository;
import com.niche.ng.service.dto.BatchCriteria;

import com.niche.ng.service.dto.BatchDTO;
import com.niche.ng.service.mapper.BatchMapper;

/**
 * Service for executing complex queries for Batch entities in the database.
 * The main input is a {@link BatchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BatchDTO} or a {@link Page} of {@link BatchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BatchQueryService extends QueryService<Batch> {

    private final Logger log = LoggerFactory.getLogger(BatchQueryService.class);

    private final BatchRepository batchRepository;

    private final BatchMapper batchMapper;

    public BatchQueryService(BatchRepository batchRepository, BatchMapper batchMapper) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
    }

    /**
     * Return a {@link List} of {@link BatchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BatchDTO> findByCriteria(BatchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Batch> specification = createSpecification(criteria);
        return batchMapper.toDto(batchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BatchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BatchDTO> findByCriteria(BatchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Batch> specification = createSpecification(criteria);
        return batchRepository.findAll(specification, page)
            .map(batchMapper::toDto);
    }

    /**
     * Function to convert BatchCriteria to a {@link Specification}
     */
    private Specification<Batch> createSpecification(BatchCriteria criteria) {
        Specification<Batch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Batch_.id));
            }
            if (criteria.getBatchNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBatchNo(), Batch_.batchNo));
            }
            if (criteria.getBatchName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBatchName(), Batch_.batchName));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), Batch_.quantity));
            }
            if (criteria.getShowingType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShowingType(), Batch_.showingType));
            }
            if (criteria.getSowingDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSowingDate(), Batch_.sowingDate));
            }
            if (criteria.getClosedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClosedDate(), Batch_.closedDate));
            }
            if (criteria.getRound() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRound(), Batch_.round));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), Batch_.remarks));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Batch_.status));
            }
            if (criteria.getNoOfKg() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoOfKg(), Batch_.noOfKg));
            }
            if (criteria.getDamagesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDamagesId(), Batch_.damages, Damage_.id));
            }
            if (criteria.getShadeAreasId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getShadeAreasId(), Batch_.shadeAreas, ShadeArea_.id));
            }
            if (criteria.getNurseryStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockDetailsId(), Batch_.nurseryStockDetails, NurseryStockDetails_.id));
            }
            if (criteria.getNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryId(), Batch_.nursery, Nursery_.id));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), Batch_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), Batch_.pickListCategory, PickListValue_.id));
            }
            if (criteria.getQuantityTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuantityTypeId(), Batch_.quantityType, PickListValue_.id));
            }
            if (criteria.getMotherBedId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMotherBedId(), Batch_.motherBed, MotherBed_.id));
            }
            if (criteria.getFinancialYearBatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearBatchId(), Batch_.financialYearBatch, FinancialYearSettings_.id));
            }
            if (criteria.getBatchQuantityId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchQuantityId(), Batch_.batchQuantities, BatchQuantity_.id));
            }
        }
        return specification;
    }

}
