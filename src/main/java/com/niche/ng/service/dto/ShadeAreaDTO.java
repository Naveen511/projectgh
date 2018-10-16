/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ShadeArea entity.
 */
public class ShadeAreaDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Long noOfSeedlings;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private Integer damage;

    private Integer saplings;

    private Long batchId;

    private String batchBatchName;

    private Long financialYearShadeAreaId;

    private String financialYearShadeAreaBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfSeedlings() {
        return noOfSeedlings;
    }

    public void setNoOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getSaplings() {
        return saplings;
    }

    public void setSaplings(Integer saplings) {
        this.saplings = saplings;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchBatchName() {
        return batchBatchName;
    }

    public void setBatchBatchName(String batchBatchName) {
        this.batchBatchName = batchBatchName;
    }

    public Long getFinancialYearShadeAreaId() {
        return financialYearShadeAreaId;
    }

    public void setFinancialYearShadeAreaId(Long financialYearSettingsId) {
        this.financialYearShadeAreaId = financialYearSettingsId;
    }

    public String getFinancialYearShadeAreaBatchName() {
        return financialYearShadeAreaBatchName;
    }

    public void setFinancialYearShadeAreaBatchName(String financialYearSettingsBatchName) {
        this.financialYearShadeAreaBatchName = financialYearSettingsBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShadeAreaDTO shadeAreaDTO = (ShadeAreaDTO) o;
        if (shadeAreaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shadeAreaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShadeAreaDTO{" +
            "id=" + getId() +
            ", noOfSeedlings=" + getNoOfSeedlings() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", damage=" + getDamage() +
            ", saplings=" + getSaplings() +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            ", financialYearShadeArea=" + getFinancialYearShadeAreaId() +
            ", financialYearShadeArea='" + getFinancialYearShadeAreaBatchName() + "'" +
            "}";
    }
}
