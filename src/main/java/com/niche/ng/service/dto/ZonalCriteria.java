/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalCriteria and
                            declared the table fields, data types for Zonal table.
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
 * Criteria class for the Zonal entity. This class is used in ZonalResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /zonals?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ZonalCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter zoneName;

    private StringFilter zoneAddress;

    private IntegerFilter status;

    private LongFilter sectorsId;

    private LongFilter financialYearId;

    private LongFilter operationalHeadId;

    private LongFilter mapZonalWithOhId;

    private LongFilter zonalInchargeId;

    private LongFilter mapSectorWithZonalId;

    /**
     * ZonalCriteria from Zonal table
     */
    public ZonalCriteria() {
    }

    /**
     * To Get the Id from Zonal table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the Zonal table.
     * 
     * @param id id of the Zonal
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the zoneName from Zonal table
     * 
     * @return zoneName
     */
    public StringFilter getZoneName() {
        return zoneName;
    }

    /**
     * To set the zoneName values for the Zonal table.
     * 
     * @param zoneName zoneName of the Zonal
     */
    public void setZoneName(StringFilter zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * To Get the zoneAddress from Zonal table
     * 
     * @return zoneAddress
     */
    public StringFilter getZoneAddress() {
        return zoneAddress;
    }

    /**
     * To set the zoneAddress values for the Zonal table.
     * 
     * @param zoneAddress zoneAddress of the Zonal
     */
    public void setZoneAddress(StringFilter zoneAddress) {
        this.zoneAddress = zoneAddress;
    }

    /**
     * To Get the status from Zonal table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the Zonal table.
     * 
     * @param status status of the Zonal
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the sectorsId from Zonal table
     * 
     * @return sectorsId
     */
    public LongFilter getSectorsId() {
        return sectorsId;
    }

    /**
     * To set the sectorsId values for the Zonal table.
     * 
     * @param sectorsId sectorsId of the Zonal
     */
    public void setSectorsId(LongFilter sectorsId) {
        this.sectorsId = sectorsId;
    }

    /**
     * To Get the financialYearId from Zonal table
     * 
     * @return financialYearId
     */
    public LongFilter getFinancialYearId() {
        return financialYearId;
    }

    /**
     * To set the financialYearId values for the Zonal table.
     * 
     * @param financialYearId financialYearId of the Zonal
     */
    public void setFinancialYearId(LongFilter financialYearId) {
        this.financialYearId = financialYearId;
    }

    /**
     * To Get the operationalHeadId from Zonal table
     * 
     * @return operationalHeadId
     */
    public LongFilter getOperationalHeadId() {
        return operationalHeadId;
    }

    /**
     * To set the operationalHeadId values for the Zonal table.
     * 
     * @param operationalHeadId operationalHeadId of the Zonal
     */
    public void setOperationalHeadId(LongFilter operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    /**
     * To Get the mapZonalWithOhId from Zonal table
     * 
     * @return mapZonalWithOhId
     */
    public LongFilter getMapZonalWithOhId() {
        return mapZonalWithOhId;
    }

    /**
     * To set the mapZonalWithOhId values for the Zonal table.
     * 
     * @param mapZonalWithOhId mapZonalWithOhId of the Zonal
     */
    public void setMapZonalWithOhId(LongFilter mapZonalWithOhId) {
        this.mapZonalWithOhId = mapZonalWithOhId;
    }

    /**
     * To Get the zonalInchargeId from Zonal table
     * 
     * @return zonalInchargeId
     */
    public LongFilter getZonalInchargeId() {
        return zonalInchargeId;
    }

    /**
     * To set the id zonalInchargeId for the Zonal table.
     * 
     * @param zonalInchargeId zonalInchargeId of the Zonal
     */
    public void setZonalInchargeId(LongFilter zonalInchargeId) {
        this.zonalInchargeId = zonalInchargeId;
    }

    /**
     * To Get the mapSectorWithZonalId from Zonal table
     * 
     * @return mapSectorWithZonalId
     */
    public LongFilter getMapSectorWithZonalId() {
        return mapSectorWithZonalId;
    }

    /**
     * To set the mapSectorWithZonalId values for the Zonal table.
     * 
     * @param mapSectorWithZonalId mapSectorWithZonalId of the Zonal
     */
    public void setMapSectorWithZonalId(LongFilter mapSectorWithZonalId) {
        this.mapSectorWithZonalId = mapSectorWithZonalId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "ZonalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (zoneName != null ? "zoneName=" + zoneName + ", " : "") +
                (zoneAddress != null ? "zoneAddress=" + zoneAddress + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (sectorsId != null ? "sectorsId=" + sectorsId + ", " : "") +
                (financialYearId != null ? "financialYearId=" + financialYearId + ", " : "") +
                (operationalHeadId != null ? "operationalHeadId=" + operationalHeadId + ", " : "") +
                (mapZonalWithOhId != null ? "mapZonalWithOhId=" + mapZonalWithOhId + ", " : "") +
                (zonalInchargeId != null ? "zonalInchargeId=" + zonalInchargeId + ", " : "") +
                (mapSectorWithZonalId != null ? "mapSectorWithZonalId=" + mapSectorWithZonalId + ", " : "") +
            "}";
    }

}
