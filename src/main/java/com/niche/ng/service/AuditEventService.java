/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/20
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs AuditEventService
 *
 *******************************************************************************/
package com.niche.ng.service;

import com.niche.ng.config.audit.AuditEventConverter;
import com.niche.ng.repository.PersistenceAuditEventRepository;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Service for managing audit events.
 * <p>
 * This is the default implementation to support SpringBoot Actuator AuditEventRepository
 */
@Service
@Transactional
public class AuditEventService {

    private final PersistenceAuditEventRepository persistenceAuditEventRepository;

    private final AuditEventConverter auditEventConverter;

    /**
     * Create a constractor of a class
     *
     * @param persistenceAuditEventRepository the auditEventRepository to access database
     * @param auditEventConverter to map the database value
     */
    public AuditEventService(
        PersistenceAuditEventRepository persistenceAuditEventRepository,
        AuditEventConverter auditEventConverter) {

        this.persistenceAuditEventRepository = persistenceAuditEventRepository;
        this.auditEventConverter = auditEventConverter;
    }

    /**
     * Get all the auditEvent
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<AuditEvent> findAll(Pageable pageable) {
        return persistenceAuditEventRepository.findAll(pageable)
            .map(auditEventConverter::convertToAuditEvent);
    }

    /**
     * Get all the auditEvent based on date between from and to date.
     *
     * @param fromDate the fromDate of the entity
     * @param toDate   the toDate of the entity
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<AuditEvent> findByDates(Instant fromDate, Instant toDate, Pageable pageable) {
        return persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate, pageable)
            .map(auditEventConverter::convertToAuditEvent);
    }

    /**
     * Load particular entity audit record using id
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<AuditEvent> find(Long id) {
        return Optional.ofNullable(persistenceAuditEventRepository.findById(id))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(auditEventConverter::convertToAuditEvent);
    }
}
