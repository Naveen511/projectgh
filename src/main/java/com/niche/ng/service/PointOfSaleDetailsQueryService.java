/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PointOfSaleDetailsQueryService
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

import com.niche.ng.domain.PointOfSaleDetails;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.PointOfSaleDetailsRepository;
import com.niche.ng.service.dto.PointOfSaleDetailsCriteria;

import com.niche.ng.service.dto.PointOfSaleDetailsDTO;
import com.niche.ng.service.mapper.PointOfSaleDetailsMapper;

/**
 * Service for executing complex queries for PointOfSaleDetails entities in the database.
 * The main input is a {@link PointOfSaleDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PointOfSaleDetailsDTO} or a {@link Page} of {@link PointOfSaleDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PointOfSaleDetailsQueryService extends QueryService<PointOfSaleDetails> {

    private final Logger log = LoggerFactory.getLogger(PointOfSaleDetailsQueryService.class);

    private final PointOfSaleDetailsRepository pointOfSaleDetailsRepository;

    private final PointOfSaleDetailsMapper pointOfSaleDetailsMapper;

    public PointOfSaleDetailsQueryService(PointOfSaleDetailsRepository pointOfSaleDetailsRepository, PointOfSaleDetailsMapper pointOfSaleDetailsMapper) {
        this.pointOfSaleDetailsRepository = pointOfSaleDetailsRepository;
        this.pointOfSaleDetailsMapper = pointOfSaleDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link PointOfSaleDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PointOfSaleDetailsDTO> findByCriteria(PointOfSaleDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PointOfSaleDetails> specification = createSpecification(criteria);
        return pointOfSaleDetailsMapper.toDto(pointOfSaleDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PointOfSaleDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PointOfSaleDetailsDTO> findByCriteria(PointOfSaleDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PointOfSaleDetails> specification = createSpecification(criteria);
        return pointOfSaleDetailsRepository.findAll(specification, page)
            .map(pointOfSaleDetailsMapper::toDto);
    }

    /**
     * Function to convert PointOfSaleDetailsCriteria to a {@link Specification}
     */
    private Specification<PointOfSaleDetails> createSpecification(PointOfSaleDetailsCriteria criteria) {
        Specification<PointOfSaleDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PointOfSaleDetails_.id));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), PointOfSaleDetails_.quantity));
            }
            if (criteria.getPurposeOfTaking() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPurposeOfTaking(), PointOfSaleDetails_.purposeOfTaking));
            }
            if (criteria.getDonorName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDonorName(), PointOfSaleDetails_.donorName));
            }
            if (criteria.getDonorAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDonorAddress(), PointOfSaleDetails_.donorAddress));
            }
            if (criteria.getContactNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactNo(), PointOfSaleDetails_.contactNo));
            }
            if (criteria.getDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscount(), PointOfSaleDetails_.discount));
            }
            if (criteria.getDiscountAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscountAmount(), PointOfSaleDetails_.discountAmount));
            }
            if (criteria.getCollectedAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCollectedAmount(), PointOfSaleDetails_.collectedAmount));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), PointOfSaleDetails_.date));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), PointOfSaleDetails_.status));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), PointOfSaleDetails_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), PointOfSaleDetails_.pickListCategory, PickListValue_.id));
            }
            if (criteria.getNurseryStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockId(), PointOfSaleDetails_.nurseryStock, NurseryStock_.id));
            }
        }
        return specification;
    }

}
