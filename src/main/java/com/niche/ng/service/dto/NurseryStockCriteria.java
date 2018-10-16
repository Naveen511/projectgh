/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockCriteria and
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

    private LongFilter damageQuantity;

    private LongFilter nurseryStockDetailsId;

    private LongFilter nurseryId;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter financialYearNurseryStockId;

    private LongFilter pointOfSaleDetailsId;

    /**
     * NurseryStockCriteria from NurseryStock table
     */
    public NurseryStockCriteria() {
    }

    /**
     * To Get the Id from NurseryStock table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryStock table.
     * 
     * @param id id of the NurseryStock
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from NurseryStock table
     * 
     * @return currentQuantity
     */
    public LongFilter getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values for the NurseryStock table.
     * 
     * @param currentQuantity currentQuantity of the NurseryStock
     */
    public void setCurrentQuantity(LongFilter currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from NurseryStock table
     * 
     * @return addedQuantity
     */
    public LongFilter getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values for the NurseryStock table.
     * 
     * @param addedQuantity addedQuantity of the NurseryStock
     */
    public void setAddedQuantity(LongFilter addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from NurseryStock table
     * 
     * @return consumedQuantity
     */
    public LongFilter getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values for the NurseryStock table.
     * 
     * @param consumedQuantity consumedQuantity of the NurseryStock
     */
    public void setConsumedQuantity(LongFilter consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from NurseryStock table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryStock table.
     * 
     * @param description description of the NurseryStock
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryStock table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryStock table.
     * 
     * @param status status of the NurseryStock
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the posQuantity from NurseryStock table
     * 
     * @return posQuantity
     */
    public IntegerFilter getPosQuantity() {
        return posQuantity;
    }

    /**
     * To set the id values for the NurseryStock table.
     * 
     * @param posQuantity posQuantity of the NurseryStock
     */
    public void setPosQuantity(IntegerFilter posQuantity) {
        this.posQuantity = posQuantity;
    }

    /**
     * To Get the damageQuantity from NurseryStock table
     * 
     * @return damageQuantity
     */
    public LongFilter getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To set the damageQuantity values for the NurseryStock table.
     * 
     * @param damageQuantity damageQuantity of the NurseryStock
     */
    public void setDamageQuantity(LongFilter damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To Get the nurseryStockDetailsId from NurseryStock table
     * 
     * @return nurseryStockDetailsId
     */
    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    /**
     * To set the nurseryStockDetailsId values for the NurseryStock table.
     * 
     * @param nurseryStockDetailsId nurseryStockDetailsId of the NurseryStock
     */
    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    /**
     * To Get the nurseryId from NurseryStock table
     * 
     * @return nurseryId
     */
    public LongFilter getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the NurseryStock table.
     * 
     * @param nurseryId nurseryId of the NurseryStock
     */
    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To Get the pickListVarietyId from NurseryStock table
     * 
     * @return pickListVarietyId
     */
    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the NurseryStock table.
     * 
     * @param id pickListVarietyId of the NurseryStock
     */
    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    /**
     * To Get the pickListCategoryId from NurseryStock table
     * 
     * @return pickListCategoryId
     */
    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the NurseryStock table.
     * 
     * @param pickListCategoryId pickListCategoryId of the NurseryStock
     */
    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    /**
     * To Get the financialYearNurseryStockId from NurseryStock table
     * 
     * @return financialYearNurseryStockId
     */
    public LongFilter getFinancialYearNurseryStockId() {
        return financialYearNurseryStockId;
    }

    /**
     * To set the financialYearNurseryStockId values for the NurseryStock table.
     * 
     * @param financialYearNurseryStockId financialYearNurseryStockId of the NurseryStock
     */
    public void setFinancialYearNurseryStockId(LongFilter financialYearNurseryStockId) {
        this.financialYearNurseryStockId = financialYearNurseryStockId;
    }

    /**
     * To Get the pointOfSaleDetailsId from NurseryStock table
     * 
     * @return pointOfSaleDetailsId
     */
    public LongFilter getPointOfSaleDetailsId() {
        return pointOfSaleDetailsId;
    }

    /**
     * To set the pointOfSaleDetailsId values for the NurseryStock table.
     * 
     * @param pointOfSaleDetailsId pointOfSaleDetailsId of the NurseryStock
     */
    public void setPointOfSaleDetailsId(LongFilter pointOfSaleDetailsId) {
        this.pointOfSaleDetailsId = pointOfSaleDetailsId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
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
                (damageQuantity != null ? "damageQuantity=" + damageQuantity + ", " : "") +
                (nurseryStockDetailsId != null ? "nurseryStockDetailsId=" + nurseryStockDetailsId + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (financialYearNurseryStockId != null ? "financialYearNurseryStockId=" + financialYearNurseryStockId + ", " : "") +
                (pointOfSaleDetailsId != null ? "pointOfSaleDetailsId=" + pointOfSaleDetailsId + ", " : "") +
            "}";
    }

}
