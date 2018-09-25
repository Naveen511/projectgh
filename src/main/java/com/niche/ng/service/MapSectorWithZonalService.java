package com.niche.ng.service;

import com.niche.ng.service.dto.MapSectorWithZonalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing MapSectorWithZonal.
 */
public interface MapSectorWithZonalService {

    /**
     * Save a mapSectorWithZonal.
     *
     * @param mapSectorWithZonalDTO the entity to save
     * @return the persisted entity
     */
    MapSectorWithZonalDTO save(MapSectorWithZonalDTO mapSectorWithZonalDTO);

    /**
     * Get all the mapSectorWithZonals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MapSectorWithZonalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mapSectorWithZonal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MapSectorWithZonalDTO> findOne(Long id);

    /**
     * Delete the "id" mapSectorWithZonal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "sectorId" records.
     *
     * @param sectorId the sectorId of the entity
     * @param status the status of the entity
     * @return the list of entity
     */
    List<MapSectorWithZonalDTO> findParticularSectorActiveRecord(Long sectorId, Integer status);

    /**
     * Get the "sectorId" records.
     *
     * @param sectorId the sectorId of the entity
     * @return the list of entity
     */
    List<MapSectorWithZonalDTO> findParticularSectorRecord(Long sectorId);
}
