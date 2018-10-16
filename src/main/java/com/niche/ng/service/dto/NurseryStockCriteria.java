package com.niche.ng.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the NurseryStock entity. This class is used in NurseryStockResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-stocks?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryStockCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter currentQuantity;

    private LongFilter addedQuantity;

    private LongFilter consumedQuantity;

    private StringFilter description;

    private IntegerFilter status;

    private IntegerFilter posQuantity;

    private LongFilter nurseryStockDetailsId;

    private LongFilter nurseryId;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter financialYearNurseryStockId;

    private LongFilter pointOfSaleDetailsId;

    public NurseryStockCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(LongFilter currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public LongFilter getAddedQuantity() {
        return addedQuantity;
    }

    public void setAddedQuantity(LongFilter addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public LongFilter getConsumedQuantity() {
        return consumedQuantity;
    }

    public void setConsumedQuantity(LongFilter consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
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

    public IntegerFilter getPosQuantity() {
        return posQuantity;
    }

    public void setPosQuantity(IntegerFilter posQuantity) {
        this.posQuantity = posQuantity;
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

    public LongFilter getFinancialYearNurseryStockId() {
        return financialYearNurseryStockId;
    }

    public void setFinancialYearNurseryStockId(LongFilter financialYearNurseryStockId) {
        this.financialYearNurseryStockId = financialYearNurseryStockId;
    }

    public LongFilter getPointOfSaleDetailsId() {
        return pointOfSaleDetailsId;
    }

    public void setPointOfSaleDetailsId(LongFilter pointOfSaleDetailsId) {
        this.pointOfSaleDetailsId = pointOfSaleDetailsId;
    }

    @Override
    public String toString() {
        return "NurseryStockCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (currentQuantity != null ? "currentQuantity=" + currentQuantity + ", " : "") +
                (addedQuantity != null ? "addedQuantity=" + addedQuantity + ", " : "") +
                (consumedQuantity != null ? "consumedQuantity=" + consumedQuantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (posQuantity != null ? "posQuantity=" + posQuantity + ", " : "") +
                (nurseryStockDetailsId != null ? "nurseryStockDetailsId=" + nurseryStockDetailsId + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (financialYearNurseryStockId != null ? "financialYearNurseryStockId=" + financialYearNurseryStockId + ", " : "") +
                (pointOfSaleDetailsId != null ? "pointOfSaleDetailsId=" + pointOfSaleDetailsId + ", " : "") +
            "}";
    }

}
