/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOhCriteria and
                            declared the table fields, data types for MapZonalWithOh table.
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
 * Criteria class for the MapZonalWithOh entity. This class is used in MapZonalWithOhResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /map-zonal-with-ohs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MapZonalWithOhCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter fromDate;

    private LocalDateFilter toDate;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter zonalId;

    private LongFilter operationalHeadId;

    /**
     * MapZonalWithOhCriteria from MapZonalWithOh table
     */
    public MapZonalWithOhCriteria() {
    }

    /**
     * To Get the Id from MapZonalWithOh table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the MapZonalWithOh table.
     * 
     * @param id id value of the MapZonalWithOh
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from MapZonalWithOh table
     * 
     * @return fromDate
     */
    public LocalDateFilter getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the MapZonalWithOh table.
     * 
     * @param fromDate fromDate value of the MapZonalWithOh
     */
    public void setFromDate(LocalDateFilter fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from MapZonalWithOh table
     * 
     * @return toDate
     */
    public LocalDateFilter getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the MapZonalWithOh table.
     * 
     * @param toDate toDate value of the MapZonalWithOh
     */
    public void setToDate(LocalDateFilter toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from MapZonalWithOh table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the MapZonalWithOh table.
     * 
     * @param description description value of the MapZonalWithOh
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from MapZonalWithOh table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the MapZonalWithOh table.
     * 
     * @param status status value of the MapZonalWithOh
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from MapZonalWithOh table
     * 
     * @return zonalId
     */
    public LongFilter getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the MapZonalWithOh table.
     * 
     * @param zonalId zonalId value of the MapZonalWithOh
     */
    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the operationalHeadId from MapZonalWithOh table
     * 
     * @return operationalHeadId
     */
    public LongFilter getOperationalHeadId() {
        return operationalHeadId;
    }

    /**
     * To set the operationalHeadId values for the MapZonalWithOh table.
     * 
     * @param operationalHeadId operationalHeadId value of the MapZonalWithOh
     */
    public void setOperationalHeadId(LongFilter operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "MapZonalWithOhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fromDate != null ? "fromDate=" + fromDate + ", " : "") +
                (toDate != null ? "toDate=" + toDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
                (operationalHeadId != null ? "operationalHeadId=" + operationalHeadId + ", " : "") +
            "}";
    }

}
