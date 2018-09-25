/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Batch entity.
 */
public class BatchDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Batch no cannot be blank.")
    private String batchNo;

    @NotNull(message = "Batch name cannot be blank.")
    private String batchName;

    @NotNull(message = "Quantity cannot be blank.")
    private Long quantity;

    @NotNull(message = "Showing cannot be blank.")
    private Integer showingType;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate sowingDate;

    private LocalDate closedDate;

    private Integer round;

    private String remarks;

    private Integer status;

    @NotNull(message = "Nursery Name cannot be blank.")
    private Long nurseryId;

    private String nurseryNurseryName;

    @NotNull(message = "Variety cannot be blank.")
    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    @NotNull(message = "Category cannot be blank.")
    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    private Long quantityTypeId;

    private String quantityTypePickListValue;

    private Long motherBedId;

    private String motherBedValue;

    private Long financialYearBatchId;

    private String financialYearBatchBatchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getShowingType() {
        return showingType;
    }

    public void setShowingType(Integer showingType) {
        this.showingType = showingType;
    }

    public LocalDate getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    public Long getQuantityTypeId() {
        return quantityTypeId;
    }

    public void setQuantityTypeId(Long pickListValueId) {
        this.quantityTypeId = pickListValueId;
    }

    public String getQuantityTypePickListValue() {
        return quantityTypePickListValue;
    }

    public void setQuantityTypePickListValue(String pickListValuePickListValue) {
        this.quantityTypePickListValue = pickListValuePickListValue;
    }

    public Long getMotherBedId() {
        return motherBedId;
    }

    public void setMotherBedId(Long motherBedId) {
        this.motherBedId = motherBedId;
    }

    public String getMotherBedValue() {
        return motherBedValue;
    }

    public void setMotherBedValue(String motherBedValue) {
        this.motherBedValue = motherBedValue;
    }

    public Long getFinancialYearBatchId() {
        return financialYearBatchId;
    }

    public void setFinancialYearBatchId(Long financialYearSettingsId) {
        this.financialYearBatchId = financialYearSettingsId;
    }

    public String getFinancialYearBatchBatchName() {
        return financialYearBatchBatchName;
    }

    public void setFinancialYearBatchBatchName(String financialYearSettingsBatchName) {
        this.financialYearBatchBatchName = financialYearSettingsBatchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BatchDTO batchDTO = (BatchDTO) o;
        if (batchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
            "id=" + getId() +
            ", batchNo='" + getBatchNo() + "'" +
            ", batchName='" + getBatchName() + "'" +
            ", quantity=" + getQuantity() +
            ", showingType=" + getShowingType() +
            ", sowingDate='" + getSowingDate() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            ", round=" + getRound() +
            ", remarks='" + getRemarks() + "'" +
            ", status=" + getStatus() +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", quantityType=" + getQuantityTypeId() +
            ", quantityType='" + getQuantityTypePickListValue() + "'" +
            ", motherBed=" + getMotherBedId() +
            ", motherBed='" + getMotherBedValue() + "'" +
            ", financialYearBatch=" + getFinancialYearBatchId() +
            ", financialYearBatch='" + getFinancialYearBatchBatchName() + "'" +
            "}";
    }
}
