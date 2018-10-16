/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingDetailsDTO and
                            declared the table fields, data types for CoverFillingDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoverFillingDetails entity.
 * 
 * CoverFillingDetailsDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class CoverFillingDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    // @NotBlank(message = "Quantity cannot be blank.")
    private Integer quantity;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private String description;

    private Long coverFillingId;

    @NotNull(message = "Damage Type cannot be blank.")
    private Long damageTypeId;

    private String damageTypePickListValue;

    private Long coverFillingDamageDescriptionId;

    private String coverFillingDamageDescriptionPickListValue;

    /**
     * To Get the Id from coverFillingDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for coverFillingDetails table
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from coverFillingDetails table
     * 
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for coverFillingDetails table
     * 
     * @param quantity quantity in table
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from coverFillingDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * To Set the date for coverFillingDetails table
     * 
     * @param date date in table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from coverFillingDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for coverFillingDetails table
     * 
     * @param status status in table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the description from coverFillingDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description from coverFillingDetails table
     * 
     * @param description description in table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To get the coverFillingId from coverFillingDetails table
     * 
     * @return coverFillingId 
     */
    public Long getCoverFillingId() {
        return coverFillingId;
    }

    /**
     * To set the coverFillingId from coverFillingDetails table
     * 
     * @param coverFillingId coverFillingId in table
     */
    public void setCoverFillingId(Long coverFillingId) {
        this.coverFillingId = coverFillingId;
    }

    /**
     * To get the damageTypeId from coverFillingDetails table
     * 
     * @return damageTypeId 
     */
    public Long getDamageTypeId() {
        return damageTypeId;
    }

    /**
     * To set the damageTypeId from coverFillingDetails table
     * 
     * @param damageTypeId damageTypeId in table
     */
    public void setDamageTypeId(Long pickListValueId) {
        this.damageTypeId = pickListValueId;
    }

    /**
     * To get the damageTypePickListValue from coverFillingDetails table
     * 
     * @return damageTypePickListValue 
     */
    public String getDamageTypePickListValue() {
        return damageTypePickListValue;
    }

    /**
     * To set the damageTypePickListValue from coverFillingDetails table
     * 
     * @param damageTypePickListValue damageTypePickListValue in table
     */
    public void setDamageTypePickListValue(String pickListValuePickListValue) {
        this.damageTypePickListValue = pickListValuePickListValue;
    }

    /**
     * To get the coverFillingDamageDescriptionId from coverFillingDetails table
     * 
     * @return coverFillingDamageDescriptionId 
     */
    public Long getCoverFillingDamageDescriptionId() {
        return coverFillingDamageDescriptionId;
    }

    /**
     * To set the coverFillingDamageDescriptionId from coverFillingDetails table
     * 
     * @param coverFillingDamageDescriptionId in table
     */
    public void setCoverFillingDamageDescriptionId(Long pickListValueId) {
        this.coverFillingDamageDescriptionId = pickListValueId;
    }

    /**
     * To get the coverFillingDamageDescriptionPickListValue from coverFillingDetails table
     * 
     * @return coverFillingDamageDescriptionPickListValue 
     */
    public String getCoverFillingDamageDescriptionPickListValue() {
        return coverFillingDamageDescriptionPickListValue;
    }

    /**
     * To set the coverFillingDamageDescriptionPickListValue from coverFillingDetails table
     * 
     * @param coverFillingDamageDescriptionPickListValue in table
     */
    public void setCoverFillingDamageDescriptionPickListValue(String pickListValuePickListValue) {
        this.coverFillingDamageDescriptionPickListValue = pickListValuePickListValue;
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

        CoverFillingDetailsDTO coverFillingDetailsDTO = (CoverFillingDetailsDTO) o;
        if (coverFillingDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFillingDetailsDTO.getId());
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
        return "CoverFillingDetailsDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            ", coverFilling=" + getCoverFillingId() +
            ", damageType=" + getDamageTypeId() +
            ", damageType='" + getDamageTypePickListValue() + "'" +
            ", coverFillingDamageDescription=" + getCoverFillingDamageDescriptionId() +
            ", coverFillingDamageDescription='" + getCoverFillingDamageDescriptionPickListValue() + "'" +
            "}";
    }
}
