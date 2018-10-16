/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryQueryService
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

import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.NurseryRepository;
import com.niche.ng.service.dto.NurseryCriteria;

import com.niche.ng.service.dto.NurseryDTO;
import com.niche.ng.service.mapper.NurseryMapper;

/**
 * Service for executing complex queries for Nursery entities in the database.
 * The main input is a {@link NurseryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NurseryDTO} or a {@link Page} of {@link NurseryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NurseryQueryService extends QueryService<Nursery> {

    private final Logger log = LoggerFactory.getLogger(NurseryQueryService.class);

    private final NurseryRepository nurseryRepository;

    private final NurseryMapper nurseryMapper;

    public NurseryQueryService(NurseryRepository nurseryRepository, NurseryMapper nurseryMapper) {
        this.nurseryRepository = nurseryRepository;
        this.nurseryMapper = nurseryMapper;
    }

    /**
     * Return a {@link List} of {@link NurseryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NurseryDTO> findByCriteria(NurseryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Nursery> specification = createSpecification(criteria);
        return nurseryMapper.toDto(nurseryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NurseryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NurseryDTO> findByCriteria(NurseryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Nursery> specification = createSpecification(criteria);
        return nurseryRepository.findAll(specification, page)
            .map(nurseryMapper::toDto);
    }

    /**
     * Function to convert NurseryCriteria to a {@link Specification}
     */
    private Specification<Nursery> createSpecification(NurseryCriteria criteria) {
        Specification<Nursery> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Nursery_.id));
            }
            if (criteria.getNurseryName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNurseryName(), Nursery_.nurseryName));
            }
            if (criteria.getNurseryAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNurseryAddress(), Nursery_.nurseryAddress));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Nursery_.status));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Nursery_.code));
            }
            if (criteria.getBatchsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchsId(), Nursery_.batchs, Batch_.id));
            }
            if (criteria.getNurseryStocksId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStocksId(), Nursery_.nurseryStocks, NurseryStock_.id));
            }
            if (criteria.getSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSectorId(), Nursery_.sector, Sector_.id));
            }
            if (criteria.getNurseryTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryTypeId(), Nursery_.nurseryType, PickListValue_.id));
            }
            if (criteria.getMotherBedsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMotherBedsId(), Nursery_.motherBeds, MotherBed_.id));
            }
            if (criteria.getNurseryInventorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventorysId(), Nursery_.nurseryInventorys, NurseryInventory_.id));
            }
            if (criteria.getNurseryStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockDetailsId(), Nursery_.nurseryStockDetails, NurseryStockDetails_.id));
            }
            if (criteria.getFinancialYearNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearNurseryId(), Nursery_.financialYearNursery, FinancialYearSettings_.id));
            }
            if (criteria.getInchargeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInchargeId(), Nursery_.incharges, NurseryIncharge_.id));
            }
            if (criteria.getMapNurseryWithSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMapNurseryWithSectorId(), Nursery_.mapNurseryWithSectors, MapNurseryWithSector_.id));
            }
            if (criteria.getFromNurseryStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFromNurseryStockDetailsId(), Nursery_.fromNurseryStockDetails, NurseryStockDetails_.id));
            }
        }
        return specification;
    }

}
