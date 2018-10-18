/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsDTO and
                            declared the table fields, data types for GodownPurchaseDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.persistence.*;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GodownPurchaseDetails entity.
 * 
 * GodownPurchaseDetailsDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class GodownPurchaseDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Variety cannot be blank.")
    private Long pickListVarietyId;

    @NotNull(message = "Category cannot be blank.")
    private Long pickListCategoryId;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    @NotNull(message = "Quantity type cannot be blank.")
    private Long pickListQuantityTypeId;

    @NotNull(message = "Quantity cannot be blank.")
    private Long quantity;

    @NotNull(message = "Price cannot be blank.")
    private Float price;

    @NotNull(message = "Owned by cannot be blank.")
    @NotBlank(message = "Owned by cannot be blank.")
    private String ownedBy;

    @NotNull(message = "Vendor name cannot be blank.")
    @NotBlank(message = "Vendor Name cannot be blank.")
    private String vendorName;

    private String vendorAddress;

    private Long vendorPhone;

    private String description;

    private Integer status;

    private String pickListVarietyPickListValue;

    private String pickListCategoryPickListValue;

    private String pickListQuantityTypePickListValue;

    @NotNull(message = "Godown cannot be blank")
    private Long godownId;

    private String godownName;

    private Long financialYearGodownPurchaseId;

    private String financialYearGodownPurchaseBatchName;

    /**
     * To Get the Id from GodownPurchaseDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the GodownPurchaseDetails table.
     * 
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from GodownPurchaseDetails table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the GodownPurchaseDetails table.
     * 
     * @param quantity quantity of the godownPurchaseDetails
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from GodownPurchaseDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the GodownPurchaseDetails table.
     * 
     * @param date date of the godownPurchaseDetails
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the price from GodownPurchaseDetails table
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * To set the price values for the GodownPurchaseDetails table.
     * 
     * @param price price of the godownPurchaseDetails
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * To Get the ownedBy from GodownPurchaseDetails table
     * 
     * @return ownedBy
     */
    public String getOwnedBy() {
        return ownedBy;
    }

    /**
     * To set the ownedBy values for the GodownPurchaseDetails table.
     * 
     * @param ownedBy ownedBy of the godownPurchaseDetails
     */
    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    /**
     * To Get the vendorName from GodownPurchaseDetails table
     * 
     * @return vendorName
     */
    public String getVendorName() {
        return vendorName;
    }
    
    /**
     * To set the vendorName values for the GodownPurchaseDetails table.
     * 
     * @param vendorName vendorName of the godownPurchaseDetails
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * To Get the vendorAddress from GodownPurchaseDetails table
     * 
     * @return vendorAddress
     */
    public String getVendorAddress() {
        return vendorAddress;
    }

    /**
     * To set the vendorAddress values for the GodownPurchaseDetails table.
     * 
     * @param vendorAddress vendorAddress of the godownPurchaseDetails
     */
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    /**
     * To Get the vendorPhone from GodownPurchaseDetails table
     * 
     * @return vendorPhone
     */
    public Long getVendorPhone() {
        return vendorPhone;
    }

    /**
     * To set the vendorPhone values for the GodownPurchaseDetails table.
     * 
     * @param vendorPhone vendorPhone of the godownPurchaseDetails
     */
    public void setVendorPhone(Long vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    /**
     * To Get the description from GodownPurchaseDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the GodownPurchaseDetails table.
     * 
     * @param description description of the godownPurchaseDetails
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownPurchaseDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the GodownPurchaseDetails table.
     * 
     * @param status status of the godownPurchaseDetails
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the pickListVarietyId from GodownPurchaseDetails table
     * 
     * @return pickListVarietyId
     */
    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the GodownPurchaseDetails table.
     * 
     * @param pickListVarietyId pickListVarietyId of the godownPurchaseDetails
     */
    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }
    
    /**
     * To Get the pickListVarietyPickListValue from GodownPurchaseDetails table
     * 
     * @return pickListVarietyPickListValue
     */
    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    /**
     * To set the pickListVarietyPickListValue values for the GodownPurchaseDetails table.
     * 
     * @param pickListVarietyPickListValue pickListVarietyPickListValue of the godownPurchaseDetails
     */
    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListCategoryId from GodownPurchaseDetails table
     * 
     * @return pickListCategoryId
     */
    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the GodownPurchaseDetails table.
     * 
     * @param pickListCategoryId pickListCategoryId of the godownPurchaseDetails
     */
    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    /**
     * To Get the pickListCategoryPickListValue from GodownPurchaseDetails table
     * 
     * @return pickListCategoryPickListValue
     */
    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    /**
     * To set the pickListCategoryPickListValue values for the GodownPurchaseDetails table.
     * 
     * @param pickListCategoryPickListValue pickListCategoryPickListValue of the godownPurchaseDetails
     */
    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListQuantityTypeId from GodownPurchaseDetails table
     * 
     * @return pickListQuantityTypeId
     */
    public Long getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    /**
     * To set the pickListQuantityTypeId values for the GodownPurchaseDetails table.
     * 
     * @param pickListQuantityTypeId of the godownPurchaseDetails
     */
    public void setPickListQuantityTypeId(Long pickListValueId) {
        this.pickListQuantityTypeId = pickListValueId;
    }

    /**
     * To Get the pickListQuantityTypePickListValue from GodownPurchaseDetails table
     * 
     * @return pickListQuantityTypePickListValue
     */
    public String getPickListQuantityTypePickListValue() {
        return pickListQuantityTypePickListValue;
    }

    /**
     * To set the pickListQuantityTypePickListValue values for the GodownPurchaseDetails table.
     * 
     * @param pickListQuantityTypePickListValue of the godownPurchaseDetails
     */
    public void setPickListQuantityTypePickListValue(String pickListValuePickListValue) {
        this.pickListQuantityTypePickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the godownId from GodownPurchaseDetails table
     * 
     * @return godownId
     */
    public Long getGodownId() {
        return godownId;
    }

    /**
     * To set the godownId values for the GodownPurchaseDetails table.
     * 
     * @param godownId godownId of the godownPurchaseDetails
     */
    public void setGodownId(Long godownId) {
        this.godownId = godownId;
    }

    /**
     * To Get the godownName from GodownPurchaseDetails table
     * 
     * @return godownName
     */
    public String getGodownName() {
        return godownName;
    }

    /**
     * To set the godownName values for the GodownPurchaseDetails table.
     * 
     * @param godownName godownName of the godownPurchaseDetails
     */
    public void setGodownName(String godownName) {
        this.godownName = godownName;
    }

    /**
     * To Get the financialYearGodownPurchaseId from GodownPurchaseDetails table
     * 
     * @return financialYearGodownPurchaseId
     */
    public Long getFinancialYearGodownPurchaseId() {
        return financialYearGodownPurchaseId;
    }

    /**
     * To set the financialYearGodownPurchaseId values for the GodownPurchaseDetails table.
     * 
     * @param financialYearGodownPurchaseId financialYearGodownPurchaseId of the godownPurchaseDetails
     */
    public void setFinancialYearGodownPurchaseId(Long financialYearSettingsId) {
        this.financialYearGodownPurchaseId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearGodownPurchaseBatchName from GodownPurchaseDetails table
     * 
     * @return financialYearGodownPurchaseBatchName
     */
    public String getFinancialYearGodownPurchaseBatchName() {
        return financialYearGodownPurchaseBatchName;
    }

    /**
     * To set the financialYearGodownPurchaseBatchName values for the GodownPurchaseDetails table.
     * 
     * @param financialYearGodownPurchaseBatchName of the godownPurchaseDetails
     */
    public void setFinancialYearGodownPurchaseBatchName(String financialYearSettingsBatchName) {
        this.financialYearGodownPurchaseBatchName = financialYearSettingsBatchName;
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

        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = (GodownPurchaseDetailsDTO) o;
        if (godownPurchaseDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownPurchaseDetailsDTO.getId());
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
        return "GodownPurchaseDetailsDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", price=" + getPrice() +
            ", ownedBy='" + getOwnedBy() + "'" +
            ", vendorName='" + getVendorName() + "'" +
            ", vendorAddress='" + getVendorAddress() + "'" +
            ", vendorPhone=" + getVendorPhone() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", pickListQuantityType=" + getPickListQuantityTypeId() +
            ", pickListQuantityType='" + getPickListQuantityTypePickListValue() + "'" +
            ", godown=" + getGodownId() +
            ", godown='" + getGodownName() + "'" +
            ", financialYearGodownPurchase=" + getFinancialYearGodownPurchaseId() +
            ", financialYearGodownPurchase='" + getFinancialYearGodownPurchaseBatchName() + "'" +
            "}";
    }
}
