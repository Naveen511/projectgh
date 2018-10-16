package com.niche.ng.service.impl;

import com.niche.ng.service.MapSectorWithZonalService;
import com.niche.ng.domain.MapSectorWithZonal;
import com.niche.ng.repository.MapSectorWithZonalRepository;
import com.niche.ng.service.dto.MapSectorWithZonalDTO;
import com.niche.ng.service.mapper.MapSectorWithZonalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing MapSectorWithZonal.
 */
@Service
@Transactional
public class MapSectorWithZonalServiceImpl implements MapSectorWithZonalService {

    private final Logger log = LoggerFactory.getLogger(MapSectorWithZonalServiceImpl.class);

    private final MapSectorWithZonalRepository mapSectorWithZonalRepository;

    private final MapSectorWithZonalMapper mapSectorWithZonalMapper;

    public MapSectorWithZonalServiceImpl(MapSectorWithZonalRepository mapSectorWithZonalRepository, MapSectorWithZonalMapper mapSectorWithZonalMapper) {
        this.mapSectorWithZonalRepository = mapSectorWithZonalRepository;
        this.mapSectorWithZonalMapper = mapSectorWithZonalMapper;
    }

    /**
     * Save a mapSectorWithZonal.
     *
     * @param mapSectorWithZonalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MapSectorWithZonalDTO save(MapSectorWithZonalDTO mapSectorWithZonalDTO) {
        log.debug("Request to save MapSectorWithZonal : {}", mapSectorWithZonalDTO);
        MapSectorWithZonal mapSectorWithZonal = mapSectorWithZonalMapper.toEntity(mapSectorWithZonalDTO);
        mapSectorWithZonal = mapSectorWithZonalRepository.save(mapSectorWithZonal);
        return mapSectorWithZonalMapper.toDto(mapSectorWithZonal);
    }

    /**
     * Get all the mapSectorWithZonals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MapSectorWithZonalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MapSectorWithZonals");
        return mapSectorWithZonalRepository.findAll(pageable)
            .map(mapSectorWithZonalMapper::toDto);
    }


    /**
     * Get one mapSectorWithZonal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MapSectorWithZonalDTO> findOne(Long id) {
        log.debug("Request to get MapSectorWithZonal : {}", id);
        return mapSectorWithZonalRepository.findById(id)
            .map(mapSectorWithZonalMapper::toDto);
    }

    /**
     * Delete the mapSectorWithZonal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MapSectorWithZonal : {}", id);
        mapSectorWithZonalRepository.deleteById(id);
    }

    /**
     * Get all the sectors mapped record by sectorId and status.
     *
     * @param sectorId the sectorId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MapSectorWithZonalDTO> findParticularSectorActiveRecord(Long sectorId, Integer status) {
        // log.debug("Request to get Sectors : {}", sectorId);
        List<MapSectorWithZonal> list = mapSectorWithZonalRepository.findBySectorIdAndStatus(sectorId, status);
        return mapSectorWithZonalMapper.toDto(list);
    }

    /**
     * Get all the sectors mapped record by sectorId.
     *
     * @param sectorId the sectorId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MapSectorWithZonalDTO> findParticularSectorRecord(Long sectorId) {
        // log.debug("Request to get Sectors : {}", sectorId);
        List<MapSectorWithZonal> list = mapSectorWithZonalRepository.findBySectorId(sectorId);
        return mapSectorWithZonalMapper.toDto(list);
    }
}
