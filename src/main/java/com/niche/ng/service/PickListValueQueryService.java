/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueQueryService
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

import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.PickListValueRepository;
import com.niche.ng.service.dto.PickListValueCriteria;

import com.niche.ng.service.dto.PickListValueDTO;
import com.niche.ng.service.mapper.PickListValueMapper;

/**
 * Service for executing complex queries for PickListValue entities in the database.
 * The main input is a {@link PickListValueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PickListValueDTO} or a {@link Page} of {@link PickListValueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PickListValueQueryService extends QueryService<PickListValue> {

    private final Logger log = LoggerFactory.getLogger(PickListValueQueryService.class);

    private final PickListValueRepository pickListValueRepository;

    private final PickListValueMapper pickListValueMapper;

    public PickListValueQueryService(PickListValueRepository pickListValueRepository, PickListValueMapper pickListValueMapper) {
        this.pickListValueRepository = pickListValueRepository;
        this.pickListValueMapper = pickListValueMapper;
    }

    /**
     * Return a {@link List} of {@link PickListValueDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PickListValueDTO> findByCriteria(PickListValueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PickListValue> specification = createSpecification(criteria);
        return pickListValueMapper.toDto(pickListValueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PickListValueDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PickListValueDTO> findByCriteria(PickListValueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PickListValue> specification = createSpecification(criteria);
        return pickListValueRepository.findAll(specification, page)
            .map(pickListValueMapper::toDto);
    }

    /**
     * Function to convert PickListValueCriteria to a {@link Specification}
     */
    private Specification<PickListValue> createSpecification(PickListValueCriteria criteria) {
        Specification<PickListValue> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PickListValue_.id));
            }
            if (criteria.getPickListValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPickListValue(), PickListValue_.pickListValue));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), PickListValue_.status));
            }
            if (criteria.getSelfIdsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSelfIdsId(), PickListValue_.selfIds, PickListValue_.id));
            }
            if (criteria.getVarietysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getVarietysId(), PickListValue_.varietys, Batch_.id));
            }
            if (criteria.getCategorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCategorysId(), PickListValue_.categorys, Batch_.id));
            }
            if (criteria.getNurseryStockVarietysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockVarietysId(), PickListValue_.nurseryStockVarietys, NurseryStock_.id));
            }
            if (criteria.getNurseryStockCategorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockCategorysId(), PickListValue_.nurseryStockCategorys, NurseryStock_.id));
            }
            if (criteria.getGodownPurchaseVarietysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownPurchaseVarietysId(), PickListValue_.godownPurchaseVarietys, GodownPurchaseDetails_.id));
            }
            if (criteria.getGodownPurchaseCategorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownPurchaseCategorysId(), PickListValue_.godownPurchaseCategorys, GodownPurchaseDetails_.id));
            }
            if (criteria.getGodownPurchaseQuantityTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownPurchaseQuantityTypeId(), PickListValue_.godownPurchaseQuantityTypes, GodownPurchaseDetails_.id));
            }
            if (criteria.getGodownStockVarietysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockVarietysId(), PickListValue_.godownStockVarietys, GodownStock_.id));
            }
            if (criteria.getGodownStockCategorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockCategorysId(), PickListValue_.godownStockCategorys, GodownStock_.id));
            }
            if (criteria.getGodownStockQuantityTypesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownStockQuantityTypesId(), PickListValue_.godownStockQuantityTypes, GodownStock_.id));
            }
            if (criteria.getPickListId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListId(), PickListValue_.pickList, PickList_.id));
            }
            if (criteria.getPickValueId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickValueId(), PickListValue_.pickValue, PickListValue_.id));
            }
            if (criteria.getNurserysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurserysId(), PickListValue_.nurserys, Nursery_.id));
            }
            if (criteria.getBatchQuantityTypesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchQuantityTypesId(), PickListValue_.batchQuantityTypes, Batch_.id));
            }
            if (criteria.getNurseryInventoryVarietysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventoryVarietysId(), PickListValue_.nurseryInventoryVarietys, NurseryInventory_.id));
            }
            if (criteria.getNurseryInventoryCategorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventoryCategorysId(), PickListValue_.nurseryInventoryCategorys, NurseryInventory_.id));
            }
            if (criteria.getNurseryInventoryQuantityTypesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventoryQuantityTypesId(), PickListValue_.nurseryInventoryQuantityTypes, NurseryInventory_.id));
            }
            if (criteria.getNurseryInventoryDamageTypesId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventoryDamageTypesId(), PickListValue_.nurseryInventoryDamageTypes, NurseryInventoryDetails_.id));
            }
            if (criteria.getPickListValueDamageAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListValueDamageAreaId(), PickListValue_.pickListValueDamageAreas, Damage_.id));
            }
            if (criteria.getNurseryStockDamageAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockDamageAreaId(), PickListValue_.nurseryStockDamageAreas, NurseryStockDetails_.id));
            }
            if (criteria.getFinancialYearNameId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearNameId(), PickListValue_.financialYearNames, FinancialYearSettings_.id));
            }
            if (criteria.getDamageDescriptionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDamageDescriptionId(), PickListValue_.damageDescriptions, Damage_.id));
            }
            if (criteria.getPointOfSaleVarietysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPointOfSaleVarietysId(), PickListValue_.pointOfSaleVarietys, PointOfSaleDetails_.id));
            }
            if (criteria.getPointOfSaleCategorysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPointOfSaleCategorysId(), PickListValue_.pointOfSaleCategorys, PointOfSaleDetails_.id));
            }
            if (criteria.getCoverFillingDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCoverFillingDetailsId(), PickListValue_.coverFillingDetails, CoverFillingDetails_.id));
            }
        }
        return specification;
    }

}
