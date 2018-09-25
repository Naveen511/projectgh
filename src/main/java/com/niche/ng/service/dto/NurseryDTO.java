/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Nursery entity.
 */
public class NurseryDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Sector name cannot be blank.")
    private Long sectorId;

    @NotNull(message = "Nursery name cannot be blank.")
    private String nurseryName;

    private String nurseryAddress;

    private Integer status;

    @NotNull(message = "Nursery code cannot be blank.")
    private String code;

    private String sectorSectorName;

    private String zonalName;

    private Long nurseryTypeId;

    private String nurseryTypePickListValue;

    private Long financialYearNurseryId;

    private String financialYearNurseryBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryAddress() {
        return nurseryAddress;
    }

    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorSectorName() {
        return sectorSectorName;
    }

    public void setSectorSectorName(String sectorSectorName) {
        this.sectorSectorName = sectorSectorName;
    }

    public String getZonalName() {
        return zonalName;
    }

    public void setZonalName(String zonalName) {
        this.zonalName = zonalName;
    }

    public Long getNurseryTypeId() {
        return nurseryTypeId;
    }

    public void setNurseryTypeId(Long pickListValueId) {
        this.nurseryTypeId = pickListValueId;
    }

    public String getNurseryTypePickListValue() {
        return nurseryTypePickListValue;
    }

    public void setNurseryTypePickListValue(String pickListValuePickListValue) {
        this.nurseryTypePickListValue = pickListValuePickListValue;
    }

    public Long getFinancialYearNurseryId() {
        return financialYearNurseryId;
    }

    public void setFinancialYearNurseryId(Long financialYearSettingsId) {
        this.financialYearNurseryId = financialYearSettingsId;
    }

    public String getFinancialYearNurseryBatchName() {
        return financialYearNurseryBatchName;
    }

    public void setFinancialYearNurseryBatchName(String financialYearSettingsBatchName) {
        this.financialYearNurseryBatchName = financialYearSettingsBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NurseryDTO nurseryDTO = (NurseryDTO) o;
        if (nurseryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryDTO{" +
            "id=" + getId() +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryAddress='" + getNurseryAddress() + "'" +
            ", status=" + getStatus() +
            ", code='" + getCode() + "'" +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            ", zonal='" + getZonalName() + "'" +
            ", nurseryType=" + getNurseryTypeId() +
            ", nurseryType='" + getNurseryTypePickListValue() + "'" +
            ", financialYearNursery=" + getFinancialYearNurseryId() +
            ", financialYearNursery='" + getFinancialYearNurseryBatchName() + "'" +
            "}";
    }
}
