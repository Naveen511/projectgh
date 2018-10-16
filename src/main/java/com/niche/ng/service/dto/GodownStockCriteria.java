/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockCriteria and
                            declared the table fields, data types for GodownStock table.
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

    /**
     * To Get the Id from GodownStock table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the GodownStock table.
     * 
     * @param id id of the GodownStock
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from GodownStock table
     * 
     * @return currentQuantity
     */
    public LongFilter getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values for the GodownStock table.
     * 
     * @param currentQuantity currentQuantity of the GodownStock
     */
    public void setCurrentQuantity(LongFilter currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from GodownStock table
     * 
     * @return addedQuantity
     */
    public LongFilter getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values for the GodownStock table.
     * 
     * @param addedQuantity addedQuantity of the GodownStock
     */
    public void setAddedQuantity(LongFilter addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from GodownStock table
     * 
     * @return consumedQuantity
     */
    public LongFilter getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values for the GodownStock table.
     * 
     * @param consumedQuantity consumedQuantity of the GodownStock
     */
    public void setConsumedQuantity(LongFilter consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from GodownStock table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the GodownStock table.
     * 
     * @param description description of the GodownStock
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownStock table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the GodownStock table.
     * 
     * @param status status of the GodownStock
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the godownStockDetailsId from GodownStock table
     * 
     * @return godownStockDetailsId
     */
    public LongFilter getGodownStockDetailsId() {
        return godownStockDetailsId;
    }

    /**
     * To set the godownStockDetailsId values for the GodownStock table.
     * 
     * @param godownStockDetailsId godownStockDetailsId of the GodownStock
     */
    public void setGodownStockDetailsId(LongFilter godownStockDetailsId) {
        this.godownStockDetailsId = godownStockDetailsId;
    }

    /**
     * To Get the pickListVarietyId from GodownStock table
     * 
     * @return pickListVarietyId
     */
    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the GodownStock table.
     * 
     * @param pickListVarietyId pickListVarietyId of the GodownStock
     */
    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    /**
     * To Get the pickListCategoryId from GodownStock table
     * 
     * @return pickListCategoryId
     */
    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the GodownStock table.
     * 
     * @param pickListCategoryId pickListCategoryId of the GodownStock
     */
    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    /**
     * To Get the pickListQuantityTypeId from GodownStock table
     * 
     * @return pickListQuantityTypeId
     */
    public LongFilter getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    /**
     * To set the pickListQuantityTypeId values for the GodownStock table.
     * 
     * @param pickListQuantityTypeId pickListQuantityTypeId of the GodownStock
     */
    public void setPickListQuantityTypeId(LongFilter pickListQuantityTypeId) {
        this.pickListQuantityTypeId = pickListQuantityTypeId;
    }

    /**
     * To Get the godownId from GodownStock table
     * 
     * @return godownId
     */
    public LongFilter getGodownId() {
        return godownId;
    }

    /**
     * To set the godownId values for the GodownStock table.
     * 
     * @param godownId godownId of the GodownStock
     */
    public void setGodownId(LongFilter godownId) {
        this.godownId = godownId;
    }

    /**
     * To Get the financialYearGodownStockId from GodownStock table
     * 
     * @return financialYearGodownStockId
     */
    public LongFilter getFinancialYearGodownStockId() {
        return financialYearGodownStockId;
    }

    /**
     * To set the financialYearGodownStockId values for the GodownStock table.
     * 
     * @param financialYearGodownStockId financialYearGodownStockId of the GodownStock
     */
    public void setFinancialYearGodownStockId(LongFilter financialYearGodownStockId) {
        this.financialYearGodownStockId = financialYearGodownStockId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
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
