/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapSectorWithZonalCriteria and
                            declared the table fields, data types for MapSectorWithZonal table.
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
 * Criteria class for the MapSectorWithZonal entity. This class is used in MapSectorWithZonalResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /map-sector-with-zonals?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MapSectorWithZonalCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter fromDate;

    private LocalDateFilter toDate;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter zonalId;

    private LongFilter sectorId;

    /**
     * MapSectorWithZonalCriteria from MapSectorWithZonal table
     */
    public MapSectorWithZonalCriteria() {
    }

    /**
     * To Get the Id from MapSectorWithZonal table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the MapSectorWithZonal table.
     * 
     * @param id id value of the MapSectorWithZonal
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from MapSectorWithZonal table
     * 
     * @return fromDate
     */
    public LocalDateFilter getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the MapSectorWithZonal table.
     * 
     * @param fromDate fromDate value of the MapSectorWithZonal
     */
    public void setFromDate(LocalDateFilter fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from MapSectorWithZonal table
     * 
     * @return toDate
     */
    public LocalDateFilter getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the MapSectorWithZonal table.
     * 
     * @param toDate toDate value of the MapSectorWithZonal
     */
    public void setToDate(LocalDateFilter toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from MapSectorWithZonal table
     * 
     * @return idescriptiond
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the MapSectorWithZonal table.
     * 
     * @param description description value of the MapSectorWithZonal
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from MapSectorWithZonal table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the MapSectorWithZonal table.
     * 
     * @param status status value of the MapSectorWithZonal
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from MapSectorWithZonal table
     * 
     * @return zonalId
     */
    public LongFilter getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the MapSectorWithZonal table.
     * 
     * @param zonalId zonalId value of the MapSectorWithZonal
     */
    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the sectorId from MapSectorWithZonal table
     * 
     * @return sectorId
     */
    public LongFilter getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId values for the MapSectorWithZonal table.
     * 
     * @param sectorId sectorId value of the MapSectorWithZonal
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
        return "MapSectorWithZonalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fromDate != null ? "fromDate=" + fromDate + ", " : "") +
                (toDate != null ? "toDate=" + toDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
                (sectorId != null ? "sectorId=" + sectorId + ", " : "") +
            "}";
    }

}
