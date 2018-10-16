/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PersistenceAuditEventRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.PersistentAuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data JPA repository for the PersistentAuditEvent entity.
 *
 * PersistenceAuditEventRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
public interface PersistenceAuditEventRepository extends JpaRepository<PersistentAuditEvent, Long> {

    // Query the list of persistentAuditEvent using a field principal.
    List<PersistentAuditEvent> findByPrincipal(String principal);

    // Query the list of persistentAuditEvent using a field after.
    List<PersistentAuditEvent> findByAuditEventDateAfter(Instant after);

    // Query the list of persistentAuditEvent using a field principal and after.
    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal, Instant after);

    // Query the list of persistentAuditEvent using a field principle and type.
    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principle, Instant after, String type);

    // Query the list of persistentAuditEvent between 2 date using a field date.
    Page<PersistentAuditEvent> findAllByAuditEventDateBetween(Instant fromDate, Instant toDate, Pageable pageable);
}
