/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PointOfSaleDetailsDTO and
                            declared the table fields, data types for PointOfSaleDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PointOfSaleDetails entity.
 * 
 * PointOfSaleDetailsDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class PointOfSaleDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quanity cannot be blank.")
    private Integer quantity;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private String purposeOfTaking;

    @NotNull(message = "Donor name cannot be blank.")
    @NotBlank(message = "Donor name cannot be blank.")
    private String donorName;

    private String donorAddress;

    private String contactNo;

    private Float discount;

    private Float discountAmount;

    @NotNull(message = "Collected amount cannot be blank.")
    private Float collectedAmount;

    private Integer status;

    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    private Long nurseryStockId;

    /**
     * To Get the Id from PointOfSaleDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the PointOfSaleDetails table.
     * 
     * @param id id of the PointOfSaleDetails
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from PointOfSaleDetails table
     * 
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the PointOfSaleDetails table.
     * 
     * @param quantity quantity of the PointOfSaleDetails
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the purposeOfTaking from PointOfSaleDetails table
     * 
     * @return purposeOfTaking
     */
    public String getPurposeOfTaking() {
        return purposeOfTaking;
    }

    /**
     * To set the purposeOfTaking values for the PointOfSaleDetails table.
     * 
     * @param purposeOfTaking purposeOfTaking of the PointOfSaleDetails
     */
    public void setPurposeOfTaking(String purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
    }

    /**
     * To Get the donorName from PointOfSaleDetails table
     * 
     * @return donorName
     */
    public String getDonorName() {
        return donorName;
    }

    /**
     * To set the donorName values for the PointOfSaleDetails table.
     * 
     * @param donorName donorName of the PointOfSaleDetails
     */
    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    /**
     * To Get the donorAddress from PointOfSaleDetails table
     * 
     * @return donorAddress
     */
    public String getDonorAddress() {
        return donorAddress;
    }

    /**
     * To set the donorAddress values for the PointOfSaleDetails table.
     * 
     * @param donorAddress donorAddress of the PointOfSaleDetails
     */
    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    /**
     * To Get the contactNo from PointOfSaleDetails table
     * 
     * @return contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * To set the contactNo values for the PointOfSaleDetails table.
     * 
     * @param contactNo contactNo of the PointOfSaleDetails
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * To Get the discount from PointOfSaleDetails table
     * 
     * @return discount
     */
    public Float getDiscount() {
        return discount;
    }

    /**
     * To set the discount values for the PointOfSaleDetails table.
     * 
     * @param discount discount of the PointOfSaleDetails
     */
    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    /**
     * To Get the discountAmount from PointOfSaleDetails table
     * 
     * @return discountAmount
     */
    public Float getDiscountAmount() {
        return discountAmount;
    }

    /**
     * To set the discountAmount values for the PointOfSaleDetails table.
     * 
     * @param discountAmount discountAmount of the PointOfSaleDetails
     */
    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * To Get the collectedAmount from PointOfSaleDetails table
     * 
     * @return collectedAmount
     */
    public Float getCollectedAmount() {
        return collectedAmount;
    }

    /**
     * To set the collectedAmount values for the PointOfSaleDetails table.
     * 
     * @param collectedAmount collectedAmount of the PointOfSaleDetails
     */
    public void setCollectedAmount(Float collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    /**
     * To Get the date from PointOfSaleDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the PointOfSaleDetails table.
     * 
     * @param date date of the PointOfSaleDetails
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from PointOfSaleDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the PointOfSaleDetails table.
     * 
     * @param status status of the PointOfSaleDetails
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the pickListVarietyId from PointOfSaleDetails table
     * 
     * @return pickListVarietyId
     */
    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the PointOfSaleDetails table.
     * 
     * @param pickListVarietyId pickListVarietyId of the PointOfSaleDetails
     */
    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    /**
     * To Get the pickListVarietyPickListValue from PointOfSaleDetails table
     * 
     * @return pickListVarietyPickListValue
     */
    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    /**
     * To set the pickListVarietyPickListValue values for the PointOfSaleDetails table.
     * 
     * @param pickListVarietyPickListValue pickListVarietyPickListValue of the PointOfSaleDetails
     */
    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListCategoryId from PointOfSaleDetails table
     * 
     * @return pickListCategoryId
     */
    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the PointOfSaleDetails table.
     * 
     * @param pickListCategoryId pickListCategoryId of the PointOfSaleDetails
     */
    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    /**
     * To Get the pickListCategoryPickListValue from PointOfSaleDetails table
     * 
     * @return pickListCategoryPickListValue
     */
    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    /**
     * To set the pickListCategoryPickListValue values for the PointOfSaleDetails table.
     * 
     * @param pickListCategoryPickListValue pickListCategoryPickListValue of the PointOfSaleDetails
     */
    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the nurseryStockId from PointOfSaleDetails table
     * 
     * @return nurseryStockId
     */
    public Long getNurseryStockId() {
        return nurseryStockId;
    }

    /**
     * To set the nurseryStockId values for the PointOfSaleDetails table.
     * 
     * @param nurseryStockId nurseryStockId of the PointOfSaleDetails
     */
    public void setNurseryStockId(Long nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
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

        PointOfSaleDetailsDTO pointOfSaleDetailsDTO = (PointOfSaleDetailsDTO) o;
        if (pointOfSaleDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pointOfSaleDetailsDTO.getId());
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
        return "PointOfSaleDetailsDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", purposeOfTaking='" + getPurposeOfTaking() + "'" +
            ", donorName='" + getDonorName() + "'" +
            ", donorAddress='" + getDonorAddress() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", discount=" + getDiscount() +
            ", discountAmount=" + getDiscountAmount() +
            ", collectedAmount=" + getCollectedAmount() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", nurseryStock=" + getNurseryStockId() +
            "}";
    }
}
