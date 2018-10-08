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

    private IntegerFilter status;

    private LongFilter nurserysId;

    private LongFilter zonalId;

    private LongFilter financialYearSectorId;

    private LongFilter inchargeId;

    private LongFilter mapSectorWithZonalId;

    private LongFilter mapNurseryWithSectorId;

    public SectorCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSectorName() {
        return sectorName;
    }

    public void setSectorName(StringFilter sectorName) {
        this.sectorName = sectorName;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getNurserysId() {
        return nurserysId;
    }

    public void setNurserysId(LongFilter nurserysId) {
        this.nurserysId = nurserysId;
    }

    public LongFilter getZonalId() {
        return zonalId;
    }

    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    public LongFilter getFinancialYearSectorId() {
        return financialYearSectorId;
    }

    public void setFinancialYearSectorId(LongFilter financialYearSectorId) {
        this.financialYearSectorId = financialYearSectorId;
    }

    public LongFilter getInchargeId() {
        return inchargeId;
    }

    public void setInchargeId(LongFilter inchargeId) {
        this.inchargeId = inchargeId;
    }

    public LongFilter getMapSectorWithZonalId() {
        return mapSectorWithZonalId;
    }

    public void setMapSectorWithZonalId(LongFilter mapSectorWithZonalId) {
        this.mapSectorWithZonalId = mapSectorWithZonalId;
    }

    public LongFilter getMapNurseryWithSectorId() {
        return mapNurseryWithSectorId;
    }

    public void setMapNurseryWithSectorId(LongFilter mapNurseryWithSectorId) {
        this.mapNurseryWithSectorId = mapNurseryWithSectorId;
    }

    @Override
    public String toString() {
        return "SectorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sectorName != null ? "sectorName=" + sectorName + ", " : "") +
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
