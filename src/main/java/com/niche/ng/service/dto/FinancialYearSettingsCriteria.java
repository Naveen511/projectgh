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

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getBatchName() {
        return batchName;
    }

    public void setBatchName(StringFilter batchName) {
        this.batchName = batchName;
    }

    public LocalDateFilter getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateFilter startDate) {
        this.startDate = startDate;
    }

    public LocalDateFilter getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateFilter endDate) {
        this.endDate = endDate;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getFinancialYearId() {
        return financialYearId;
    }

    public void setFinancialYearId(LongFilter financialYearId) {
        this.financialYearId = financialYearId;
    }

    public LongFilter getZonalId() {
        return zonalId;
    }

    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    public LongFilter getSectorId() {
        return sectorId;
    }

    public void setSectorId(LongFilter sectorId) {
        this.sectorId = sectorId;
    }

    public LongFilter getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    public LongFilter getBatchId() {
        return batchId;
    }

    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    public LongFilter getDamageId() {
        return damageId;
    }

    public void setDamageId(LongFilter damageId) {
        this.damageId = damageId;
    }

    public LongFilter getShadeAreaId() {
        return shadeAreaId;
    }

    public void setShadeAreaId(LongFilter shadeAreaId) {
        this.shadeAreaId = shadeAreaId;
    }

    public LongFilter getNurseryStockId() {
        return nurseryStockId;
    }

    public void setNurseryStockId(LongFilter nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    public LongFilter getGodownId() {
        return godownId;
    }

    public void setGodownId(LongFilter godownId) {
        this.godownId = godownId;
    }

    public LongFilter getGodownStockId() {
        return godownStockId;
    }

    public void setGodownStockId(LongFilter godownStockId) {
        this.godownStockId = godownStockId;
    }

    public LongFilter getGodownStockDetailsId() {
        return godownStockDetailsId;
    }

    public void setGodownStockDetailsId(LongFilter godownStockDetailsId) {
        this.godownStockDetailsId = godownStockDetailsId;
    }

    public LongFilter getGodownPurchaseDetailsId() {
        return godownPurchaseDetailsId;
    }

    public void setGodownPurchaseDetailsId(LongFilter godownPurchaseDetailsId) {
        this.godownPurchaseDetailsId = godownPurchaseDetailsId;
    }

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
