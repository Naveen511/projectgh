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

    public BatchCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(StringFilter batchNo) {
        this.batchNo = batchNo;
    }

    public StringFilter getBatchName() {
        return batchName;
    }

    public void setBatchName(StringFilter batchName) {
        this.batchName = batchName;
    }

    public LongFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    public IntegerFilter getShowingType() {
        return showingType;
    }

    public void setShowingType(IntegerFilter showingType) {
        this.showingType = showingType;
    }

    public LocalDateFilter getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(LocalDateFilter sowingDate) {
        this.sowingDate = sowingDate;
    }

    public LocalDateFilter getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDateFilter closedDate) {
        this.closedDate = closedDate;
    }

    public IntegerFilter getRound() {
        return round;
    }

    public void setRound(IntegerFilter round) {
        this.round = round;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public StringFilter getNoOfKg() {
        return noOfKg;
    }

    public void setNoOfKg(StringFilter noOfKg) {
        this.noOfKg = noOfKg;
    }

    public LongFilter getDamagesId() {
        return damagesId;
    }

    public void setDamagesId(LongFilter damagesId) {
        this.damagesId = damagesId;
    }

    public LongFilter getShadeAreasId() {
        return shadeAreasId;
    }

    public void setShadeAreasId(LongFilter shadeAreasId) {
        this.shadeAreasId = shadeAreasId;
    }

    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    public LongFilter getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    public LongFilter getQuantityTypeId() {
        return quantityTypeId;
    }

    public void setQuantityTypeId(LongFilter quantityTypeId) {
        this.quantityTypeId = quantityTypeId;
    }

    public LongFilter getMotherBedId() {
        return motherBedId;
    }

    public void setMotherBedId(LongFilter motherBedId) {
        this.motherBedId = motherBedId;
    }

    public LongFilter getFinancialYearBatchId() {
        return financialYearBatchId;
    }

    public void setFinancialYearBatchId(LongFilter financialYearBatchId) {
        this.financialYearBatchId = financialYearBatchId;
    }

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
            "}";
    }

}
