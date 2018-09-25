/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Zonal entity.
 */
public class ZonalDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Zone name cannot be blank.")
    private String zoneName;

    private String zoneAddress;

    private Integer status;

    private Long financialYearId;

    private String financialYearBatchName;

    private Long operationalHeadId;

    private String operationalHeadName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneAddress() {
        return zoneAddress;
    }

    public void setZoneAddress(String zoneAddress) {
        this.zoneAddress = zoneAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFinancialYearId() {
        return financialYearId;
    }

    public void setFinancialYearId(Long financialYearSettingsId) {
        this.financialYearId = financialYearSettingsId;
    }

    public String getFinancialYearBatchName() {
        return financialYearBatchName;
    }

    public void setFinancialYearBatchName(String financialYearSettingsBatchName) {
        this.financialYearBatchName = financialYearSettingsBatchName;
    }

    public Long getOperationalHeadId() {
        return operationalHeadId;
    }

    public void setOperationalHeadId(Long operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    public String getOperationalHeadName() {
        return operationalHeadName;
    }

    public void setOperationalHeadName(String operationalHeadName) {
        this.operationalHeadName = operationalHeadName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ZonalDTO zonalDTO = (ZonalDTO) o;
        if (zonalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ZonalDTO{" +
            "id=" + getId() +
            ", zoneName='" + getZoneName() + "'" +
            ", zoneAddress='" + getZoneAddress() + "'" +
            ", status=" + getStatus() +
            ", financialYear=" + getFinancialYearId() +
            ", financialYear='" + getFinancialYearBatchName() + "'" +
            ", operationalHead=" + getOperationalHeadId() +
            ", operationalHead='" + getOperationalHeadName() + "'" +
            "}";
    }
}
