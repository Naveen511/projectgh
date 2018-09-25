/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Sector entity.
 */
public class SectorDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Sector name cannot be blank.")
    private String sectorName;

    private String sectorAddress;

    private Integer status;

    @NotNull(message = "Zonal name cannot be blank.")
    private Long zonalId;

    private String zonalZoneName;

    private Long financialYearSectorId;

    private String financialYearSectorBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSectorAddress() {
        return sectorAddress;
    }

    public void setSectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getZonalId() {
        return zonalId;
    }

    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    public String getZonalZoneName() {
        return zonalZoneName;
    }

    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
    }

    public Long getFinancialYearSectorId() {
        return financialYearSectorId;
    }

    public void setFinancialYearSectorId(Long financialYearSettingsId) {
        this.financialYearSectorId = financialYearSettingsId;
    }

    public String getFinancialYearSectorBatchName() {
        return financialYearSectorBatchName;
    }

    public void setFinancialYearSectorBatchName(String financialYearSettingsBatchName) {
        this.financialYearSectorBatchName = financialYearSettingsBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SectorDTO sectorDTO = (SectorDTO) o;
        if (sectorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sectorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SectorDTO{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", sectorAddress='" + getSectorAddress() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            ", financialYearSector=" + getFinancialYearSectorId() +
            ", financialYearSector='" + getFinancialYearSectorBatchName() + "'" +
            "}";
    }
}
