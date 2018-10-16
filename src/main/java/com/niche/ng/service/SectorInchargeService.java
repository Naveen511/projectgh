package com.niche.ng.service;

import com.niche.ng.service.dto.SectorInchargeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SectorIncharge.
 */
public interface SectorInchargeService {

    /**
     * Save a sectorIncharge.
     *
     * @param sectorInchargeDTO the entity to save
     * @return the persisted entity
     */
    SectorInchargeDTO save(SectorInchargeDTO sectorInchargeDTO);

    /**
     * Get all the sectorIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SectorInchargeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sectorIncharge.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SectorInchargeDTO> findOne(Long id);

    /**
     * Delete the "id" sectorIncharge.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
