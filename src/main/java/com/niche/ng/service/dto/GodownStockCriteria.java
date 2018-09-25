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
 * Criteria class for the GodownStock entity. This class is used in GodownStockResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /godown-stocks?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GodownStockCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter currentQuantity;

    private LongFilter addedQuantity;

    private LongFilter consumedQuantity;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter godownStockDetailsId;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter pickListQuantityTypeId;

    private LongFilter godownId;

    private LongFilter financialYearGodownStockId;

    public GodownStockCriteria() {
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

    public LongFilter getGodownStockDetailsId() {
        return godownStockDetailsId;
    }

    public void setGodownStockDetailsId(LongFilter godownStockDetailsId) {
        this.godownStockDetailsId = godownStockDetailsId;
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

    public LongFilter getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    public void setPickListQuantityTypeId(LongFilter pickListQuantityTypeId) {
        this.pickListQuantityTypeId = pickListQuantityTypeId;
    }

    public LongFilter getGodownId() {
        return godownId;
    }

    public void setGodownId(LongFilter godownId) {
        this.godownId = godownId;
    }

    public LongFilter getFinancialYearGodownStockId() {
        return financialYearGodownStockId;
    }

    public void setFinancialYearGodownStockId(LongFilter financialYearGodownStockId) {
        this.financialYearGodownStockId = financialYearGodownStockId;
    }

    @Override
    public String toString() {
        return "GodownStockCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (currentQuantity != null ? "currentQuantity=" + currentQuantity + ", " : "") +
                (addedQuantity != null ? "addedQuantity=" + addedQuantity + ", " : "") +
                (consumedQuantity != null ? "consumedQuantity=" + consumedQuantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (godownStockDetailsId != null ? "godownStockDetailsId=" + godownStockDetailsId + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (pickListQuantityTypeId != null ? "pickListQuantityTypeId=" + pickListQuantityTypeId + ", " : "") +
                (godownId != null ? "godownId=" + godownId + ", " : "") +
                (financialYearGodownStockId != null ? "financialYearGodownStockId=" + financialYearGodownStockId + ", " : "") +
            "}";
    }

}
