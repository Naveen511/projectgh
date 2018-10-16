/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsCriteria and
                            declared the table fields, data types for CommonSettings table.
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
 * Criteria class for the CommonSettings entity. This class is used in CommonSettingsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /common-settings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CommonSettingsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter daysToCloseBatch;

    private IntegerFilter status;

    public CommonSettingsCriteria() {
    }

    /**
     * To Get the Id from CommonSettings table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To Set the Id for CommonSettings table
     *
     * @param id id of the CommonSettings
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To get the daysToCloseBatch for CommonSettings table
     * 
     * @return daysToCloseBatch
     */
    public IntegerFilter getDaysToCloseBatch() {
        return daysToCloseBatch;
    }

    /**
     * To set the daysToCloseBatch for CommonSettings table
     * 
     * @param daysToCloseBatch close the batch
     */
    public void setDaysToCloseBatch(IntegerFilter daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
    }

    /**
     * To Set the status for CommonSettings table
     *
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To Set the status for CommonSettings table
     *
     * @param status status in table
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "CommonSettingsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (daysToCloseBatch != null ? "daysToCloseBatch=" + daysToCloseBatch + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
            "}";
    }

}
