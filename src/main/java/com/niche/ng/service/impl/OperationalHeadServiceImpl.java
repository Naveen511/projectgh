package com.niche.ng.service.impl;

import com.niche.ng.service.OperationalHeadService;
import com.niche.ng.domain.OperationalHead;
import com.niche.ng.repository.OperationalHeadRepository;
import com.niche.ng.service.dto.OperationalHeadDTO;
import com.niche.ng.service.mapper.OperationalHeadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing OperationalHead.
 */
@Service
@Transactional
public class OperationalHeadServiceImpl implements OperationalHeadService {

    private final Logger log = LoggerFactory.getLogger(OperationalHeadServiceImpl.class);

    private final OperationalHeadRepository operationalHeadRepository;

    private final OperationalHeadMapper operationalHeadMapper;

    public OperationalHeadServiceImpl(OperationalHeadRepository operationalHeadRepository, OperationalHeadMapper operationalHeadMapper) {
        this.operationalHeadRepository = operationalHeadRepository;
        this.operationalHeadMapper = operationalHeadMapper;
    }

    /**
     * Save a operationalHead.
     *
     * @param operationalHeadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OperationalHeadDTO save(OperationalHeadDTO operationalHeadDTO) {
        log.debug("Request to save OperationalHead : {}", operationalHeadDTO);
        OperationalHead operationalHead = operationalHeadMapper.toEntity(operationalHeadDTO);
        operationalHead = operationalHeadRepository.save(operationalHead);
        return operationalHeadMapper.toDto(operationalHead);
    }

    /**
     * Get all the operationalHeads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OperationalHeadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperationalHeads");
        return operationalHeadRepository.findAll(pageable)
            .map(operationalHeadMapper::toDto);
    }


    /**
     * Get one operationalHead by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OperationalHeadDTO> findOne(Long id) {
        log.debug("Request to get OperationalHead : {}", id);
        return operationalHeadRepository.findById(id)
            .map(operationalHeadMapper::toDto);
    }

    /**
     * Delete the operationalHead by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperationalHead : {}", id);
        operationalHeadRepository.deleteById(id);
    }

    /**
     * Get all the head office by status.
     *
     * @param status the status of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OperationalHeadDTO> findParticularStatus(Integer status) {
        log.debug("Request to get headOffice : {}", status);
        List<OperationalHead> list = operationalHeadRepository.findByStatus(status);
        return operationalHeadMapper.toDto(list);
    }

    @Override
    public Long findActiveCount(Integer status) {
        return operationalHeadRepository.countByStatus(status);
    }
    
}
