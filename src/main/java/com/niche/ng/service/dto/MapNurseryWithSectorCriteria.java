/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSectorCriteria and
                            declared the table fields, data types for MapNurseryWithSectorC table.
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
 * Criteria class for the MapNurseryWithSector entity. This class is used in MapNurseryWithSectorResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /map-nursery-with-sectors?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MapNurseryWithSectorCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter fromDate;

    private LocalDateFilter toDate;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter nurseryId;

    private LongFilter sectorId;

    public MapNurseryWithSectorCriteria() {
    }

    /**
     * To Get the Id from MapNurseryWithSector table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the MapNurseryWithSector table.
     * 
     * @param id id value of the MapNurseryWithSector
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public LocalDateFilter getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the MapNurseryWithSector table.
     * 
     * @param fromDate fromDate value of the MapNurseryWithSector
     */
    public void setFromDate(LocalDateFilter fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public LocalDateFilter getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the MapNurseryWithSector table.
     * 
     * @param toDate toDate value of the MapNurseryWithSector
     */
    public void setToDate(LocalDateFilter toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the MapNurseryWithSector table.
     * 
     * @param description description value of the MapNurseryWithSector
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the MapNurseryWithSector table.
     * 
     * @param status status value of the MapNurseryWithSector
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public LongFilter getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the MapNurseryWithSector table.
     * 
     * @param nurseryId nurseryId value of the MapNurseryWithSector
     */
    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public LongFilter getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorIdid values for the MapNurseryWithSector table.
     * 
     * @param sectorId sectorId value of the MapNurseryWithSector
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
        return "MapNurseryWithSectorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fromDate != null ? "fromDate=" + fromDate + ", " : "") +
                (toDate != null ? "toDate=" + toDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (sectorId != null ? "sectorId=" + sectorId + ", " : "") +
            "}";
    }

}
