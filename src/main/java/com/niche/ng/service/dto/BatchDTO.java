/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchDTO and
                            declared the table fields, data types for batch table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
// import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Batch entity.
 * 
 * BatchDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */

public class BatchDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Batch no cannot be blank.")
    @NotBlank(message = "Batch no cannot be blank.")
    private String batchNo;

    @NotNull(message = "Batch name cannot be blank.")
    @NotBlank(message = "Batch name cannot be blank.")
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

    private String noOfKg;

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

    /**
     * To Get the Id of the batch table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id of the batch table
     *
     * @param id : Number
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the batch no from the batch table
     *
     * @return batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * To set the batch no from the batch table
     * 
     * @param batchNo : String
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * To Get the batch name from the batch table
     * 
     * @return batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * To set the batch name from the batch table
     * 
     * @param batchName batch name of a particular batch
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * To get the quantity from the batch table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the batch quantity from the batch table
     *
     * @return quantity Quantity of the batch
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To get the showingType from the batch table
     * 
     * @return showingType type of sown
     */
    public Integer getShowingType() {
        return showingType;
    }

    /**
     * To set the sowing type  from the batch table
     * 
     * @param showingType type of sown
     */
    public void setShowingType(Integer showingType) {
        this.showingType = showingType;
    }

    /**
     * To get the sowingDate from the batch table
     * 
     * @return sowingDate
     */
    public LocalDate getSowingDate() {
        return sowingDate;
    }

    /**
     * To set the sowing date from the batch table.
     * 
     * @param sowingDate date sown
     */
    public void setSowingDate(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    /**
     * To get the closedDate from the batch table
     * 
     * @return closedDate
     */
    public LocalDate getClosedDate() {
        return closedDate;
    }

    /**
     * To set the closed date from the batch table.
     * 
     * @param closedDate date of closing the batch
     */
    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    /**
     * To get the closedDate from the batch table
     * 
     * @return round
     */
    public Integer getRound() {
        return round;
    }

    /**
     * To set the round for the batch table.
     * 
     * @param round round in batch
     */
    public void setRound(Integer round) {
        this.round = round;
    }

    /**
     * To get the remarks from the batch table
     * 
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * To set the remarks for the batch table.
     * 
     * @param remarks remarks in batch
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * To get the status from the batch table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status for the batch table.
     * 
     * @param status status in batch
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To get the noOfKg from the batch table
     * 
     * @return noOfKg
     */
    public String getNoOfKg() {
        return noOfKg;
    }

    /**
     * To set the noOfKg for the batch table.
     * 
     * @param noOfKg the noOfKg in batch
     */
    public void setNoOfKg(String noOfKg) {
        this.noOfKg = noOfKg;
    }

    /**
     * To get the nurseryId from the batch table
     * 
     * @return nurseryId
     */
    public Long getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId for the batch table.
     * 
     * @param nurseryId nurseryId in batch
     */
    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To get the nurseryNurseryName from the batch table
     * 
     * @return nurseryNurseryName
     */
    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    /**
     * To set the nurseryNurseryName for the batch table.
     * 
     * @param nurseryNurseryName nurseryNurseryName in batch
     */
    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

    /**
     * To get the pickListVarietyId from the batch table
     * 
     * @return pickListVarietyId
     */
    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId for the batch table.
     * 
     * @param pickListVarietyId pickListVarietyId in batch
     */
    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    /**
     * To get the pickListVarietyPickListValue from the batch table
     * 
     * @return pickListVarietyPickListValue
     */
    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    /**
     * To set the pickListVarietyPickListValue for the batch table.
     * 
     * @param pickListVarietyPickListValue pickListVarietyPickListValue in batch
     */
    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    /**
     * To get the pickListCategoryId from the batch table
     * 
     * @return pickListCategoryId
     */
    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId for the batch table.
     * 
     * @param pickListCategoryId pickListCategoryId in batch
     */
    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    /**
     * To get the pickListCategoryPickListValue from the batch table
     * 
     * @return pickListCategoryPickListValue
     */
    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    /**
     * To set the pickListCategoryPickListValue for the batch table.
     * 
     * @param pickListCategoryPickListValue pickListCategoryPickListValue in batch
     */
    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    /**
     * To get the quantityTypeId from the batch table
     * 
     * @return quantityTypeId
     */
    public Long getQuantityTypeId() {
        return quantityTypeId;
    }

    /**
     * To set the quantityTypeId for the batch table.
     * 
     * @param quantityTypeId quantityTypeId in batch
     */
    public void setQuantityTypeId(Long pickListValueId) {
        this.quantityTypeId = pickListValueId;
    }

    /**
     * To get the quantityTypePickListValue from the batch table
     * 
     * @return quantityTypePickListValue
     */
    public String getQuantityTypePickListValue() {
        return quantityTypePickListValue;
    }

    /**
     * To set the quantityTypePickListValue for the batch table.
     * 
     * @param quantityTypePickListValue quantityTypePickListValue in batch
     */
    public void setQuantityTypePickListValue(String pickListValuePickListValue) {
        this.quantityTypePickListValue = pickListValuePickListValue;
    }

    /**
     * To get the motherBedId from the batch table
     * 
     * @return motherBedId
     */
    public Long getMotherBedId() {
        return motherBedId;
    }

    /**
     * To set the motherBedId for the batch table.
     * 
     * @param motherBedId motherBedId in batch
     */
    public void setMotherBedId(Long motherBedId) {
        this.motherBedId = motherBedId;
    }

    /**
     * To get the motherBedValue from the batch table
     * 
     * @return motherBedValue
     */
    public String getMotherBedValue() {
        return motherBedValue;
    }

    /**
     * To set the motherBedValue for the batch table.
     * 
     * @param motherBedValue motherBedValue in batch
     */
    public void setMotherBedValue(String motherBedValue) {
        this.motherBedValue = motherBedValue;
    }

    /**
     * To get the financialYearBatchId from the batch table
     * 
     * @return financialYearBatchId
     */
    public Long getFinancialYearBatchId() {
        return financialYearBatchId;
    }

    /**
     * To set the financialYearBatchId for the batch table.
     * 
     * @param financialYearBatchId financialYearBatchId in batch
     */
    public void setFinancialYearBatchId(Long financialYearSettingsId) {
        this.financialYearBatchId = financialYearSettingsId;
    }

    /**
     * To get the financialYearBatchBatchName from the batch table
     * 
     * @return financialYearBatchBatchName
     */
    public String getFinancialYearBatchBatchName() {
        return financialYearBatchBatchName;
    }

    /**
     * To set the financialYearBatchBatchName for the batch table.
     * 
     * @param financialYearBatchBatchName financialYearBatchBatchName in batch
     */
    public void setFinancialYearBatchBatchName(String financialYearSettingsBatchName) {
        this.financialYearBatchBatchName = financialYearSettingsBatchName;
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

        BatchDTO batchDTO = (BatchDTO) o;
        if (batchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batchDTO.getId());
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
            ", noOfKg='" + getNoOfKg() + "'" +
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
