/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryCriteria and
                            declared the table fields, data types for NurseryInventory table.
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
 * Criteria class for the NurseryInventory entity. This class is used in NurseryInventoryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-inventories?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryInventoryCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter currentQuantity;

    private IntegerFilter addedQuantity;

    private IntegerFilter consumedQuantity;

    private StringFilter description;

    private IntegerFilter status;

    private IntegerFilter damageQuantity;

    private LongFilter nurserysId;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter nurseryInventoryDetailsId;

    private LongFilter quantityTypeId;

    /**
     * NurseryInventoryCriteria from NurseryInventory table
     */
    public NurseryInventoryCriteria() {
    }

    /**
     * To Get the Id from NurseryInventory table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryInventory table.
     * 
     * @param id id of the NurseryInventory

     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from NurseryInventory table
     * 
     * @return currentQuantity
     */
    public IntegerFilter getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values for the NurseryInventory table.
     * 
     * @param currentQuantity currentQuantity of the NurseryInventory
     */
    public void setCurrentQuantity(IntegerFilter currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from NurseryInventory table
     * 
     * @return addedQuantity
     */
    public IntegerFilter getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values for the NurseryInventory table.
     * 
     * @param addedQuantity addedQuantity of the NurseryInventory
     */
    public void setAddedQuantity(IntegerFilter addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from NurseryInventory table
     * 
     * @return consumedQuantity
     */
    public IntegerFilter getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values for the NurseryInventory table.
     * 
     * @param consumedQuantity consumedQuantity of the NurseryInventory
     */
    public void setConsumedQuantity(IntegerFilter consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from NurseryInventory table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryInventory table.
     * 
     * @param description description of the NurseryInventory
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryInventory table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryInventory table.
     * 
     * @param status status of the NurseryInventory
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the damageQuantity from NurseryInventory table
     * 
     * @return damageQuantity
     */
    public IntegerFilter getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To set the damageQuantity values for the NurseryInventory table.
     * 
     * @param damageQuantity damageQuantity of the NurseryInventory
     */
    public void setDamageQuantity(IntegerFilter damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To Get the nurserysId from NurseryInventory table
     * 
     * @return nurserysId
     */
    public LongFilter getNurserysId() {
        return nurserysId;
    }

    /**
     * To set the nurserysId values for the NurseryInventory table.
     * 
     * @param nurserysId nurserysId of the NurseryInventory
     */
    public void setNurserysId(LongFilter nurserysId) {
        this.nurserysId = nurserysId;
    }

    /**
     * To Get the pickListVarietyId from NurseryInventory table
     * 
     * @return pickListVarietyId
     */
    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the NurseryInventory table.
     * 
     * @param pickListVarietyId pickListVarietyId of the NurseryInventory
     */
    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    /**
     * To Get the pickListCategoryId from NurseryInventory table
     * 
     * @return pickListCategoryId
     */
    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the NurseryInventory table.
     * 
     * @param pickListCategoryId pickListCategoryId of the NurseryInventory
     */
    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    /**
     * To Get the nurseryInventoryDetailsId from NurseryInventory table
     * 
     * @return nurseryInventoryDetailsId
     */
    public LongFilter getNurseryInventoryDetailsId() {
        return nurseryInventoryDetailsId;
    }

    /**
     * To set the nurseryInventoryDetailsId values for the NurseryInventory table.
     * 
     * @param nurseryInventoryDetailsId nurseryInventoryDetailsId of the NurseryInventory
     */
    public void setNurseryInventoryDetailsId(LongFilter nurseryInventoryDetailsId) {
        this.nurseryInventoryDetailsId = nurseryInventoryDetailsId;
    }

    /**
     * To Get the quantityTypeId from NurseryInventory table
     * 
     * @return quantityTypeId
     */
    public LongFilter getQuantityTypeId() {
        return quantityTypeId;
    }

    /**
     * To set the idquantityTypeId values for the NurseryInventory table.
     * 
     * @param quantityTypeId quantityTypeId of the NurseryInventory
     */
    public void setQuantityTypeId(LongFilter quantityTypeId) {
        this.quantityTypeId = quantityTypeId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "NurseryInventoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (currentQuantity != null ? "currentQuantity=" + currentQuantity + ", " : "") +
                (addedQuantity != null ? "addedQuantity=" + addedQuantity + ", " : "") +
                (consumedQuantity != null ? "consumedQuantity=" + consumedQuantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (damageQuantity != null ? "damageQuantity=" + damageQuantity + ", " : "") +
                (nurserysId != null ? "nurserysId=" + nurserysId + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (nurseryInventoryDetailsId != null ? "nurseryInventoryDetailsId=" + nurseryInventoryDetailsId + ", " : "") +
                (quantityTypeId != null ? "quantityTypeId=" + quantityTypeId + ", " : "") +
            "}";
    }

}
