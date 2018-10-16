/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaCriteria and
                            declared the table fields, data types for ShadeArea table.
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
 * Criteria class for the ShadeArea entity. This class is used in ShadeAreaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /shade-areas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ShadeAreaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter noOfSeedlings;

    private LocalDateFilter date;

    private IntegerFilter status;

    private IntegerFilter damage;

    private IntegerFilter saplings;

    private IntegerFilter round;

    private LongFilter batchId;

    private LongFilter financialYearShadeAreaId;

    /**
     * ShadeAreaCriteria from ShadeArea table
     */
    public ShadeAreaCriteria() {
    }

    /**
     * To Get the Id from ShadeArea table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the ShadeArea table.
     * 
     * @param id id of the ShadeArea
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the noOfSeedlings from ShadeArea table
     * 
     * @return noOfSeedlings
     */
    public LongFilter getNoOfSeedlings() {
        return noOfSeedlings;
    }

    /**
     * To set the noOfSeedlings values for the ShadeArea table.
     * 
     * @param noOfSeedlings noOfSeedlings of the ShadeArea
     */
    public void setNoOfSeedlings(LongFilter noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
    }

    /**
     * To Get the date from ShadeArea table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the ShadeArea table.
     * 
     * @param date date of the ShadeArea
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the status from ShadeArea table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the ShadeArea table.
     * 
     * @param status status of the ShadeArea
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the damage from ShadeArea table
     * 
     * @return damage
     */
    public IntegerFilter getDamage() {
        return damage;
    }

    /**
     * To set the damage values for the ShadeArea table.
     * 
     * @param damage damage of the ShadeArea
     */
    public void setDamage(IntegerFilter damage) {
        this.damage = damage;
    }

    /**
     * To Get the saplings from ShadeArea table
     * 
     * @return idsaplings
     */
    public IntegerFilter getSaplings() {
        return saplings;
    }

    /**
     * To set the saplings values for the ShadeArea table.
     * 
     * @param saplings saplings of the ShadeArea
     */
    public void setSaplings(IntegerFilter saplings) {
        this.saplings = saplings;
    }

    /**
     * To Get the round from ShadeArea table
     * 
     * @return round
     */
    public IntegerFilter getRound() {
        return round;
    }

    /**
     * To set the round values for the ShadeArea table.
     * 
     * @param round round of the ShadeArea
     */
    public void setRound(IntegerFilter round) {
        this.round = round;
    }

    /**
     * To Get the batchId from ShadeArea table
     * 
     * @return batchId
     */
    public LongFilter getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the ShadeArea table.
     * 
     * @param batchId batchId of the ShadeArea
     */
    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the financialYearShadeAreaId from ShadeArea table
     * 
     * @return financialYearShadeAreaId
     */
    public LongFilter getFinancialYearShadeAreaId() {
        return financialYearShadeAreaId;
    }

    /**
     * To set the financialYearShadeAreaId values for the ShadeArea table.
     * 
     * @param financialYearShadeAreaId financialYearShadeAreaId of the ShadeArea
     */
    public void setFinancialYearShadeAreaId(LongFilter financialYearShadeAreaId) {
        this.financialYearShadeAreaId = financialYearShadeAreaId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "ShadeAreaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noOfSeedlings != null ? "noOfSeedlings=" + noOfSeedlings + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (damage != null ? "damage=" + damage + ", " : "") +
                (saplings != null ? "saplings=" + saplings + ", " : "") +
                (round != null ? "round=" + round + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (financialYearShadeAreaId != null ? "financialYearShadeAreaId=" + financialYearShadeAreaId + ", " : "") +
            "}";
    }

}
