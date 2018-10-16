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

import com.niche.ng.domain.Sector;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.SectorRepository;
import com.niche.ng.service.dto.SectorCriteria;

import com.niche.ng.service.dto.SectorDTO;
import com.niche.ng.service.mapper.SectorMapper;

/**
 * Service for executing complex queries for Sector entities in the database.
 * The main input is a {@link SectorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SectorDTO} or a {@link Page} of {@link SectorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SectorQueryService extends QueryService<Sector> {

    private final Logger log = LoggerFactory.getLogger(SectorQueryService.class);

    private final SectorRepository sectorRepository;

    private final SectorMapper sectorMapper;

    public SectorQueryService(SectorRepository sectorRepository, SectorMapper sectorMapper) {
        this.sectorRepository = sectorRepository;
        this.sectorMapper = sectorMapper;
    }

    /**
     * Return a {@link List} of {@link SectorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SectorDTO> findByCriteria(SectorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Sector> specification = createSpecification(criteria);
        return sectorMapper.toDto(sectorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SectorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SectorDTO> findByCriteria(SectorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Sector> specification = createSpecification(criteria);
        return sectorRepository.findAll(specification, page)
            .map(sectorMapper::toDto);
    }

    /**
     * Function to convert SectorCriteria to a {@link Specification}
     */
    private Specification<Sector> createSpecification(SectorCriteria criteria) {
        Specification<Sector> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Sector_.id));
            }
            if (criteria.getSectorName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSectorName(), Sector_.sectorName));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Sector_.status));
            }
            if (criteria.getNurserysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurserysId(), Sector_.nurserys, Nursery_.id));
            }
            if (criteria.getZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalId(), Sector_.zonal, Zonal_.id));
            }
            if (criteria.getFinancialYearSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearSectorId(), Sector_.financialYearSector, FinancialYearSettings_.id));
            }
            if (criteria.getInchargeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInchargeId(), Sector_.incharges, SectorIncharge_.id));
            }
            if (criteria.getMapSectorWithZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMapSectorWithZonalId(), Sector_.mapSectorWithZonals, MapSectorWithZonal_.id));
            }
            if (criteria.getMapNurseryWithSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMapNurseryWithSectorId(), Sector_.mapNurseryWithSectors, MapNurseryWithSector_.id));
            }
        }
        return specification;
    }

}
