/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityCriteria and
                            declared the table fields, data types for BatchQuantity table.
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
 * Criteria class for the BatchQuantity entity. This class is used in BatchQuantityResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /batch-quantities?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BatchQuantityCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter quantity;

    private LocalDateFilter date;

    private StringFilter remarks;

    private IntegerFilter status;

    private LongFilter batchId;

    /**
     * BatchQuantityCriteria for batch quantity table
     *
     */
    public BatchQuantityCriteria() {
    }

    /**
     * To Get the Id from batch quantity table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id for batch quantity table
     *
     * @return id
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the quantity from batch quantity table
     * 
     * @return id
     */
    public IntegerFilter getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for batch quantity table
     *
     * @param quantity quantity in batch quantity table
     */
    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from batch quantity table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To Set the date for batch quantity table
     *
     * @param date date in batch quantity table
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the remarks from batch quantity table
     * 
     * @return remarks
     */
    public StringFilter getRemarks() {
        return remarks;
    }

    /**
     * To Set the remarks for batch quantity table
     *
     * @param remarks remarks in batch quantity table
     */
    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    /**
     * To Get the status from batch quantity table
     *
     * @param status status in batch quantity table
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To Set the status for batch quantity table
     * 
     * @param status status in batch quantity table
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the batchId from batchId quantity table
     * 
     * @return batchId
     */
    public LongFilter getBatchId() {
        return batchId;
    }

    /**
     * To Set the batchId for batch quantity table
     * 
     * @param batchId batchId in batch quantity table
     */
    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "BatchQuantityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
            "}";
    }

}
