/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDTO and
                            declared the table fields, data types for NurseryInventory table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryInventory entity.
 * 
 * NurseryInventoryDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class NurseryInventoryDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Integer currentQuantity;

    private Integer addedQuantity;

    private Integer consumedQuantity;

    @Size(max = 255, message = "Description should not exist 255 character.")
    private String description;

    private Integer status;

    private Integer damageQuantity;

    @NotNull(message = "Nursery cannot be blank.")
    private Long nurserysId;

    private String nurserysNurseryName;

    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    // @NotNull(message = "Category cannot be blank.")
    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    @NotNull(message = "Quantity Type cannot be blank.")
    private Long quantityTypeId;

    private String quantityTypePickListValue;

    /**
     * To Get the Id from NurseryInventory table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryInventory table.
     * 
     * @param id id of the NurseryInventory
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from NurseryInventory table
     * 
     * @return currentQuantity
     */
    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values for the NurseryInventory table.
     * 
     * @param currentQuantity currentQuantity of the NurseryInventory
     */
    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from NurseryInventory table
     * 
     * @return addedQuantity
     */
    public Integer getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values for the NurseryInventory table.
     * 
     * @param addedQuantity addedQuantity of the NurseryInventory
     */
    public void setAddedQuantity(Integer addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from NurseryInventory table
     * 
     * @return consumedQuantity
     */
    public Integer getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values for the NurseryInventory table.
     * 
     * @param consumedQuantity consumedQuantity of the NurseryInventory
     */
    public void setConsumedQuantity(Integer consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from NurseryInventory table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryInventory table.
     * 
     * @param description description of the NurseryInventory
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryInventory table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryInventory table.
     * 
     * @param status status of the NurseryInventory
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the damageQuantity from NurseryInventory table
     * 
     * @return damageQuantity
     */
    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To set the damageQuantity values for the NurseryInventory table.
     * 
     * @param damageQuantity damageQuantity of the NurseryInventory
     */
    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To Get the nurserysId from NurseryInventory table
     * 
     * @return nurserysId
     */
    public Long getNurserysId() {
        return nurserysId;
    }

    /**
     * To set the nurserysId values for the NurseryInventory table.
     * 
     * @param nurserysId nurserysId of the NurseryInventory
     */
    public void setNurserysId(Long nurseryId) {
        this.nurserysId = nurseryId;
    }

    /**
     * To Get the nurserysNurseryName from NurseryInventory table
     * 
     * @return nurserysNurseryName
     */
    public String getNurserysNurseryName() {
        return nurserysNurseryName;
    }

    /**
     * To set the nurserysNurseryName values for the NurseryInventory table.
     * 
     * @param nurserysNurseryName nurserysNurseryName of the NurseryInventory
     */
    public void setNurserysNurseryName(String nurseryNurseryName) {
        this.nurserysNurseryName = nurseryNurseryName;
    }

    /**
     * To Get the pickListVarietyId from NurseryInventory table
     * 
     * @return pickListVarietyId
     */
    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the NurseryInventory table.
     * 
     * @param pickListVarietyId pickListVarietyId of the NurseryInventory
     */
    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    /**
     * To Get the pickListVarietyPickListValue from NurseryInventory table
     * 
     * @return pickListVarietyPickListValue
     */
    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    /**
     * To set the pickListVarietyPickListValue values for the NurseryInventory table.
     * 
     * @param pickListVarietyPickListValue pickListVarietyPickListValue of the NurseryInventory
     */
    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListCategoryId from NurseryInventory table
     * 
     * @return pickListCategoryId
     */
    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the NurseryInventory table.
     * 
     * @param pickListCategoryId pickListCategoryId of the NurseryInventory
     */
    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    /**
     * To Get the pickListCategoryPickListValue from NurseryInventory table
     * 
     * @return pickListCategoryPickListValue
     */
    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    /**
     * To set the pickListCategoryPickListValue values for the NurseryInventory table.
     * 
     * @param pickListCategoryPickListValue pickListCategoryPickListValue of the NurseryInventory
     */
    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the quantityTypeId from NurseryInventory table
     * 
     * @return quantityTypeId
     */
    public Long getQuantityTypeId() {
        return quantityTypeId;
    }

    /**
     * To set the idquantityTypeId values for the NurseryInventory table.
     * 
     * @param quantityTypeId quantityTypeId of the NurseryInventory
     */
    public void setQuantityTypeId(Long pickListValueId) {
        this.quantityTypeId = pickListValueId;
    }

    /**
     * To Get the quantityTypePickListValue from NurseryInventory table
     * 
     * @return quantityTypePickListValue
     */
    public String getQuantityTypePickListValue() {
        return quantityTypePickListValue;
    }

    /**
     * To set the quantityTypePickListValue values for the NurseryInventory table.
     * 
     * @param quantityTypePickListValue quantityTypePickListValue of the NurseryInventory
     */
    public void setQuantityTypePickListValue(String pickListValuePickListValue) {
        this.quantityTypePickListValue = pickListValuePickListValue;
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

        NurseryInventoryDTO nurseryInventoryDTO = (NurseryInventoryDTO) o;
        if (nurseryInventoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInventoryDTO.getId());
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
        return "NurseryInventoryDTO{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", damageQuantity=" + getDamageQuantity() +
            ", nurserys=" + getNurserysId() +
            ", nurserys='" + getNurserysNurseryName() + "'" +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", quantityType=" + getQuantityTypeId() +
            ", quantityType='" + getQuantityTypePickListValue() + "'" +
            "}";
    }
}
