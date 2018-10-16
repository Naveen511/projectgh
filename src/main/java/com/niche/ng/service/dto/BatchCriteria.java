/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchCriteria and
                            declared the table fields, data types for batch table.
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
 * Criteria class for the Batch entity. This class is used in BatchResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /batches?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BatchCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter batchNo;

    private StringFilter batchName;

    private LongFilter quantity;

    private IntegerFilter showingType;

    private LocalDateFilter sowingDate;

    private LocalDateFilter closedDate;

    private IntegerFilter round;

    private StringFilter remarks;

    private IntegerFilter status;

    private StringFilter noOfKg;

    private LongFilter damagesId;

    private LongFilter shadeAreasId;

    private LongFilter nurseryStockDetailsId;

    private LongFilter nurseryId;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter quantityTypeId;

    private LongFilter motherBedId;

    private LongFilter financialYearBatchId;

    private LongFilter batchQuantityId;

    public BatchCriteria() {
    }

    /**
     * To Get the Id of the batch table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id of the batch table
     *
     * @param id : Integer
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the batch no from the batch table
     *
     * @return batchNo
     */
    public StringFilter getBatchNo() {
        return batchNo;
    }

    /**
     * To set the batch no from the batch table
     * 
     * @param batchNo : String
     */
    public void setBatchNo(StringFilter batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * To Get the batch name from the batch table
     * 
     * @return batchName
     */
    public StringFilter getBatchName() {
        return batchName;
    }

    /**
     * To set the batch name from the batch table
     * 
     * @param batchName batch name of a particular batch
     */
    public void setBatchName(StringFilter batchName) {
        this.batchName = batchName;
    }

    /**
     * To get the quantity from the batch table
     * 
     * @return quantity
     */
    public LongFilter getQuantity() {
        return quantity;
    }

    /**
     * To set the batch quantity from the batch table
     *
     * @param quantity quantity of a particular batch
     */
    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To get the showingType from the batch table
     * 
     * @return showingType type of sown
     */
    public IntegerFilter getShowingType() {
        return showingType;
    }

    /**
     * To set the sowing type  from the batch table
     * 
     * @param showingType type of sown
     */
    public void setShowingType(IntegerFilter showingType) {
        this.showingType = showingType;
    }

    /**
     * To get the sowingDate from the batch table
     * 
     * @return sowingDate
     */
    public LocalDateFilter getSowingDate() {
        return sowingDate;
    }

    /**
     * To set the sowing date from the batch table.
     * 
     * @param sowingDate date sown
     */
    public void setSowingDate(LocalDateFilter sowingDate) {
        this.sowingDate = sowingDate;
    }

    /**
     * To get the closedDate from the batch table
     * 
     * @return closedDate
     */
    public LocalDateFilter getClosedDate() {
        return closedDate;
    }

    /**
     * To set the closed date from the batch table.
     * 
     * @param closedDate date of closing the batch
     */
    public void setClosedDate(LocalDateFilter closedDate) {
        this.closedDate = closedDate;
    }

    /**
     * To get the closedDate from the batch table
     * 
     * @return round
     */
    public IntegerFilter getRound() {
        return round;
    }

    /**
     * To set the round for the batch table.
     * 
     * @param round round in batch
     */
    public void setRound(IntegerFilter round) {
        this.round = round;
    }

    /**
     * To get the remarks from the batch table
     * 
     * @return remarks
     */
    public StringFilter getRemarks() {
        return remarks;
    }

    /**
     * To set the remarks for the batch table.
     * 
     * @param remarks remarks in batch
     */
    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    /**
     * To get the status from the batch table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status for the batch table.
     * 
     * @param status status in batch
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To get the noOfKg from the batch table
     * 
     * @return noOfKg
     */
    public StringFilter getNoOfKg() {
        return noOfKg;
    }

    /**
     * To set the noOfKg for the batch table.
     * 
     * @param noOfKg the noOfKg in batch
     */
    public void setNoOfKg(StringFilter noOfKg) {
        this.noOfKg = noOfKg;
    }

    /**
     * To get the damagesId from the batch table
     * 
     * @return damagesId
     */
    public LongFilter getDamagesId() {
        return damagesId;
    }

    /**
     * To set the damagesId for the batch table.
     * 
     * @param damagesId damagesId in batch
     */
    public void setDamagesId(LongFilter damagesId) {
        this.damagesId = damagesId;
    }

    /**
     * To get the shadeAreasId from the batch table
     * 
     * @return shadeAreasId
     */
    public LongFilter getShadeAreasId() {
        return shadeAreasId;
    }

    /**
     * To set the shadeAreasId for the batch table.
     * 
     * @param shadeAreasId shadeAreasId in batch
     */
    public void setShadeAreasId(LongFilter shadeAreasId) {
        this.shadeAreasId = shadeAreasId;
    }

    /**
     * To get the nurseryStockDetailsId from the batch table
     * 
     * @return nurseryStockDetailsId
     */
    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    /**
     * To set the nurseryStockDetailsId for the batch table.
     * 
     * @param nurseryStockDetailsId nurseryStockDetailsId in batch
     */
    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    /**
     * To get the nurseryId from the batch table
     * 
     * @return nurseryId
     */
    public LongFilter getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId for the batch table.
     * 
     * @param nurseryId nurseryId in batch
     */
    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To get the pickListVarietyId from the batch table
     * 
     * @return pickListVarietyId
     */
    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId for the batch table.
     * 
     * @param pickListVarietyId pickListVarietyId in batch
     */
    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    /**
     * To get the pickListCategoryId from the batch table
     * 
     * @return pickListCategoryId
     */
    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId for the batch table.
     * 
     * @param pickListCategoryId pickListCategoryId in batch
     */
    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    /**
     * To get the quantityTypeId from the batch table
     * 
     * @return quantityTypeId
     */
    public LongFilter getQuantityTypeId() {
        return quantityTypeId;
    }

    /**
     * To set the quantityTypeId for the batch table.
     * 
     * @param quantityTypeId quantityTypeId in batch
     */
    public void setQuantityTypeId(LongFilter quantityTypeId) {
        this.quantityTypeId = quantityTypeId;
    }

    /**
     * To get the motherBedId from the batch table
     * 
     * @return motherBedId
     */
    public LongFilter getMotherBedId() {
        return motherBedId;
    }

    /**
     * To set the motherBedId for the batch table.
     * 
     * @param motherBedId motherBedId in batch
     */
    public void setMotherBedId(LongFilter motherBedId) {
        this.motherBedId = motherBedId;
    }

    /**
     * To get the financialYearBatchId from the batch table
     * 
     * @return financialYearBatchId
     */
    public LongFilter getFinancialYearBatchId() {
        return financialYearBatchId;
    }

    /**
     * To set the financialYearBatchId for the batch table.
     * 
     * @param financialYearBatchId financialYearBatchId in batch
     */
    public void setFinancialYearBatchId(LongFilter financialYearBatchId) {
        this.financialYearBatchId = financialYearBatchId;
    }

    /**
     * To get the batchQuantityId from the batch table
     * 
     * @return batchQuantityId
     */
    public LongFilter getBatchQuantityId() {
        return batchQuantityId;
    }

    /**
     * To set the batchQuantityId for the batch table.
     * 
     * @param batchQuantityId batchQuantityId in batch
     */
    public void setBatchQuantityId(LongFilter batchQuantityId) {
        this.batchQuantityId = batchQuantityId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "BatchCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (batchNo != null ? "batchNo=" + batchNo + ", " : "") +
                (batchName != null ? "batchName=" + batchName + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (showingType != null ? "showingType=" + showingType + ", " : "") +
                (sowingDate != null ? "sowingDate=" + sowingDate + ", " : "") +
                (closedDate != null ? "closedDate=" + closedDate + ", " : "") +
                (round != null ? "round=" + round + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (noOfKg != null ? "noOfKg=" + noOfKg + ", " : "") +
                (damagesId != null ? "damagesId=" + damagesId + ", " : "") +
                (shadeAreasId != null ? "shadeAreasId=" + shadeAreasId + ", " : "") +
                (nurseryStockDetailsId != null ? "nurseryStockDetailsId=" + nurseryStockDetailsId + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (quantityTypeId != null ? "quantityTypeId=" + quantityTypeId + ", " : "") +
                (motherBedId != null ? "motherBedId=" + motherBedId + ", " : "") +
                (financialYearBatchId != null ? "financialYearBatchId=" + financialYearBatchId + ", " : "") +
                (batchQuantityId != null ? "batchQuantityId=" + batchQuantityId + ", " : "") +
            "}";
    }

}
