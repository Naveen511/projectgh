package com.niche.ng.service;

import com.niche.ng.service.dto.OperationalHeadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing OperationalHead.
 */
public interface OperationalHeadService {

    /**
     * Save a operationalHead.
     *
     * @param operationalHeadDTO the entity to save
     * @return the persisted entity
     */
    OperationalHeadDTO save(OperationalHeadDTO operationalHeadDTO);

    /**
     * Get all the operationalHeads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OperationalHeadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" operationalHead.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OperationalHeadDTO> findOne(Long id);

    /**
     * Delete the "id" operationalHead.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get the "status" operationalHead.
     *
     * @param status the batchId of the entity
     * @return the list of entity
     */
    List<OperationalHeadDTO> findParticularStatus(Integer status);

     /**
     * Get the "count" operationalHead.
     *
     * @param status the batchId of the entity
     * @return the list of entity
     */
    Long findActiveCount(Integer status);
}
