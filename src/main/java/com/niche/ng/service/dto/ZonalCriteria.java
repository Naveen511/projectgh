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

    public ZonalCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getZoneName() {
        return zoneName;
    }

    public void setZoneName(StringFilter zoneName) {
        this.zoneName = zoneName;
    }

    public StringFilter getZoneAddress() {
        return zoneAddress;
    }

    public void setZoneAddress(StringFilter zoneAddress) {
        this.zoneAddress = zoneAddress;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getSectorsId() {
        return sectorsId;
    }

    public void setSectorsId(LongFilter sectorsId) {
        this.sectorsId = sectorsId;
    }

    public LongFilter getFinancialYearId() {
        return financialYearId;
    }

    public void setFinancialYearId(LongFilter financialYearId) {
        this.financialYearId = financialYearId;
    }

    public LongFilter getOperationalHeadId() {
        return operationalHeadId;
    }

    public void setOperationalHeadId(LongFilter operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    public LongFilter getMapZonalWithOhId() {
        return mapZonalWithOhId;
    }

    public void setMapZonalWithOhId(LongFilter mapZonalWithOhId) {
        this.mapZonalWithOhId = mapZonalWithOhId;
    }

    public LongFilter getZonalInchargeId() {
        return zonalInchargeId;
    }

    public void setZonalInchargeId(LongFilter zonalInchargeId) {
        this.zonalInchargeId = zonalInchargeId;
    }

    public LongFilter getMapSectorWithZonalId() {
        return mapSectorWithZonalId;
    }

    public void setMapSectorWithZonalId(LongFilter mapSectorWithZonalId) {
        this.mapSectorWithZonalId = mapSectorWithZonalId;
    }

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
