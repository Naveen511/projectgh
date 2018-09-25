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

    private LongFilter batchId;

    private LongFilter nurseryStockId;

    private LongFilter itNurseryId;

    private LongFilter saplingDamageAreaId;

    private LongFilter financialYearStockDetailsId;

    public NurseryStockDetailsCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public LongFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getBatchId() {
        return batchId;
    }

    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    public LongFilter getNurseryStockId() {
        return nurseryStockId;
    }

    public void setNurseryStockId(LongFilter nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    public LongFilter getItNurseryId() {
        return itNurseryId;
    }

    public void setItNurseryId(LongFilter itNurseryId) {
        this.itNurseryId = itNurseryId;
    }

    public LongFilter getSaplingDamageAreaId() {
        return saplingDamageAreaId;
    }

    public void setSaplingDamageAreaId(LongFilter saplingDamageAreaId) {
        this.saplingDamageAreaId = saplingDamageAreaId;
    }

    public LongFilter getFinancialYearStockDetailsId() {
        return financialYearStockDetailsId;
    }

    public void setFinancialYearStockDetailsId(LongFilter financialYearStockDetailsId) {
        this.financialYearStockDetailsId = financialYearStockDetailsId;
    }

    @Override
    public String toString() {
        return "NurseryStockDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (nurseryStockId != null ? "nurseryStockId=" + nurseryStockId + ", " : "") +
                (itNurseryId != null ? "itNurseryId=" + itNurseryId + ", " : "") +
                (saplingDamageAreaId != null ? "saplingDamageAreaId=" + saplingDamageAreaId + ", " : "") +
                (financialYearStockDetailsId != null ? "financialYearStockDetailsId=" + financialYearStockDetailsId + ", " : "") +
            "}";
    }

}
