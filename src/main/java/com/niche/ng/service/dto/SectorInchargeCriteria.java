/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeCriteria and
                            declared the table fields, data types for SectorIncharge table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the SectorIncharge entity. This class is used in SectorInchargeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sector-incharges?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SectorInchargeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter fromDate;

    private LocalDateFilter toDate;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter sectorId;

    /**
     * SectorInchargeCriteria from SectorIncharge table
     */
    public SectorInchargeCriteria() {
    }

    /**
     * To Get the Id from SectorIncharge table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the SectorIncharge table.
     * 
     * @param id id of the SectorIncharge
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from SectorIncharge table
     * 
     * @return fromDate
     */
    public LocalDateFilter getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the SectorIncharge table.
     * 
     * @param fromDate fromDate of the SectorIncharge
     */
    public void setFromDate(LocalDateFilter fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from SectorIncharge table
     * 
     * @return toDate
     */
    public LocalDateFilter getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the SectorIncharge table.
     * 
     * @param toDate toDate of the SectorIncharge
     */
    public void setToDate(LocalDateFilter toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from SectorIncharge table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the SectorIncharge table.
     * 
     * @param description description of the SectorIncharge
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from SectorIncharge table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the SectorIncharge table.
     * 
     * @param status status of the SectorIncharge
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the sectorId from SectorIncharge table
     * 
     * @return sectorId
     */
    public LongFilter getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId values for the SectorIncharge table.
     * 
     * @param sectorId sectorId of the SectorIncharge
     */
    public void setSectorId(LongFilter sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "SectorInchargeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fromDate != null ? "fromDate=" + fromDate + ", " : "") +
                (toDate != null ? "toDate=" + toDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (sectorId != null ? "sectorId=" + sectorId + ", " : "") +
            "}";
    }

}
