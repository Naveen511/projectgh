/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListCriteria and
                            declared the table fields, data types for PickList table.
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
 * Criteria class for the PickList entity. This class is used in PickListResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /pick-lists?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PickListCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter pickListName;

    private IntegerFilter status;

    private StringFilter displayLabelName;

    private LongFilter pickListValuesId;

    /**
     * PickListCriteria from PickList table
     */
    public PickListCriteria() {
    }

    /**
     * To Get the Id from PickList table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the PickList table.
     * 
     * @param id id of the PickList
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the pickListName from PickList table
     * 
     * @return pickListName
     */
    public StringFilter getPickListName() {
        return pickListName;
    }

    /**
     * To set the pickListName values for the PickList table.
     * 
     * @param pickListName pickListName of the PickList
     */
    public void setPickListName(StringFilter pickListName) {
        this.pickListName = pickListName;
    }

    /**
     * To Get the status from PickList table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the PickList table.
     * 
     * @param status status of the PickList
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the displayLabelName from PickList table
     * 
     * @return displayLabelName
     */
    public StringFilter getDisplayLabelName() {
        return displayLabelName;
    }

    /**
     * To set the displayLabelName values for the PickList table.
     * 
     * @param displayLabelName displayLabelName of the PickList
     */
    public void setDisplayLabelName(StringFilter displayLabelName) {
        this.displayLabelName = displayLabelName;
    }

    /**
     * To Get the pickListValuesId from PickList table
     * 
     * @return pickListValuesId
     */
    public LongFilter getPickListValuesId() {
        return pickListValuesId;
    }

    /**
     * To set the pickListValuesId values for the PickList table.
     * 
     * @param pickListValuesId pickListValuesId of the PickList
     */
    public void setPickListValuesId(LongFilter pickListValuesId) {
        this.pickListValuesId = pickListValuesId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "PickListCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pickListName != null ? "pickListName=" + pickListName + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (displayLabelName != null ? "displayLabelName=" + displayLabelName + ", " : "") +
                (pickListValuesId != null ? "pickListValuesId=" + pickListValuesId + ", " : "") +
            "}";
    }

}
