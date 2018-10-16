/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/31/08
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetails
                            and declared the table fields,
                            data types for NurseryInventory table.
 *  Relation            : ManyToOne Relation
 *  ManyToOne Relation  : NurseryInventory, Pick List Value Table
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
 * A NurseryInventoryDetails Domain Class
 * 
 * Batch class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    @Size(max = 255, message = "Description should not exist 255 character.")
    @Column(name = "description", length = 255)
    private String description;

    /**
     * Relation Name : ManyToOne - nurseryInventoryDetails
     * Table Name    : Connects the NurseryInventoryDetails Table to Nursery Table
     * To get the list of nursery inventory's form the NurseryInventory table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryDetails")
    private NurseryInventory nurseryInventory;

    /**
     * Relation Name : ManyToOne - nurseryInventoryDamageTypes
     * Table Name    : Connects the NurseryInventoryDetails Table to PickListValue Table
     * To get the list of damage type form the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryDamageTypes")
    private PickListValue damageType;

    /**
     * Relation Name : ManyToOne - nurseryInventoryDamageDescs
     * Table Name    : Connects the NurseryInventoryDetails Table to PickListValue Table
     * To get the list of damage description form the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryDamageDescs")
    private PickListValue inventoryDamageDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from NurseryInventoryDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for NurseryInventoryDetails table
     *
     * @param id id of the NurseryInventoryDetails Table
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
     * To set the date for NurseryInventoryDetails table
     * 
     * @param date date of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for NurseryInventoryDetails table
     *
     * @param date date of the NurseryInventoryDetails Table
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
     * To set the quantity for NurseryInventoryDetails table
     * 
     * @param quantity quantity of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for NurseryInventoryDetails table
     *
     * @param quantity quantity of the NurseryInventoryDetails Table
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
     * To set the status for NurseryInventoryDetails table
     * 
     * @param status status of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for NurseryInventoryDetails table
     *
     * @param status status of the NurseryInventoryDetails Table
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
     * To set the description for NurseryInventoryDetails table
     * 
     * @param description description of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for NurseryInventoryDetails table
     *
     * @param description description of the NurseryInventoryDetails Table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the nurseryInventory from NurseryInventoryDetails table
     * 
     * @return nurseryInventory
     */
    public NurseryInventory getNurseryInventory() {
        return nurseryInventory;
    }

    /**
     * To set the nurseryInventory for NurseryInventoryDetails table
     * 
     * @param nurseryInventory nurseryInventory of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails nurseryInventory(NurseryInventory nurseryInventory) {
        this.nurseryInventory = nurseryInventory;
        return this;
    }

    /**
     * To Set the nurseryInventory for NurseryInventoryDetails table
     *
     * @param nurseryInventory nurseryInventory of the NurseryInventoryDetails Table
     */
    public void setNurseryInventory(NurseryInventory nurseryInventory) {
        this.nurseryInventory = nurseryInventory;
    }

    /**
     * To Get the damageType from NurseryInventoryDetails table
     * 
     * @return damageType
     */
    public PickListValue getDamageType() {
        return damageType;
    }

    /**
     * To set the damageType for NurseryInventoryDetails table
     * 
     * @param damageType damageType of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails damageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
        return this;
    }

    /**
     * To Set the damageType for NurseryInventoryDetails table
     *
     * @param damageType damageType of the NurseryInventoryDetails Table
     */
    public void setDamageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
    }

    /**
     * To Get the inventoryDamageDescription from NurseryInventoryDetails table
     * 
     * @return inventoryDamageDescription
     */
    public PickListValue getInventoryDamageDescription() {
        return inventoryDamageDescription;
    }

    /**
     * To set the inventoryDamageDescription for NurseryInventoryDetails table
     * 
     * @param inventoryDamageDescription inventoryDamageDescription of the NurseryInventoryDetails
     * @return this
     */
    public NurseryInventoryDetails inventoryDamageDescription(PickListValue pickListValue) {
        this.inventoryDamageDescription = pickListValue;
        return this;
    }

    /**
     * To Set the inventoryDamageDescription for NurseryInventoryDetails table
     *
     * @param inventoryDamageDescription inventoryDamageDescription of the NurseryInventoryDetails Table
     */
    public void setInventoryDamageDescription(PickListValue pickListValue) {
        this.inventoryDamageDescription = pickListValue;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
        NurseryInventoryDetails nurseryInventoryDetails = (NurseryInventoryDetails) o;
        if (nurseryInventoryDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInventoryDetails.getId());
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
        return "NurseryInventoryDetails{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
