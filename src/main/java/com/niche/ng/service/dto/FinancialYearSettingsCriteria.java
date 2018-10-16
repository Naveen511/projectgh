/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingsCriteria and
                            declared the table fields, data types for FinancialYearSettings table.
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
 * Criteria class for the FinancialYearSettings entity. This class is used in FinancialYearSettingsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /financial-year-settings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FinancialYearSettingsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter batchName;

    private LocalDateFilter startDate;

    private LocalDateFilter endDate;

    private IntegerFilter status;

    private LongFilter financialYearId;

    private LongFilter zonalId;

    private LongFilter sectorId;

    private LongFilter nurseryId;

    private LongFilter batchId;

    private LongFilter damageId;

    private LongFilter shadeAreaId;

    private LongFilter nurseryStockId;

    private LongFilter nurseryStockDetailsId;

    private LongFilter godownId;

    private LongFilter godownStockId;

    private LongFilter godownStockDetailsId;

    private LongFilter godownPurchaseDetailsId;

    public FinancialYearSettingsCriteria() {
    }

    /**
     * To Get the Id from FinancialYearSettings table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id for FinancialYearSettings table
     * 
     * @param id id value
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To get the batchName from the FinancialYearSettings table.
     * 
     * @return batchName
     */
    public StringFilter getBatchName() {
        return batchName;
    }

    /**
     * To set the batchName values for the FinancialYearSettings table.
     * 
     * @param batchName name of the batch
     */
    public void setBatchName(StringFilter batchName) {
        this.batchName = batchName;
    }
    
    /**
     * To get the startDate values for the FinancialYearSettings table.
     * 
     * @return startDate date of starting
     */
    public LocalDateFilter getStartDate() {
        return startDate;
    }
    /**
     * To set the startDate values for the FinancialYearSettings table.
     * 
     * @param startDate date of starting
     */
    public void setStartDate(LocalDateFilter startDate) {
        this.startDate = startDate;
    }

     /**
     * To get the endDate values for the FinancialYearSettings table.
     * 
     * @return endDate
     */
    public LocalDateFilter getEndDate() {
        return endDate;
    }

    /**
     * To set the endDate values for the FinancialYearSettings table.
     * 
     * @param endDate date of ending
     */
    public void setEndDate(LocalDateFilter endDate) {
        this.endDate = endDate;
    }

    /**
     * To get the status values for the FinancialYearSettings table.
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the FinancialYearSettings table.
     * 
     * @param status status in financial year setting table
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To get the financialYearId values for the FinancialYearSettings table.
     * 
     * @return financialYearId
     */
    public LongFilter getFinancialYearId() {
        return financialYearId;
    }

    /**
     * To set the financialYearId values for the FinancialYearSettings table.
     * 
     * @param financialYearId financialYearId in financial year setting table
     */
    public void setFinancialYearId(LongFilter financialYearId) {
        this.financialYearId = financialYearId;
    }

    /**
     * To get the zonalId values for the FinancialYearSettings table.
     * 
     * @return zonalId
     */
    public LongFilter getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the FinancialYearSettings table.
     * 
     * @param zonalId zonalId in financial year setting table
     */
    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To get the sectorId values for the FinancialYearSettings table.
     * 
     * @return sectorId
     */
    public LongFilter getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId values for the FinancialYearSettings table.
     * 
     * @param sectorId sectorId in financial year setting table
     */
    public void setSectorId(LongFilter sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * To get the nurseryId values for the FinancialYearSettings table.
     * 
     * @return nurseryId
     */
    public LongFilter getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the FinancialYearSettings table.
     * 
     * @param nurseryId nurseryId in financial year setting table
     */
    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To get the batchId values for the FinancialYearSettings table.
     * 
     * @return batchId
     */
    public LongFilter getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the FinancialYearSettings table.
     * 
     * @param batchId batchId in financial year setting table
     */
    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    /**
     * To get the damageId values for the FinancialYearSettings table.
     * 
     * @return damageId
     */
    public LongFilter getDamageId() {
        return damageId;
    }

    /**
     * To set the damageId values for the FinancialYearSettings table.
     * 
     * @param damageId damageId in financial year setting table
     */
    public void setDamageId(LongFilter damageId) {
        this.damageId = damageId;
    }

    /**
     * To get the shadeAreaId values for the FinancialYearSettings table.
     * 
     * @return shadeAreaId
     */
    public LongFilter getShadeAreaId() {
        return shadeAreaId;
    }

    /**
     * To set the shadeAreaId values for the FinancialYearSettings table.
     * 
     * @param shadeAreaId shadeAreaId in financial year setting table
     */
    public void setShadeAreaId(LongFilter shadeAreaId) {
        this.shadeAreaId = shadeAreaId;
    }

    /**
     * To get the nurseryStockId values for the FinancialYearSettings table.
     * 
     * @return nurseryStockId
     */
    public LongFilter getNurseryStockId() {
        return nurseryStockId;
    }

    /**
     * To set the nurseryStockId values for the FinancialYearSettings table.
     * 
     * @param nurseryStockId nurseryStockId in financial year setting table
     */
    public void setNurseryStockId(LongFilter nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    /**
     * To get the nurseryStockDetailsId values for the FinancialYearSettings table.
     * 
     * @return nurseryStockDetailsId
     */
    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    /**
     * To set the nurseryStockDetailsId values for the FinancialYearSettings table.
     * 
     * @param nurseryStockDetailsId nurseryStockDetailsId in financial year setting table
     */
    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    /**
     * To get the godownId values for the FinancialYearSettings table.
     * 
     * @return godownId
     */
    public LongFilter getGodownId() {
        return godownId;
    }

    /**
     * To set the godownId values for the FinancialYearSettings table.
     * 
     * @param godownId godownId in financial year setting table
     */
    public void setGodownId(LongFilter godownId) {
        this.godownId = godownId;
    }

    /**
     * To get the godownStockId values for the FinancialYearSettings table.
     * 
     * @return godownStockId
     */
    public LongFilter getGodownStockId() {
        return godownStockId;
    }

    /**
     * To set the godownStockId values for the FinancialYearSettings table.
     * 
     * @param godownStockId godownStockId in financial year setting table
     */
    public void setGodownStockId(LongFilter godownStockId) {
        this.godownStockId = godownStockId;
    }

    /**
     * To get the godownStockDetailsId values for the FinancialYearSettings table.
     * 
     * @return godownStockDetailsId
     */
    public LongFilter getGodownStockDetailsId() {
        return godownStockDetailsId;
    }

    /**
     * To set the godownStockDetailsId values for the FinancialYearSettings table.
     * 
     * @param godownStockDetailsId godownStockDetailsId in financial year setting table
     */
    public void setGodownStockDetailsId(LongFilter godownStockDetailsId) {
        this.godownStockDetailsId = godownStockDetailsId;
    }

    /**
     * To get the godownPurchaseDetailsId values for the FinancialYearSettings table.
     * 
     * @return godownPurchaseDetailsId
     */
    public LongFilter getGodownPurchaseDetailsId() {
        return godownPurchaseDetailsId;
    }

    /**
     * To set the godownPurchaseDetailsId values for the FinancialYearSettings table.
     * 
     * @param godownPurchaseDetailsId godownPurchaseDetailsId in financial year setting table
     */
    public void setGodownPurchaseDetailsId(LongFilter godownPurchaseDetailsId) {
        this.godownPurchaseDetailsId = godownPurchaseDetailsId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "FinancialYearSettingsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (batchName != null ? "batchName=" + batchName + ", " : "") +
                (startDate != null ? "startDate=" + startDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (financialYearId != null ? "financialYearId=" + financialYearId + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
                (sectorId != null ? "sectorId=" + sectorId + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (damageId != null ? "damageId=" + damageId + ", " : "") +
                (shadeAreaId != null ? "shadeAreaId=" + shadeAreaId + ", " : "") +
                (nurseryStockId != null ? "nurseryStockId=" + nurseryStockId + ", " : "") +
                (nurseryStockDetailsId != null ? "nurseryStockDetailsId=" + nurseryStockDetailsId + ", " : "") +
                (godownId != null ? "godownId=" + godownId + ", " : "") +
                (godownStockId != null ? "godownStockId=" + godownStockId + ", " : "") +
                (godownStockDetailsId != null ? "godownStockDetailsId=" + godownStockDetailsId + ", " : "") +
                (godownPurchaseDetailsId != null ? "godownPurchaseDetailsId=" + godownPurchaseDetailsId + ", " : "") +
            "}";
    }

}
