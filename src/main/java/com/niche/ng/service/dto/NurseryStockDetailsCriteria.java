/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsCriteria and
                            declared the table fields, data types for NurseryStock table.
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
 * Criteria class for the NurseryStockDetails entity. This class is used in NurseryStockDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-stock-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryStockDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter date;

    private LongFilter quantity;

    private StringFilter description;

    private IntegerFilter status;
    
    private IntegerFilter itStatus;

    private LongFilter batchId;

    private LongFilter nurseryStockId;

    private LongFilter itNurseryId;

    private LongFilter saplingDamageAreaId;

    private LongFilter financialYearStockDetailsId;

    private LongFilter fromNurseryStockDetailsId;

    /**
     * NurseryStockDetailsCriteria from NurseryStockDetails table
     */
    public NurseryStockDetailsCriteria() {
    }

    /**
     * To Get the Id from NurseryStockDetails table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryStockDetails table.
     * 
     * @param id id of the NurseryStockDetails
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the date from NurseryStockDetails table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the NurseryStockDetails table.
     * 
     * @param date date of the NurseryStockDetails
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the quantity from NurseryStockDetails table
     * 
     * @return quantity
     */
    public LongFilter getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the NurseryStockDetails table.
     * 
     * @param quantity quantity of the NurseryStockDetails
     */
    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the description from NurseryStockDetails table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryStockDetails table.
     * 
     * @param description description of the NurseryStockDetails
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryStockDetails table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryStockDetails table.
     * 
     * @param status status of the NurseryStockDetails
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the itStatus from NurseryStockDetails table
     * 
     * @return itStatus
     */
    public IntegerFilter getItStatus() {
        return itStatus;
    }

    /**
     * To set the itStatus values for the NurseryStockDetails table.
     * 
     * @param itStatus itStatus of the NurseryStockDetails
     */
    public void setItStatus(IntegerFilter itStatus) {
        this.itStatus = itStatus;
    }

    /**
     * To Get the batchId from NurseryStockDetails table
     * 
     * @return batchId
     */
    public LongFilter getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the NurseryStockDetails table.
     * 
     * @param batchId batchId of the NurseryStockDetails
     */
    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the nurseryStockId from NurseryStockDetails table
     * 
     * @return nurseryStockId
     */
    public LongFilter getNurseryStockId() {
        return nurseryStockId;
    }

    /**
     * To set the nurseryStockId values for the NurseryStockDetails table.
     * 
     * @param nurseryStockId nurseryStockId of the NurseryStockDetails
     */
    public void setNurseryStockId(LongFilter nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    /**
     * To Get the itNurseryId from NurseryStockDetails table
     * 
     * @return itNurseryId
     */
    public LongFilter getItNurseryId() {
        return itNurseryId;
    }

    /**
     * To set the itNurseryId values for the NurseryStockDetails table.
     * 
     * @param itNurseryId itNurseryId of the NurseryStockDetails
     */
    public void setItNurseryId(LongFilter itNurseryId) {
        this.itNurseryId = itNurseryId;
    }

    /**
     * To Get the saplingDamageAreaId from NurseryStockDetails table
     * 
     * @return saplingDamageAreaId
     */
    public LongFilter getSaplingDamageAreaId() {
        return saplingDamageAreaId;
    }

    /**
     * To set the saplingDamageAreaId values for the NurseryStockDetails table.
     * 
     * @param saplingDamageAreaId saplingDamageAreaId of the NurseryStockDetails
     */
    public void setSaplingDamageAreaId(LongFilter saplingDamageAreaId) {
        this.saplingDamageAreaId = saplingDamageAreaId;
    }

    /**
     * To Get the financialYearStockDetailsId from NurseryStockDetails table
     * 
     * @return financialYearStockDetailsId
     */
    public LongFilter getFinancialYearStockDetailsId() {
        return financialYearStockDetailsId;
    }
    
    /**
     * To set the financialYearStockDetailsId values for the NurseryStockDetails table.
     * 
     * @param financialYearStockDetailsId financialYearStockDetailsId of the NurseryStockDetails
     */
    public void setFinancialYearStockDetailsId(LongFilter financialYearStockDetailsId) {
        this.financialYearStockDetailsId = financialYearStockDetailsId;
    }

    /**
     * To Get the fromNurseryStockDetailsId from NurseryStockDetails table
     * 
     * @return fromNurseryStockDetailsId
     */
    public LongFilter getFromNurseryStockDetailsId() {
        return fromNurseryStockDetailsId;
    }

    /**
     * To set the fromNurseryStockDetailsId values for the NurseryStockDetails table.
     * 
     * @param fromNurseryStockDetailsId fromNurseryStockDetailsId of the NurseryStockDetails
     */
    public void setFromNurseryStockDetailsId(LongFilter fromNurseryStockDetailsId) {
        this.fromNurseryStockDetailsId = fromNurseryStockDetailsId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "NurseryStockDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (itStatus != null ? "itStatus=" + itStatus + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (nurseryStockId != null ? "nurseryStockId=" + nurseryStockId + ", " : "") +
                (itNurseryId != null ? "itNurseryId=" + itNurseryId + ", " : "") +
                (saplingDamageAreaId != null ? "saplingDamageAreaId=" + saplingDamageAreaId + ", " : "") +
                (financialYearStockDetailsId != null ? "financialYearStockDetailsId=" + financialYearStockDetailsId + ", " : "") +
                (fromNurseryStockDetailsId != null ? "fromNurseryStockDetailsId=" + fromNurseryStockDetailsId + ", " : "") +
            "}";
    }

}
