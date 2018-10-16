/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalInchargeCriteria and
                            declared the table fields, data types for ZonalIncharge table.
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
 * Criteria class for the ZonalIncharge entity. This class is used in ZonalInchargeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /zonal-incharges?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ZonalInchargeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter fromDate;

    private LocalDateFilter toDate;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter zonalId;

    /**
     * ZonalInchargeCriteria from ZonalIncharge table
     */
    public ZonalInchargeCriteria() {
    }

    /**
     * To Get the Id from ZonalIncharge table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the ZonalIncharge table.
     * 
     * @param id id of the ZonalIncharge
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from ZonalIncharge table
     * 
     * @return fromDate
     */
    public LocalDateFilter getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the ZonalIncharge table.
     * 
     * @param fromDate fromDate of the ZonalIncharge
     */
    public void setFromDate(LocalDateFilter fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from ZonalIncharge table
     * 
     * @return toDate
     */
    public LocalDateFilter getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the ZonalIncharge table.
     * 
     * @param toDate toDate of the ZonalIncharge
     */
    public void setToDate(LocalDateFilter toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from ZonalIncharge table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the ZonalIncharge table.
     * 
     * @param description description of the ZonalIncharge
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from ZonalIncharge table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the ZonalIncharge table.
     * 
     * @param status status of the ZonalIncharge
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from ZonalIncharge table
     * 
     * @return zonalId
     */
    public LongFilter getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the ZonalIncharge table.
     * 
     * @param zonalIdid zonalId of the ZonalIncharge
     */
    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "ZonalInchargeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fromDate != null ? "fromDate=" + fromDate + ", " : "") +
                (toDate != null ? "toDate=" + toDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
            "}";
    }

}
