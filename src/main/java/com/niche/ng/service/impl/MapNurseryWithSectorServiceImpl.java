package com.niche.ng.service.impl;

import com.niche.ng.service.MapNurseryWithSectorService;
import com.niche.ng.domain.MapNurseryWithSector;
import com.niche.ng.repository.MapNurseryWithSectorRepository;
import com.niche.ng.service.dto.MapNurseryWithSectorDTO;
import com.niche.ng.service.mapper.MapNurseryWithSectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing MapNurseryWithSector.
 */
@Service
@Transactional
public class MapNurseryWithSectorServiceImpl implements MapNurseryWithSectorService {

    private final Logger log = LoggerFactory.getLogger(MapNurseryWithSectorServiceImpl.class);

    private final MapNurseryWithSectorRepository mapNurseryWithSectorRepository;

    private final MapNurseryWithSectorMapper mapNurseryWithSectorMapper;

    public MapNurseryWithSectorServiceImpl(MapNurseryWithSectorRepository mapNurseryWithSectorRepository, MapNurseryWithSectorMapper mapNurseryWithSectorMapper) {
        this.mapNurseryWithSectorRepository = mapNurseryWithSectorRepository;
        this.mapNurseryWithSectorMapper = mapNurseryWithSectorMapper;
    }

    /**
     * Save a mapNurseryWithSector.
     *
     * @param mapNurseryWithSectorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MapNurseryWithSectorDTO save(MapNurseryWithSectorDTO mapNurseryWithSectorDTO) {
        log.debug("Request to save MapNurseryWithSector : {}", mapNurseryWithSectorDTO);
        MapNurseryWithSector mapNurseryWithSector = mapNurseryWithSectorMapper.toEntity(mapNurseryWithSectorDTO);
        mapNurseryWithSector = mapNurseryWithSectorRepository.save(mapNurseryWithSector);
        return mapNurseryWithSectorMapper.toDto(mapNurseryWithSector);
    }

    /**
     * Get all the mapNurseryWithSectors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MapNurseryWithSectorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MapNurseryWithSectors");
        return mapNurseryWithSectorRepository.findAll(pageable)
            .map(mapNurseryWithSectorMapper::toDto);
    }


    /**
     * Get one mapNurseryWithSector by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MapNurseryWithSectorDTO> findOne(Long id) {
        log.debug("Request to get MapNurseryWithSector : {}", id);
        return mapNurseryWithSectorRepository.findById(id)
            .map(mapNurseryWithSectorMapper::toDto);
    }

    /**
     * Delete the mapNurseryWithSector by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MapNurseryWithSector : {}", id);
        mapNurseryWithSectorRepository.deleteById(id);
    }

    /**
     * Get all the nursery mapped record by nurseryId and status.
     *
     * @param nurseryId the nurseryId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MapNurseryWithSectorDTO> findParticularNurseryActiveRecord(Long nurseryId, Integer status) {
        // log.debug("Request to get nursery : {}", nurseryId);
        List<MapNurseryWithSector> list = mapNurseryWithSectorRepository.findByNurseryIdAndStatus(nurseryId, status);
        return mapNurseryWithSectorMapper.toDto(list);
    }

    /**
     * Get all the nursery mapped record by nurseryId.
     *
     * @param nurseryId the nurseryId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MapNurseryWithSectorDTO> findParticularNurseryRecord(Long nurseryId) {
        // log.debug("Request to get Nursery : {}", nurseryId);
        List<MapNurseryWithSector> list = mapNurseryWithSectorRepository.findByNurseryId(nurseryId);
        return mapNurseryWithSectorMapper.toDto(list);
    }
}
