/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingsDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FinancialYearSettings entity.
 */
public class FinancialYearSettingsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String batchName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer status;

    private Long financialYearId;

    private String financialYearPickListValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public void setFinancialYearId(Long pickListValueId) {
        this.financialYearId = pickListValueId;
    }

    public String getFinancialYearPickListValue() {
        return financialYearPickListValue;
    }

    public void setFinancialYearPickListValue(String pickListValuePickListValue) {
        this.financialYearPickListValue = pickListValuePickListValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinancialYearSettingsDTO financialYearSettingsDTO = (FinancialYearSettingsDTO) o;
        if (financialYearSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), financialYearSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinancialYearSettingsDTO{" +
            "id=" + getId() +
            ", batchName='" + getBatchName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status=" + getStatus() +
            ", financialYear=" + getFinancialYearId() +
            ", financialYear='" + getFinancialYearPickListValue() + "'" +
            "}";
    }
}
