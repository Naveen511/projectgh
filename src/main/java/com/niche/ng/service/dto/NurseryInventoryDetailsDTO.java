/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryInventoryDetails entity.
 */
public class NurseryInventoryDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    @NotNull(message = "Quantity cannot be blank.")
    private Integer quantity;

    private Integer status;

    @Size(max = 255)
    private String description;

    private Long nurseryInventoryId;

    private Long damageTypeId;

    private String damageTypePickListValue;

    private Long inventoryDamageDescriptionId;

    private String inventoryDamageDescriptionPickListValue;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNurseryInventoryId() {
        return nurseryInventoryId;
    }

    public void setNurseryInventoryId(Long nurseryInventoryId) {
        this.nurseryInventoryId = nurseryInventoryId;
    }

    public Long getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(Long pickListValueId) {
        this.damageTypeId = pickListValueId;
    }

    public String getDamageTypePickListValue() {
        return damageTypePickListValue;
    }

    public void setDamageTypePickListValue(String pickListValuePickListValue) {
        this.damageTypePickListValue = pickListValuePickListValue;
    }

    public Long getInventoryDamageDescriptionId() {
        return inventoryDamageDescriptionId;
    }

    public void setInventoryDamageDescriptionId(Long pickListValueId) {
        this.inventoryDamageDescriptionId = pickListValueId;
    }

    public String getInventoryDamageDescriptionPickListValue() {
        return inventoryDamageDescriptionPickListValue;
    }

    public void setInventoryDamageDescriptionPickListValue(String pickListValuePickListValue) {
        this.inventoryDamageDescriptionPickListValue = pickListValuePickListValue;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
