
/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingCriteria and
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
 * Criteria class for the CoverFilling entity. This class is used in CoverFillingResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /cover-fillings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CoverFillingCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter noOfCover;

    private LocalDateFilter date;

    private StringFilter description;

    private IntegerFilter status;

    private IntegerFilter damageQuantity;

    private LongFilter coverFillingDetailsId;

    public CoverFillingCriteria() {
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id for CoverFilling table
     * 
     * @param id
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return noOfCover
     */
    public IntegerFilter getNoOfCover() {
        return noOfCover;
    }
    
    /**
     * To Set the noOfCover for CoverFilling table
     * 
     * @param noOfCover noOfCover on cover filling
     */
    public void setNoOfCover(IntegerFilter noOfCover) {
        this.noOfCover = noOfCover;
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To Set the date for CoverFilling table.
     * 
     * @param date date while cover filling
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the description from CoverFilling table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To Set the description for CoverFilling table.
     * 
     * @param description description in cover filling
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from CoverFilling table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To Set the status for CoverFilling table.
     * 
     * @param status status in cover filling
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the damageQuantity from CoverFilling table
     * 
     * @return damageQuantity
     */
    public IntegerFilter getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To Set the damageQuantity for CoverFilling table.
     * 
     * @param damageQuantity damage quantity in cover filling
     */
    public void setDamageQuantity(IntegerFilter damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To Set the coverFillingDetailsId for CoverFilling table.
     * 
     * @param coverFillingDetailsId damage quantity in cover filling
     */
    public LongFilter getCoverFillingDetailsId() {
        return coverFillingDetailsId;
    }

    /**
     * To Set the coverFillingDetailsId for CoverFilling table.
     * 
     * @param coverFillingDetailsId damage quantity in cover filling
     */
    public void setCoverFillingDetailsId(LongFilter coverFillingDetailsId) {
        this.coverFillingDetailsId = coverFillingDetailsId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "CoverFillingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noOfCover != null ? "noOfCover=" + noOfCover + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (damageQuantity != null ? "damageQuantity=" + damageQuantity + ", " : "") +
                (coverFillingDetailsId != null ? "coverFillingDetailsId=" + coverFillingDetailsId + ", " : "") +
            "}";
    }

}
