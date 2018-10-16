package com.niche.ng.service;

import com.niche.ng.service.dto.MapNurseryWithSectorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing MapNurseryWithSector.
 */
public interface MapNurseryWithSectorService {

    /**
     * Save a mapNurseryWithSector.
     *
     * @param mapNurseryWithSectorDTO the entity to save
     * @return the persisted entity
     */
    MapNurseryWithSectorDTO save(MapNurseryWithSectorDTO mapNurseryWithSectorDTO);

    /**
     * Get all the mapNurseryWithSectors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MapNurseryWithSectorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mapNurseryWithSector.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MapNurseryWithSectorDTO> findOne(Long id);

    /**
     * Delete the "id" mapNurseryWithSector.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "nurseryId" records.
     *
     * @param nurseryId the nurseryId of the entity
     * @param status the status of the entity
     * @return the list of entity
     */
    List<MapNurseryWithSectorDTO> findParticularNurseryActiveRecord(Long nurseryId, Integer status);

    /**
     * Get the "nurseryId" records.
     *
     * @param nurseryId the nurseryId of the entity
     * @return the list of entity
     */
    List<MapNurseryWithSectorDTO> findParticularNurseryRecord(Long nurseryId);
}
