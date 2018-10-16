/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingDetailsCriteria and
                            declared the table fields, data types for CoverFilling table.
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
 * Criteria class for the CoverFillingDetails entity. This class is used in CoverFillingDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /cover-filling-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CoverFillingDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter quantity;

    private LocalDateFilter date;

    private IntegerFilter status;

    private StringFilter description;

    private LongFilter coverFillingId;

    private LongFilter damageTypeId;

    private LongFilter coverFillingDamageDescriptionId;

    public CoverFillingDetailsCriteria() {
    }

    /**
     * To Get the Id from coverFillingDetails table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id for coverFillingDetails table
     * 
     * @param id
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the quantity from coverFillingDetails table
     * 
     * @return quantity
     */
    public IntegerFilter getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for coverFillingDetails table
     * 
     */
    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from coverFillingDetails table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To Set the date for coverFillingDetails table
     * 
     * @param date date in table
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the status from coverFillingDetails table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To Set the status for coverFillingDetails table
     * 
     * @param status status in table
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the description from coverFillingDetails table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description from coverFillingDetails table
     * 
     * @param description description in table
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To get the coverFillingId from coverFillingDetails table
     * 
     * @return coverFillingId 
     */
    public LongFilter getCoverFillingId() {
        return coverFillingId;
    }

    /**
     * To set the coverFillingId from coverFillingDetails table
     * 
     * @param coverFillingId coverFillingId in table
     */
    public void setCoverFillingId(LongFilter coverFillingId) {
        this.coverFillingId = coverFillingId;
    }

    /**
     * To get the damageTypeId from coverFillingDetails table
     * 
     * @return damageTypeId 
     */
    public LongFilter getDamageTypeId() {
        return damageTypeId;
    }

    /**
     * To set the damageTypeId from coverFillingDetails table
     * 
     * @param damageTypeId damageTypeId in table
     */
    public void setDamageTypeId(LongFilter damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    /**
     * To get the coverFillingDamageDescriptionId from coverFillingDetails table
     * 
     * @return coverFillingDamageDescriptionId 
     */
    public LongFilter getCoverFillingDamageDescriptionId() {
        return coverFillingDamageDescriptionId;
    }

    /**
     * To set the coverFillingDamageDescriptionId from coverFillingDetails table
     * 
     * @param coverFillingDamageDescriptionId in table
     */
    public void setCoverFillingDamageDescriptionId(LongFilter coverFillingDamageDescriptionId) {
        this.coverFillingDamageDescriptionId = coverFillingDamageDescriptionId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "CoverFillingDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (coverFillingId != null ? "coverFillingId=" + coverFillingId + ", " : "") +
                (damageTypeId != null ? "damageTypeId=" + damageTypeId + ", " : "") +
                (coverFillingDamageDescriptionId != null ? "coverFillingDamageDescriptionId=" + coverFillingDamageDescriptionId + ", " : "") +
            "}";
    }

}
