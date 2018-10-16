/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs AbstractAuditingDTO and declared the table 
 *                        fields, data types for AbstractAuditing table.
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.ReadOnlyProperty;

/**
 * Base abstract class for DTO which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
public abstract class AbstractAuditingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ReadOnlyProperty
    private String createdBy;

    @ReadOnlyProperty
    private Instant createdDate = Instant.now();

    private String lastModifiedBy;

    private Instant lastModifiedDate = Instant.now();

    /**
     * To Get the createdBy of the AbstractAuditing table
     * 
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * To Set the createdBy of the AbstractAuditing table
     *
     * @param createdBy : createdBy 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * To Get the createdDate of the AbstractAuditing table
     * 
     * @return createdDate
     */
    public Instant getCreatedDate() {
        return createdDate;
    }

    /**
     * To Set the createdBy of the AbstractAuditing table
     *
     * @param createdDate : createdDate
     */
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * To Get the lastModifiedBy of the AbstractAuditing table
     * 
     * @return lastModifiedBy
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * To Set the createdBy of the AbstractAuditing table
     *
     * @param lastModifiedDate : lastModifiedDate
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * To Get the lastModifiedDate of the AbstractAuditing table
     * 
     * @return lastModifiedDate
     */
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * To Set the lastModifiedDate of the AbstractAuditing table
     *
     * @param lastModifiedDate : lastModifiedDate
     */
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
