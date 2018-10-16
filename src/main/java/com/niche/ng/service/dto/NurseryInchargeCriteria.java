/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInchargeCriteria and
                            declared the table fields, data types for NurseryIncharge table.
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
 * Criteria class for the NurseryIncharge entity. This class is used in NurseryInchargeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-incharges?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryInchargeCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter fromDate;

    private LocalDateFilter toDate;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter nurseryId;

    /**
     * NurseryInchargeCriteria from NurseryIncharge table
     */
    public NurseryInchargeCriteria() {
    }

    /**
     * To Get the Id from NurseryIncharge table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryIncharge table.
     * 
     * @param id id value of the NurseryIncharge
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from NurseryIncharge table
     * 
     * @return fromDate
     */
    public LocalDateFilter getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the NurseryIncharge table.
     * 
     * @param fromDate fromDate value of the NurseryIncharge
     */
    public void setFromDate(LocalDateFilter fromDate) {
        this.fromDate = fromDate;
    } 

    /**
     * To Get the toDate from NurseryIncharge table
     * 
     * @return toDate
     */
    public LocalDateFilter getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the NurseryIncharge table.
     * 
     * @param toDate toDate value of the NurseryIncharge
     */
    public void setToDate(LocalDateFilter toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from NurseryIncharge table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryIncharge table.
     * 
     * @param description description value of the NurseryIncharge
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryIncharge table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryIncharge table.
     * 
     * @param status status value of the NurseryIncharge
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the nurseryId from NurseryIncharge table
     * 
     * @return nurseryId
     */
    public LongFilter getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the NurseryIncharge table.
     * 
     * @param nurseryId nurseryId value of the NurseryIncharge
     */
    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "NurseryInchargeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fromDate != null ? "fromDate=" + fromDate + ", " : "") +
                (toDate != null ? "toDate=" + toDate + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
            "}";
    }

}
