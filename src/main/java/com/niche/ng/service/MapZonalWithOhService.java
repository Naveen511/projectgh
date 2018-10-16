package com.niche.ng.service;

import com.niche.ng.service.dto.MapZonalWithOhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing MapZonalWithOh.
 */
public interface MapZonalWithOhService {

    /**
     * Save a mapZonalWithOh.
     *
     * @param mapZonalWithOhDTO the entity to save
     * @return the persisted entity
     */
    MapZonalWithOhDTO save(MapZonalWithOhDTO mapZonalWithOhDTO);

    /**
     * Get all the mapZonalWithOhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MapZonalWithOhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mapZonalWithOh.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MapZonalWithOhDTO> findOne(Long id);

    /**
     * Delete the "id" mapZonalWithOh.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "zonalId" records.
     *
     * @param zonalId the zonalId of the entity
     * @param status the status of the entity
     * @return the list of entity
     */
    List<MapZonalWithOhDTO> findParticularZonalActiveRecord(Long zonalId, Integer status);

    /**
     * Get the "zonalId" records.
     *
     * @param zonalId the zonalId of the entity
     * @return the list of entity
     */
    List<MapZonalWithOhDTO> findParticularZonalRecord(Long zonalId);
}
