/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PersistentAuditEvent Generation.
 *
 *******************************************************************************/
package com.niche.ng.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Persist AuditEvent managed by the Spring Boot actuator.
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
@Entity
@Table(name = "jhi_persistent_audit_event")
public class PersistentAuditEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "event_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String principal;

    @Column(name = "event_date")
    private Instant auditEventDate;

    @Column(name = "event_type")
    private String auditEventType;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "jhi_persistent_audit_evt_data", joinColumns=@JoinColumn(name="event_id"))
    private Map<String, String> data = new HashMap<>();

    /**
     * To Get the Id from persistentAuditEvent table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for persistentAuditEvent table
     *
     * @param id the id in persistentAuditEvent table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the principal from persistentAuditEvent table
     * 
     * @return principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * To Set the principal for persistentAuditEvent table
     *
     * @param principal the principal in persistentAuditEvent table
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * To Get the auditEventDate from persistentAuditEvent table
     * 
     * @return auditEventDate
     */
    public Instant getAuditEventDate() {
        return auditEventDate;
    }

    /**
     * To Set the auditEventDate for persistentAuditEvent table
     *
     * @param auditEventDate the auditEventDate in persistentAuditEvent table
     */
    public void setAuditEventDate(Instant auditEventDate) {
        this.auditEventDate = auditEventDate;
    }

    /**
     * To Get the auditEventType from persistentAuditEvent table
     * 
     * @return auditEventType
     */
    public String getAuditEventType() {
        return auditEventType;
    }

    /**
     * To Set the auditEventType for persistentAuditEvent table
     *
     * @param auditEventType the auditEventType in persistentAuditEvent table
     */
    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    /**
     * To Get the data from persistentAuditEvent table
     * 
     * @return data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * To Set the data for persistentAuditEvent table
     *
     * @param data the data in persistentAuditEvent table
     */
    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
