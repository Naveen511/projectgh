/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryStockDetails entity.
 */
public class NurseryStockDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long quantity;

    private String description;

    private Integer status;

    private Long batchId;

    private String batchBatchName;

    private Long nurseryStockId;

    private Long itNurseryId;

    private String itNurseryNurseryName;

    private Long saplingDamageAreaId;

    private String saplingDamageAreaPickListValue;

    private Long financialYearStockDetailsId;

    private String financialYearStockDetailsBatchName;

    private String stockVariety;

    private String stockCategory;

    private Integer stockVarietyId;

    private Integer stockCategoryId;

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

    public Long getNurseryStockId() {
        return nurseryStockId;
    }

    public void setNurseryStockId(Long nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    public Long getItNurseryId() {
        return itNurseryId;
    }

    public void setItNurseryId(Long nurseryId) {
        this.itNurseryId = nurseryId;
    }

    public String getItNurseryNurseryName() {
        return itNurseryNurseryName;
    }

    public void setItNurseryNurseryName(String nurseryNurseryName) {
        this.itNurseryNurseryName = nurseryNurseryName;
    }

    public Long getSaplingDamageAreaId() {
        return saplingDamageAreaId;
    }

    public void setSaplingDamageAreaId(Long pickListValueId) {
        this.saplingDamageAreaId = pickListValueId;
    }

    public String getSaplingDamageAreaPickListValue() {
        return saplingDamageAreaPickListValue;
    }

    public void setSaplingDamageAreaPickListValue(String pickListValuePickListValue) {
        this.saplingDamageAreaPickListValue = pickListValuePickListValue;
    }

    public Long getFinancialYearStockDetailsId() {
        return financialYearStockDetailsId;
    }

    public void setFinancialYearStockDetailsId(Long financialYearSettingsId) {
        this.financialYearStockDetailsId = financialYearSettingsId;
    }

    public String getFinancialYearStockDetailsBatchName() {
        return financialYearStockDetailsBatchName;
    }

    public void setFinancialYearStockDetailsBatchName(String financialYearSettingsBatchName) {
        this.financialYearStockDetailsBatchName = financialYearSettingsBatchName;
    }

    public String getstockVariety() {
        return stockVariety;
    }

    public void setstockVariety(String stockVariety) {
        this.stockVariety = stockVariety;
    }

    public String getstockCategory() {
        return stockCategory;
    }

    /**
     * @param stockCategory the stockCategory to set
     */
    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }

    public Integer getstockCategoryId() {
        return stockCategoryId;
    }

    /**
     * @param stockCategory the stockCategory to set
     */
    public void setStockCategoryId(Integer stockCategoryId) {
        this.stockCategoryId = stockCategoryId;
    }

    public Integer getstockVarietyId() {
        return stockVarietyId;
    }

    public void setstockVarietyId(Integer stockVarietyId) {
        this.stockVarietyId = stockVarietyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NurseryStockDetailsDTO nurseryStockDetailsDTO = (NurseryStockDetailsDTO) o;
        if (nurseryStockDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStockDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryStockDetailsDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            ", nurseryStock=" + getNurseryStockId() +
            ", itNursery=" + getItNurseryId() +
            ", itNursery='" + getItNurseryNurseryName() + "'" +
            ", saplingDamageArea=" + getSaplingDamageAreaId() +
            ", saplingDamageArea='" + getSaplingDamageAreaPickListValue() + "'" +
            ", financialYearStockDetails=" + getFinancialYearStockDetailsId() +
            ", financialYearStockDetails='" + getFinancialYearStockDetailsBatchName() + "'" +
            "' stockvarietyId='" +getstockVarietyId() +
            ", stockVariety='" + getstockVariety() + "'" +
            "' stockvarietyId='" +getstockCategoryId() +
            ", stockCategory='" + getstockCategory() + "'" +
            "}";
    }
}
