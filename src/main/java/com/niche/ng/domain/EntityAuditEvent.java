/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs EntityAuditEvent  Generation 
 *                       and declared the table fields, data types for 
 *                       EntityAuditEvent table.
 *
 *******************************************************************************/
package com.niche.ng.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * EntityAuditEvent Domain Class
 * 
 * EntityAuditEvent class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "jhi_entity_audit_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntityAuditEvent implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @NotNull
    @Size(max = 255)
    @Column(name = "entity_type", length = 255, nullable = false)
    private String entityType;

    @NotNull
    @Size(max=20)
    @Column(name = "action", length = 20, nullable = false)
    private String action;

    @Lob
    @Column(name = "entity_value")
    private String entityValue;

    @Column(name = "commit_version")
    private Integer commitVersion;

    @Size(max = 100)
    @Column(name = "modified_by", length = 100)
    private String modifiedBy;

    @NotNull
    @Column(name = "modified_date", nullable = false)
    private Instant modifiedDate;
    
    /**
     * To Get the Id from EntityAuditEvent table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the Id from EntityAuditEvent table
     * 
     * @param id id values
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the entityId from EntityAuditEvent table
     * 
     * @return entityId
     */
    public Long getEntityId() {
        return entityId;
    }

    /**
     * To set the entityId from EntityAuditEvent table
     * 
     * @param entityId entityId values
     */
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    /**
     * To Get the entityType from EntityAuditEvent table
     * 
     * @return entityType 
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * To set the entityType from EntityAuditEvent table
     * 
     * @param entityType entityType values
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    /**
     * To Get the action from EntityAuditEvent table
     * 
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * To set the action from EntityAuditEvent table
     * 
     * @param action action values
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * To Get the entityValue from EntityAuditEvent table
     * 
     * @return entityValue
     */
    public String getEntityValue() {
        return entityValue;
    }

    /**
     * To set the entityValue from EntityAuditEvent table
     * 
     * @param entityValue entityValue values
     */
    public void setEntityValue(String entityValue) {
        this.entityValue = entityValue;
    }

    /**
     * To Get the commitVersion from EntityAuditEvent table
     * 
     * @return commitVersion
     */
    public Integer getCommitVersion() {
        return commitVersion;
    }

    /**
     * To set the commitVersion from EntityAuditEvent table
     * 
     * @param commitVersion commitVersion values
     */
    public void setCommitVersion(Integer commitVersion) {
        this.commitVersion = commitVersion;
    }

    /**
     * To Get the modifiedBy from EntityAuditEvent table
     * 
     * @return modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * To set the modifiedBy from EntityAuditEvent table
     * 
     * @param modifiedBy modifiedBy values
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * To Get the modifiedDate from EntityAuditEvent table
     * 
     * @return modifiedDate
     */
    public Instant getModifiedDate() {
        return modifiedDate;
    }

    /**
     * To set the modifiedDate from EntityAuditEvent table
     * 
     * @param modifiedDate modifiedDate values
     */
    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * To check the equals
     * 
     * @return objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityAuditEvent entityAuditEvent = (EntityAuditEvent) o;
        return Objects.equals(id, entityAuditEvent.id);
    }

    /**
     * Hash code for the id
     * 
     * @return objects
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

     /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "EntityAuditEvent{" +
            "id=" + id +
            ", entityId='" + entityId + "'" +
            ", entityType='" + entityType + "'" +
            ", action='" + action + "'" +
            ", entityValue='" + entityValue + "'" +
            ", commitVersion='" + commitVersion + "'" +
            ", modifiedBy='" + modifiedBy + "'" +
            ", modifiedDate='" + modifiedDate + "'" +
            '}';
    }

    

}
