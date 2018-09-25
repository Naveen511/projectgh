/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetails Generation.
 *  Declared the table fields and data types for the NurseryInventoryDetails table.
 *  Defined the following Relation for the NurseryInventoryDetails Table :
 *  ManyToOne Relation to NurseryInventory, PickListValue
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A NurseryInventoryDetails.
 */
@Entity
@Table(name = "nursery_inventory_details")
public class NurseryInventoryDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_date")
    private LocalDate date;

    // @NotNull(message = "Quantity cannot be blank.")
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private Integer status;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryDetails")
    private NurseryInventory nurseryInventory;

    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryDamageTypes")
    private PickListValue damageType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PickListValue inventoryDamageDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public NurseryInventoryDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public NurseryInventoryDetails quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public NurseryInventoryDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public NurseryInventoryDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NurseryInventory getNurseryInventory() {
        return nurseryInventory;
    }

    public NurseryInventoryDetails nurseryInventory(NurseryInventory nurseryInventory) {
        this.nurseryInventory = nurseryInventory;
        return this;
    }

    public void setNurseryInventory(NurseryInventory nurseryInventory) {
        this.nurseryInventory = nurseryInventory;
    }

    public PickListValue getDamageType() {
        return damageType;
    }

    public NurseryInventoryDetails damageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
        return this;
    }

    public void setDamageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
    }

    public PickListValue getInventoryDamageDescription() {
        return inventoryDamageDescription;
    }

    public NurseryInventoryDetails inventoryDamageDescription(PickListValue pickListValue) {
        this.inventoryDamageDescription = pickListValue;
        return this;
    }

    public void setInventoryDamageDescription(PickListValue pickListValue) {
        this.inventoryDamageDescription = pickListValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NurseryInventoryDetails nurseryInventoryDetails = (NurseryInventoryDetails) o;
        if (nurseryInventoryDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInventoryDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryInventoryDetails{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
