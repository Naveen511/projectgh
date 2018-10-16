/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsCriteria and
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


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the NurseryInventoryDetails entity. This class is used in NurseryInventoryDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-inventory-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryInventoryDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter date;

    private IntegerFilter quantity;

    private IntegerFilter status;

    private StringFilter description;

    private LongFilter nurseryInventoryId;

    private LongFilter damageTypeId;

    private LongFilter inventoryDamageDescriptionId;

    /**
     * NurseryInventoryDetailsCriteria from NurseryInventoryDetails table
     */
    public NurseryInventoryDetailsCriteria() {
    }

    /**
     * To Get the Id from NurseryInventoryDetails table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryInventoryDetails table.
     * 
     * @param id id of the NurseryInventoryDetails
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the date from NurseryInventoryDetails table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the NurseryInventoryDetails table.
     *
     * @param date date of the NurseryInventoryDetails
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the quantity from NurseryInventoryDetails table
     * 
     * @return quantity
     */
    public IntegerFilter getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the NurseryInventoryDetails table.
     * 
     * @param quantity quantity of the NurseryInventoryDetails
     */
    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the status from NurseryInventoryDetails table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryInventoryDetails table.
     * 
     * @param status status of the NurseryInventoryDetails
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the description from NurseryInventoryDetails table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryInventoryDetails table.
     * 
     * @param description description of the NurseryInventoryDetails
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the nurseryInventoryId from NurseryInventoryDetails table
     * 
     * @return nurseryInventoryId
     */
    public LongFilter getNurseryInventoryId() {
        return nurseryInventoryId;
    }

    /**
     * To set the nurseryInventoryId values for the NurseryInventoryDetails table.
     * 
     * @param nurseryInventoryId nurseryInventoryId of the NurseryInventoryDetails
     */
    public void setNurseryInventoryId(LongFilter nurseryInventoryId) {
        this.nurseryInventoryId = nurseryInventoryId;
    }

    /**
     * To Get the damageTypeId from NurseryInventoryDetails table
     * 
     * @return damageTypeId
     */
    public LongFilter getDamageTypeId() {
        return damageTypeId;
    }

    /**
     * To set the damageTypeId values for the NurseryInventoryDetails table.
     * 
     * @param damageTypeId damageTypeId of the NurseryInventoryDetails
     */
    public void setDamageTypeId(LongFilter damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    /**
     * To Get the inventoryDamageDescriptionId from NurseryInventoryDetails table
     * 
     * @return inventoryDamageDescriptionId
     */
    public LongFilter getInventoryDamageDescriptionId() {
        return inventoryDamageDescriptionId;
    }

    /**
     * To set the inventoryDamageDescriptionId values for the NurseryInventoryDetails table.
     * 
     * @param inventoryDamageDescriptionId inventoryDamageDescriptionId of the NurseryInventoryDetails
     */
    public void setInventoryDamageDescriptionId(LongFilter inventoryDamageDescriptionId) {
        this.inventoryDamageDescriptionId = inventoryDamageDescriptionId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "NurseryInventoryDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (nurseryInventoryId != null ? "nurseryInventoryId=" + nurseryInventoryId + ", " : "") +
                (damageTypeId != null ? "damageTypeId=" + damageTypeId + ", " : "") +
                (inventoryDamageDescriptionId != null ? "inventoryDamageDescriptionId=" + inventoryDamageDescriptionId + ", " : "") +
            "}";
    }

}
