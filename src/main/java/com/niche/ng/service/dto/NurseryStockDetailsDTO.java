/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsDTO and
                            declared the table fields, data types for NurseryStockDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryStockDetails entity.
 * 
 * NurseryStockDetailsDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class NurseryStockDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    @NotNull(message = "Quantity cannot be blank.")
    private Long quantity;

    private String description;

    private Integer status;

    private Integer itStatus;

    private Long batchId;

    private String batchBatchName;

    private Long nurseryStockId;

    private Long itNurseryId;

    private String itNurseryNurseryName;

    private Long saplingDamageAreaId;

    private String saplingDamageAreaPickListValue;

    private Long financialYearStockDetailsId;

    private String financialYearStockDetailsBatchName;

    private Long fromNurseryStockDetailsId;

    private String fromNurseryStockDetailsNurseryName;

    private String stockVariety;

    private String stockCategory;

    private Integer stockVarietyId;

    private Integer stockCategoryId;

    /**
     * To Get the Id from NurseryStockDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryStockDetails table.
     * 
     * @param id id of the NurseryStockDetails
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the date from NurseryStockDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the NurseryStockDetails table.
     * 
     * @param date date of the NurseryStockDetails
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the quantity from NurseryStockDetails table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the NurseryStockDetails table.
     * 
     * @param quantity quantity of the NurseryStockDetails
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the description from NurseryStockDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryStockDetails table.
     * 
     * @param description description of the NurseryStockDetails
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryStockDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryStockDetails table.
     * 
     * @param status status of the NurseryStockDetails
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the itStatus from NurseryStockDetails table
     * 
     * @return itStatus
     */
    public Integer getItStatus() {
        return itStatus;
    }
    
    /**
     * To set the itStatus values for the NurseryStockDetails table.
     * 
     * @param itStatus itStatus of the NurseryStockDetails
     */
    public void setItStatus(Integer itStatus) {
        this.itStatus = itStatus;
    }

    /**
     * To Get the batchId from NurseryStockDetails table
     * 
     * @return batchId
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the NurseryStockDetails table.
     * 
     * @param batchId batchId of the NurseryStockDetails
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the batchBatchName from NurseryStockDetails table
     * 
     * @return batchBatchName
     */
    public String getBatchBatchName() {
        return batchBatchName;
    }

    /**
     * To set the batchBatchName values for the NurseryStockDetails table.
     * 
     * @param batchBatchName batchBatchName of the NurseryStockDetails
     */
    public void setBatchBatchName(String batchBatchName) {
        this.batchBatchName = batchBatchName;
    }

    /**
     * To Get the nurseryStockId from NurseryStockDetails table
     * 
     * @return nurseryStockId
     */
    public Long getNurseryStockId() {
        return nurseryStockId;
    }

    /**
     * To set the nurseryStockId values for the NurseryStockDetails table.
     * 
     * @param nurseryStockId nurseryStockId of the NurseryStockDetails
     */
    public void setNurseryStockId(Long nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    /**
     * To Get the itNurseryId from NurseryStockDetails table
     * 
     * @return itNurseryId
     */
    public Long getItNurseryId() {
        return itNurseryId;
    }

    /**
     * To set the itNurseryId values for the NurseryStockDetails table.
     * 
     * @param itNurseryId itNurseryId of the NurseryStockDetails
     */
    public void setItNurseryId(Long nurseryId) {
        this.itNurseryId = nurseryId;
    }

    /**
     * To Get the itNurseryNurseryName from NurseryStockDetails table
     * 
     * @return itNurseryNurseryName
     */
    public String getItNurseryNurseryName() {
        return itNurseryNurseryName;
    }

    /**
     * To set the itNurseryNurseryName values for the NurseryStockDetails table.
     * 
     * @param itNurseryNurseryName itNurseryNurseryName of the NurseryStockDetails
     */
    public void setItNurseryNurseryName(String nurseryNurseryName) {
        this.itNurseryNurseryName = nurseryNurseryName;
    }

    /**
     * To Get the saplingDamageAreaId from NurseryStockDetails table
     * 
     * @return saplingDamageAreaId
     */
    public Long getSaplingDamageAreaId() {
        return saplingDamageAreaId;
    }

    /**
     * To set the saplingDamageAreaId values for the NurseryStockDetails table.
     * 
     * @param saplingDamageAreaId saplingDamageAreaId of the NurseryStockDetails
     */
    public void setSaplingDamageAreaId(Long pickListValueId) {
        this.saplingDamageAreaId = pickListValueId;
    }

    /**
     * To Get the saplingDamageAreaPickListValue from NurseryStockDetails table
     * 
     * @return saplingDamageAreaPickListValue
     */
    public String getSaplingDamageAreaPickListValue() {
        return saplingDamageAreaPickListValue;
    }

    /**
     * To set the saplingDamageAreaPickListValue values for the NurseryStockDetails table.
     * 
     * @param saplingDamageAreaPickListValue saplingDamageAreaPickListValue of the NurseryStockDetails
     */
    public void setSaplingDamageAreaPickListValue(String pickListValuePickListValue) {
        this.saplingDamageAreaPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the financialYearStockDetailsId from NurseryStockDetails table
     * 
     * @return financialYearStockDetailsId
     */
    public Long getFinancialYearStockDetailsId() {
        return financialYearStockDetailsId;
    }

    /**
     * To set the financialYearStockDetailsId values for the NurseryStockDetails table.
     * 
     * @param financialYearStockDetailsId financialYearStockDetailsId of the NurseryStockDetails
     */
    public void setFinancialYearStockDetailsId(Long financialYearSettingsId) {
        this.financialYearStockDetailsId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearStockDetailsBatchName from NurseryStockDetails table
     * 
     * @return financialYearStockDetailsBatchName
     */
    public String getFinancialYearStockDetailsBatchName() {
        return financialYearStockDetailsBatchName;
    }

    /**
     * To set the financialYearStockDetailsBatchName values for the NurseryStockDetails table.
     * 
     * @param financialYearStockDetailsBatchName financialYearStockDetailsBatchName of the NurseryStockDetails
     */
    public void setFinancialYearStockDetailsBatchName(String financialYearSettingsBatchName) {
        this.financialYearStockDetailsBatchName = financialYearSettingsBatchName;
    }

    /**
     * To Get the fromNurseryStockDetailsId from NurseryStockDetails table
     * 
     * @return fromNurseryStockDetailsId
     */
    public Long getFromNurseryStockDetailsId() {
        return fromNurseryStockDetailsId;
    }

    /**
     * To set the fromNurseryStockDetailsId values for the NurseryStockDetails table.
     * 
     * @param fromNurseryStockDetailsId fromNurseryStockDetailsId of the NurseryStockDetails
     */
    public void setFromNurseryStockDetailsId(Long nurseryId) {
        this.fromNurseryStockDetailsId = nurseryId;
    }

    /**
     * To Get the fromNurseryStockDetailsNurseryName from NurseryStockDetails table
     * 
     * @return fromNurseryStockDetailsNurseryName
     */
    public String getFromNurseryStockDetailsNurseryName() {
        return fromNurseryStockDetailsNurseryName;
    }

    /**
     * To set the fromNurseryStockDetailsNurseryName values for the NurseryStockDetails table.
     * 
     * @param fromNurseryStockDetailsNurseryName fromNurseryStockDetailsNurseryName of the NurseryStockDetails
     */
    public void setFromNurseryStockDetailsNurseryName(String nurseryNurseryName) {
        this.fromNurseryStockDetailsNurseryName = nurseryNurseryName;
    }

    /**
     * To Get the stockVariety from NurseryStockDetails table
     * 
     * @return stockVariety
     */
    public String getstockVariety() {
        return stockVariety;
    }

    /**
     * To set the stockVariety values for the NurseryStockDetails table.
     * 
     * @param stockVariety stockVariety of the NurseryStockDetails
     */
    public void setstockVariety(String stockVariety) {
        this.stockVariety = stockVariety;
    }

    /**
     * To Get the stockCategory from NurseryStockDetails table
     * 
     * @return stockCategory
     */
    public String getstockCategory() {
        return stockCategory;
    }

    /**
     * To set the stockCategory values for the NurseryStockDetails table.
     * 
     * @param stockCategory stockCategory of the NurseryStockDetails
     */    
    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }

    /**
     * To Get the stockCategoryId from NurseryStockDetails table
     * 
     * @return stockCategoryId
     */
    public Integer getstockCategoryId() {
        return stockCategoryId;
    }

    /**
     * To set the stockCategoryId values for the NurseryStockDetails table.
     * 
     * @param stockCategoryId stockCategoryId of the NurseryStockDetails
     */
    public void setStockCategoryId(Integer stockCategoryId) {
        this.stockCategoryId = stockCategoryId;
    }

    /**
     * To Get the stockVarietyId from NurseryStockDetails table
     * 
     * @return stockVarietyId
     */
    public Integer getstockVarietyId() {
        return stockVarietyId;
    }

    /**
     * To set the stockVarietyId values for the NurseryStockDetails table.
     * 
     * @param stockVarietyId stockVarietyId of the NurseryStockDetails
     */
    public void setstockVarietyId(Integer stockVarietyId) {
        this.stockVarietyId = stockVarietyId;
    }

    /**
     * To check the equals
     * 
     * @return objects
     */
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

    /**
     * hash code for the id
     * 
     * @return objects
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "NurseryStockDetailsDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", itStatus=" + getItStatus() +
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
            ", fromNurseryStockDetails=" + getFromNurseryStockDetailsId() +
            ", fromNurseryStockDetails='" + getFromNurseryStockDetailsNurseryName() + "'" +
            "}";
    }
}
