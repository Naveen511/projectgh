/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MotherBedCriteria and
                            declared the table fields, data types for MotherBed table.
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
 * Criteria class for the MotherBed entity. This class is used in MotherBedResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /mother-beds?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MotherBedCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter value;

    private IntegerFilter status;

    private LongFilter nurseryId;

    private LongFilter batchMotherBedId;

    /**
     * MotherBedCriteria from MotherBed table
     */
    public MotherBedCriteria() {
    }

    /**
     * To Get the Id from MotherBed table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the MotherBed table.
     * 
     * @param id id value of the MotherBed
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the value from MotherBed table
     * 
     * @return value
     */
    public StringFilter getValue() {
        return value;
    }

    /**
     * To set the id values for the MotherBed table.
     * 
     * @param value value of the MotherBed
     */
    public void setValue(StringFilter value) {
        this.value = value;
    }

    /**
     * To Get the status from MotherBed table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status for the MotherBed table.
     * 
     * @param status status of the MotherBed
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the nurseryId from MotherBed table
     * 
     * @return nurseryId
     */
    public LongFilter getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the MotherBed table.
     * 
     * @param nurseryId nurseryId value of the MotherBed
     */
    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To Get the batchMotherBedId from MotherBed table
     * 
     * @return batchMotherBedId
     */
    public LongFilter getBatchMotherBedId() {
        return batchMotherBedId;
    }

    /**
     * To set the batchMotherBedId values for the MotherBed table.
     * 
     * @param batchMotherBedId batchMotherBedId value of the MotherBed
     */
    public void setBatchMotherBedId(LongFilter batchMotherBedId) {
        this.batchMotherBedId = batchMotherBedId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "MotherBedCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (value != null ? "value=" + value + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (batchMotherBedId != null ? "batchMotherBedId=" + batchMotherBedId + ", " : "") +
            "}";
    }

}
