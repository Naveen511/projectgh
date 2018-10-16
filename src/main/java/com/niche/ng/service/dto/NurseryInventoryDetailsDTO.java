/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsDTO and
                            declared the table fields, data types for NurseryInventoryDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryInventoryDetails entity.
 * 
 * NurseryInventoryDetailsDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class NurseryInventoryDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    @NotNull(message = "Quantity cannot be blank.")
    private Integer quantity;

    private Integer status;

    @Size(max = 255, message = "Description should not exist 255 character.")
    private String description;

    private Long nurseryInventoryId;

    private Long damageTypeId;

    private String damageTypePickListValue;

    private Long inventoryDamageDescriptionId;

    private String inventoryDamageDescriptionPickListValue;

    /**
     * To Get the Id from NurseryInventoryDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryInventoryDetails table.
     * 
     * @param id id of the NurseryInventoryDetails
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the date from NurseryInventoryDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the NurseryInventoryDetails table.
     *
     * @param date date of the NurseryInventoryDetails
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the quantity from NurseryInventoryDetails table
     * 
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the NurseryInventoryDetails table.
     * 
     * @param quantity quantity of the NurseryInventoryDetails
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the status from NurseryInventoryDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryInventoryDetails table.
     * 
     * @param status status of the NurseryInventoryDetails
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the description from NurseryInventoryDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryInventoryDetails table.
     * 
     * @param description description of the NurseryInventoryDetails
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the nurseryInventoryId from NurseryInventoryDetails table
     * 
     * @return nurseryInventoryId
     */
    public Long getNurseryInventoryId() {
        return nurseryInventoryId;
    }

    /**
     * To set the nurseryInventoryId values for the NurseryInventoryDetails table.
     * 
     * @param nurseryInventoryId nurseryInventoryId of the NurseryInventoryDetails
     */
    public void setNurseryInventoryId(Long nurseryInventoryId) {
        this.nurseryInventoryId = nurseryInventoryId;
    }

    /**
     * To Get the damageTypeId from NurseryInventoryDetails table
     * 
     * @return damageTypeId
     */
    public Long getDamageTypeId() {
        return damageTypeId;
    }

    /**
     * To set the damageTypeId values for the NurseryInventoryDetails table.
     * 
     * @param damageTypeId damageTypeId of the NurseryInventoryDetails
     */
    public void setDamageTypeId(Long pickListValueId) {
        this.damageTypeId = pickListValueId;
    }

    /**
     * To Get the damageTypePickListValue from NurseryInventoryDetails table
     * 
     * @return damageTypePickListValue
     */
    public String getDamageTypePickListValue() {
        return damageTypePickListValue;
    }

    /**
     * To set the damageTypePickListValue values for the NurseryInventoryDetails table.
     * 
     * @param damageTypePickListValue damageTypePickListValue of the NurseryInventoryDetails
     */
    public void setDamageTypePickListValue(String pickListValuePickListValue) {
        this.damageTypePickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the inventoryDamageDescriptionId from NurseryInventoryDetails table
     * 
     * @return inventoryDamageDescriptionId
     */
    public Long getInventoryDamageDescriptionId() {
        return inventoryDamageDescriptionId;
    }

    /**
     * To set the inventoryDamageDescriptionId values for the NurseryInventoryDetails table.
     * 
     * @param inventoryDamageDescriptionId inventoryDamageDescriptionId of the NurseryInventoryDetails
     */
    public void setInventoryDamageDescriptionId(Long pickListValueId) {
        this.inventoryDamageDescriptionId = pickListValueId;
    }

    /**
     * To Get the inventoryDamageDescriptionPickListValue from NurseryInventoryDetails table
     * 
     * @return inventoryDamageDescriptionPickListValue
     */
    public String getInventoryDamageDescriptionPickListValue() {
        return inventoryDamageDescriptionPickListValue;
    }

    /**
     * To set the inventoryDamageDescriptionId values for the NurseryInventoryDetails table.
     * 
     * @param inventoryDamageDescriptionPickListValue 
     *          inventoryDamageDescriptionPickListValue of the NurseryInventoryDetails
     */
    public void setInventoryDamageDescriptionPickListValue(String pickListValuePickListValue) {
        this.inventoryDamageDescriptionPickListValue = pickListValuePickListValue;
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

        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO = (NurseryInventoryDetailsDTO) o;
        if (nurseryInventoryDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInventoryDetailsDTO.getId());
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
        return "NurseryInventoryDetailsDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            ", nurseryInventory=" + getNurseryInventoryId() +
            ", damageType=" + getDamageTypeId() +
            ", damageType='" + getDamageTypePickListValue() + "'" +
            ", inventoryDamageDescription=" + getInventoryDamageDescriptionId() +
            ", inventoryDamageDescription='" + getInventoryDamageDescriptionPickListValue() + "'" +
            "}";
    }
}
