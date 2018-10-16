/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorCriteria and
                            declared the table fields, data types for Sector table.
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
 * Criteria class for the Sector entity. This class is used in SectorResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sectors?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SectorCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter sectorName;

    private StringFilter sectorAddress;

    private IntegerFilter status;

    private LongFilter nurserysId;

    private LongFilter zonalId;

    private LongFilter financialYearSectorId;

    private LongFilter inchargeId;

    private LongFilter mapSectorWithZonalId;

    private LongFilter mapNurseryWithSectorId;

    /**
     * SectorCriteria from Sector table
     */
    public SectorCriteria() {
    }

    /**
     * To Get the Id from Sector table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the Sector table.
     * 
     * @param id id of the Sector
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the sectorName from Sector table
     * 
     * @return sectorName
     */
    public StringFilter getSectorName() {
        return sectorName;
    }

    /**
     * To set the sectorName values for the Sector table.
     * 
     * @param sectorName sectorName of the Sector
     */
    public void setSectorName(StringFilter sectorName) {
        this.sectorName = sectorName;
    }

    /**
     * To Get the sectorAddress from Sector table
     * 
     * @return sectorAddress
     */
    public StringFilter getSectorAddress() {
        return sectorAddress;
    }

    /**
     * To set the sectorAddress values for the Sector table.
     * 
     * @param sectorAddress sectorAddress of the Sector
     */
    public void setSectorAddress(StringFilter sectorAddress) {
        this.sectorAddress = sectorAddress;
    }

    /**
     * To Get the status from Sector table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the Sector table.
     * 
     * @param status status of the Sector
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the nurserysId from Sector table
     * 
     * @return nurserysId
     */
    public LongFilter getNurserysId() {
        return nurserysId;
    }

    /**
     * To set the nurserysId values for the Sector table.
     * 
     * @param nurserysId nurserysId of the Sector
     */
    public void setNurserysId(LongFilter nurserysId) {
        this.nurserysId = nurserysId;
    }

    /**
     * To Get the zonalId from Sector table
     * 
     * @return zonalId
     */
    public LongFilter getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the Sector table.
     * 
     * @param zonalId zonalId of the Sector
     */
    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the financialYearSectorId from Sector table
     * 
     * @return financialYearSectorId
     */
    public LongFilter getFinancialYearSectorId() {
        return financialYearSectorId;
    }

    /**
     * To set the financialYearSectorId values for the Sector table.
     * 
     * @param financialYearSectorId financialYearSectorId of the Sector
     */
    public void setFinancialYearSectorId(LongFilter financialYearSectorId) {
        this.financialYearSectorId = financialYearSectorId;
    }

    /**
     * To Get the inchargeId from Sector table
     * 
     * @return inchargeId
     */
    public LongFilter getInchargeId() {
        return inchargeId;
    }

    /**
     * To set the inchargeId values for the Sector table.
     * 
     * @param inchargeId inchargeId of the Sector
     */
    public void setInchargeId(LongFilter inchargeId) {
        this.inchargeId = inchargeId;
    }

    /**
     * To Get the mapSectorWithZonalId from Sector table
     * 
     * @return mapSectorWithZonalId
     */
    public LongFilter getMapSectorWithZonalId() {
        return mapSectorWithZonalId;
    }

    /**
     * To set the mapSectorWithZonalId values for the Sector table.
     * 
     * @param mapSectorWithZonalId mapSectorWithZonalId of the Sector
     */
    public void setMapSectorWithZonalId(LongFilter mapSectorWithZonalId) {
        this.mapSectorWithZonalId = mapSectorWithZonalId;
    }

    /**
     * To Get the mapNurseryWithSectorId from Sector table
     * 
     * @return mapNurseryWithSectorId
     */
    public LongFilter getMapNurseryWithSectorId() {
        return mapNurseryWithSectorId;
    }

    /**
     * To set the mapNurseryWithSectorId values for the Sector table.
     * 
     * @param mapNurseryWithSectorId mapNurseryWithSectorId of the Sector
     */
    public void setMapNurseryWithSectorId(LongFilter mapNurseryWithSectorId) {
        this.mapNurseryWithSectorId = mapNurseryWithSectorId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "SectorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sectorName != null ? "sectorName=" + sectorName + ", " : "") +
                (sectorAddress != null ? "sectorAddress=" + sectorAddress + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (nurserysId != null ? "nurserysId=" + nurserysId + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
                (financialYearSectorId != null ? "financialYearSectorId=" + financialYearSectorId + ", " : "") +
                (inchargeId != null ? "inchargeId=" + inchargeId + ", " : "") +
                (mapSectorWithZonalId != null ? "mapSectorWithZonalId=" + mapSectorWithZonalId + ", " : "") +
                (mapNurseryWithSectorId != null ? "mapNurseryWithSectorId=" + mapNurseryWithSectorId + ", " : "") +
            "}";
    }

}
