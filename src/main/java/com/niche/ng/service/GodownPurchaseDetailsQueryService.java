/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsQueryService
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

import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.GodownPurchaseDetailsRepository;
import com.niche.ng.service.dto.GodownPurchaseDetailsCriteria;

import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;
import com.niche.ng.service.mapper.GodownPurchaseDetailsMapper;

/**
 * Service for executing complex queries for GodownPurchaseDetails entities in the database.
 * The main input is a {@link GodownPurchaseDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GodownPurchaseDetailsDTO} or a {@link Page} of {@link GodownPurchaseDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GodownPurchaseDetailsQueryService extends QueryService<GodownPurchaseDetails> {

    private final Logger log = LoggerFactory.getLogger(GodownPurchaseDetailsQueryService.class);

    private final GodownPurchaseDetailsRepository godownPurchaseDetailsRepository;

    private final GodownPurchaseDetailsMapper godownPurchaseDetailsMapper;

    public GodownPurchaseDetailsQueryService(GodownPurchaseDetailsRepository godownPurchaseDetailsRepository, GodownPurchaseDetailsMapper godownPurchaseDetailsMapper) {
        this.godownPurchaseDetailsRepository = godownPurchaseDetailsRepository;
        this.godownPurchaseDetailsMapper = godownPurchaseDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link GodownPurchaseDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GodownPurchaseDetailsDTO> findByCriteria(GodownPurchaseDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GodownPurchaseDetails> specification = createSpecification(criteria);
        return godownPurchaseDetailsMapper.toDto(godownPurchaseDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GodownPurchaseDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GodownPurchaseDetailsDTO> findByCriteria(GodownPurchaseDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GodownPurchaseDetails> specification = createSpecification(criteria);
        return godownPurchaseDetailsRepository.findAll(specification, page)
            .map(godownPurchaseDetailsMapper::toDto);
    }

    /**
     * Function to convert GodownPurchaseDetailsCriteria to a {@link Specification}
     */
    private Specification<GodownPurchaseDetails> createSpecification(GodownPurchaseDetailsCriteria criteria) {
        Specification<GodownPurchaseDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), GodownPurchaseDetails_.id));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), GodownPurchaseDetails_.quantity));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), GodownPurchaseDetails_.date));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), GodownPurchaseDetails_.price));
            }
            if (criteria.getOwnedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOwnedBy(), GodownPurchaseDetails_.ownedBy));
            }
            if (criteria.getVendorName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVendorName(), GodownPurchaseDetails_.vendorName));
            }
            if (criteria.getVendorAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVendorAddress(), GodownPurchaseDetails_.vendorAddress));
            }
            if (criteria.getVendorPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVendorPhone(), GodownPurchaseDetails_.vendorPhone));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), GodownPurchaseDetails_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), GodownPurchaseDetails_.status));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), GodownPurchaseDetails_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), GodownPurchaseDetails_.pickListCategory, PickListValue_.id));
            }
            if (criteria.getPickListQuantityTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListQuantityTypeId(), GodownPurchaseDetails_.pickListQuantityType, PickListValue_.id));
            }
            if (criteria.getGodownId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGodownId(), GodownPurchaseDetails_.godown, Godown_.id));
            }
            if (criteria.getFinancialYearGodownPurchaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearGodownPurchaseId(), GodownPurchaseDetails_.financialYearGodownPurchase, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
