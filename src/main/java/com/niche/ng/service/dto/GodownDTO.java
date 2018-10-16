/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Godown entity.
 */
public class GodownDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String address;

    private String incharge;

    private Integer status;

    private Long financialYearGodownId;

    private String financialYearGodownBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFinancialYearGodownId() {
        return financialYearGodownId;
    }

    public void setFinancialYearGodownId(Long financialYearSettingsId) {
        this.financialYearGodownId = financialYearSettingsId;
    }

    public String getFinancialYearGodownBatchName() {
        return financialYearGodownBatchName;
    }

    public void setFinancialYearGodownBatchName(String financialYearSettingsBatchName) {
        this.financialYearGodownBatchName = financialYearSettingsBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GodownDTO godownDTO = (GodownDTO) o;
        if (godownDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GodownDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", incharge='" + getIncharge() + "'" +
            ", status=" + getStatus() +
            ", financialYearGodown=" + getFinancialYearGodownId() +
            ", financialYearGodown='" + getFinancialYearGodownBatchName() + "'" +
            "}";
    }
}
