/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryInventory entity.
 */
public class NurseryInventoryDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Integer currentQuantity;

    private Integer addedQuantity;

    private Integer consumedQuantity;

    @Size(max = 255)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getAddedQuantity() {
        return addedQuantity;
    }

    public void setAddedQuantity(Integer addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public Integer getConsumedQuantity() {
        return consumedQuantity;
    }

    public void setConsumedQuantity(Integer consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
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

    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    public Long getNurserysId() {
        return nurserysId;
    }

    public void setNurserysId(Long nurseryId) {
        this.nurserysId = nurseryId;
    }

    public String getNurserysNurseryName() {
        return nurserysNurseryName;
    }

    public void setNurserysNurseryName(String nurseryNurseryName) {
        this.nurserysNurseryName = nurseryNurseryName;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
