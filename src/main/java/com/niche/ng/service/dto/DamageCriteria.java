/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageCriteria and
                            declared the table fields, data types for Damage table.
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
 * Criteria class for the Damage entity. This class is used in DamageResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /damages?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DamageCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter noOfQuantity;

    private LocalDateFilter date;

    private IntegerFilter status;

    private LongFilter batchId;

    private LongFilter descriptionId;

    private LongFilter damageAreaId;

    private LongFilter financialYearDamageId;

    public DamageCriteria() {
    }

    /**
     * To Get the Id from damage table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id for damage table
     * 
     * @param id
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the noOfQuantity from damage table.
     * 
     * @return noOfQuantity
     */
    public LongFilter getNoOfQuantity() {
        return noOfQuantity;
    }

    /**
     * To set the noOfQuantity values for the damage table.
     * 
     * @param noOfQuantity number of quantity in damage
     */
    public void setNoOfQuantity(LongFilter noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    /**
     * To Get the noOfQuantity from damage table.
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the damage table.
     * 
     * @param date damage date
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the status from damage table.
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the damage table.
     * 
     * @param status status in damage
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the batchId from damage table.
     * 
     * @return batchId
     */
    public LongFilter getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the damage table.
     * 
     * @param batchId batchId in damage
     */
    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the descriptionId from damage table.
     * 
     * @return descriptionId
     */
    public LongFilter getDescriptionId() {
        return descriptionId;
    }

    /**
     * To set the descriptionId values for the damage table.
     * 
     * @param descriptionId descriptionId in damage
     */
    public void setDescriptionId(LongFilter descriptionId) {
        this.descriptionId = descriptionId;
    }

    /**
     * To Get the damageAreaId from damage table.
     * 
     * @return damageAreaId
     */
    public LongFilter getDamageAreaId() {
        return damageAreaId;
    }

    /**
     * To set the damageAreaId values for the damage table.
     * 
     * @param damageAreaId damageAreaId in damage
     */
    public void setDamageAreaId(LongFilter damageAreaId) {
        this.damageAreaId = damageAreaId;
    }

    /**
     * To Get the financialYearDamageId from damage table.
     * 
     * @return financialYearDamageId
     */
    public LongFilter getFinancialYearDamageId() {
        return financialYearDamageId;
    }

    /**
     * To set the financialYearDamageId values for the damage table.
     * 
     * @param financialYearDamageId in damage
     */
    public void setFinancialYearDamageId(LongFilter financialYearDamageId) {
        this.financialYearDamageId = financialYearDamageId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "DamageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noOfQuantity != null ? "noOfQuantity=" + noOfQuantity + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (descriptionId != null ? "descriptionId=" + descriptionId + ", " : "") +
                (damageAreaId != null ? "damageAreaId=" + damageAreaId + ", " : "") +
                (financialYearDamageId != null ? "financialYearDamageId=" + financialYearDamageId + ", " : "") +
            "}";
    }

}
