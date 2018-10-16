/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHeadCriteria and
                            declared the table fields, data types for OperationalHead table.
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
 * Criteria class for the OperationalHead entity. This class is used in OperationalHeadResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /operational-heads?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OperationalHeadCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter zonalId;

    private LongFilter mapZonalWithOhId;

    /**
     * OperationalHeadCriteria from OperationalHead table
     */
    public OperationalHeadCriteria() {
    }

    /**
     * To Get the Id from OperationalHead table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the OperationalHead table.
     * 
     * @param id id of the OperationalHead
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the name from OperationalHead table
     * 
     * @return name
     */
    public StringFilter getName() {
        return name;
    }

    /**
     * To set the name values for the OperationalHead table.
     * 
     * @param name name of the OperationalHead
     */
    public void setName(StringFilter name) {
        this.name = name;
    }

    /**
     * To Get the description from OperationalHead table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the OperationalHead table.
     * 
     * @param description description of the OperationalHead
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from OperationalHead table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the OperationalHead table.
     * 
     * @param status status of the OperationalHead
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from OperationalHead table
     * 
     * @return zonalId
     */
    public LongFilter getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the OperationalHead table.
     * 
     * @param zonalId zonalId of the OperationalHead
     */
    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the mapZonalWithOhId from OperationalHead table
     * 
     * @return mapZonalWithOhId
     */
    public LongFilter getMapZonalWithOhId() {
        return mapZonalWithOhId;
    }

    /**
     * To set the mapZonalWithOhId values for the OperationalHead table.
     * 
     * @param mapZonalWithOhId mapZonalWithOhId of the OperationalHead
     */
    public void setMapZonalWithOhId(LongFilter mapZonalWithOhId) {
        this.mapZonalWithOhId = mapZonalWithOhId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "OperationalHeadCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
                (mapZonalWithOhId != null ? "mapZonalWithOhId=" + mapZonalWithOhId + ", " : "") +
            "}";
    }

}
