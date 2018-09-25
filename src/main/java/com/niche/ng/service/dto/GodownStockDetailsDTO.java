/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GodownStockDetails entity.
 */
public class GodownStockDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Date cannot be blank")
    private LocalDate date;

    @NotNull
    private Long quantity;

    private String description;

    private Integer status;

    private Float price;

    private Long godownStockId;

    private Long financialYearGodownStockDetailsId;

    private String financialYearGodownStockDetailsBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getGodownStockId() {
        return godownStockId;
    }

    public void setGodownStockId(Long godownStockId) {
        this.godownStockId = godownStockId;
    }

    public Long getFinancialYearGodownStockDetailsId() {
        return financialYearGodownStockDetailsId;
    }

    public void setFinancialYearGodownStockDetailsId(Long financialYearSettingsId) {
        this.financialYearGodownStockDetailsId = financialYearSettingsId;
    }

    public String getFinancialYearGodownStockDetailsBatchName() {
        return financialYearGodownStockDetailsBatchName;
    }

    public void setFinancialYearGodownStockDetailsBatchName(String financialYearSettingsBatchName) {
        this.financialYearGodownStockDetailsBatchName = financialYearSettingsBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GodownStockDetailsDTO godownStockDetailsDTO = (GodownStockDetailsDTO) o;
        if (godownStockDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStockDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GodownStockDetailsDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", price=" + getPrice() +
            ", godownStock=" + getGodownStockId() +
            ", financialYearGodownStockDetails=" + getFinancialYearGodownStockDetailsId() +
            ", financialYearGodownStockDetails='" + getFinancialYearGodownStockDetailsBatchName() + "'" +
            "}";
    }
}
