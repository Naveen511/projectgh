/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/31/08
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description              : This file performs Nursery inventory of seeds and cover
                                    and declared the table fields, data types for NurseryInventory table.
 *  Relation for NurseryInventory : OneToMany Relation and ManyToOne Relation
 *  OneToMany Relation            : NurseryInventoryDetails
 *  ManyToOne Relation            : Nursery, PickList Variety, and Picklist Category
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A NurseryInventory Domain Class
 * 
 * Batch class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "nursery_inventory")
public class NurseryInventory extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "current_quantity")
    private Integer currentQuantity;

    @Column(name = "added_quantity")
    private Integer addedQuantity;

    @Column(name = "consumed_quantity")
    private Integer consumedQuantity;

    @Size(max = 255, message = "Description should not exist 255 character.")
    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "damage_quantity")
    private Integer damageQuantity;

    /**
     * Relation Name : ManyToOne - nurseryInventorys
     * Table Name    : Connect the NurseryInventory Table to Nursery Table
     * To get the list of nurserys form the nursery table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventorys")
    private Nursery nurserys;

    /**
     * Relation Name : ManyToOne - nurseryInventoryVarietys
     * Table Name    : Connect the NurseryInventory Table to PickListValue Table
     * To get the list of Varietys form the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryVarietys")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - nurseryInventoryCategorys
     * Table Name    : Connect the NurseryInventory Table to PickListValue Table
     * To get the list of category form the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryCategorys")
    private PickListValue pickListCategory;

    /**
     * Relation Name : ManyToOne - nurseryInventoryQuantityTypes
     * Table Name    : Connect the NurseryInventory Table to PickListValue Table
     * To get the list of quantity types form the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryQuantityTypes")
    private PickListValue quantityType;

    /**
     * Relation Name : OneToMany - nurseryInventoryDetails
     * Table Name    : Connect the NurseryInventory Table to NurseryInventoryDetails Table
     * Nursery Inventory id is mapped to the nursery inventory details
     */
    @OneToMany(mappedBy = "nurseryInventory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventoryDetails> nurseryInventoryDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from NurseryInventory table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for NurseryInventory table
     *
     * @param id id of the NurseryInventory Table
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
     * To set the currentQuantity for NurseryInventory table
     * 
     * @param currentQuantity currentQuantity of the NurseryInventory
     * @return this
     */
    public NurseryInventory currentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
        return this;
    }

    /**
     * To set the currentQuantity for NurseryInventory table
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
     * To set the addedQuantity for NurseryInventory table
     * 
     * @param addedQuantity addedQuantity of the NurseryInventory
     * @return this
     */
    public NurseryInventory addedQuantity(Integer addedQuantity) {
        this.addedQuantity = addedQuantity;
        return this;
    }

    /**
     * To set the addedQuantity for NurseryInventory table
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
     * To set the consumedQuantity for NurseryInventory table
     * 
     * @param consumedQuantity consumedQuantity of the NurseryInventory
     * @return this
     */
    public NurseryInventory consumedQuantity(Integer consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
        return this;
    }

    /**
     * To set the consumedQuantity for NurseryInventory table
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
     * To set the description for NurseryInventory table
     * 
     * @param description description of the NurseryInventory
     * @return this
     */
    public NurseryInventory description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To set the description for NurseryInventory table
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
     * To set the status for NurseryInventory table
     * 
     * @param status status of the NurseryInventory
     * @return this
     */
    public NurseryInventory status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status for NurseryInventory table
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
     * To set the damageQuantity for NurseryInventory table
     * 
     * @param damageQuantity damageQuantity of the NurseryInventory
     * @return this
     */
    public NurseryInventory damageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
        return this;
    }

    /**
     * To set the damageQuantity for NurseryInventory table
     * 
     * @param damageQuantity damageQuantity of the NurseryInventory
     */
    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To Get the nurserys from NurseryInventory table
     * 
     * @return nurserys
     */
    public Nursery getNurserys() {
        return nurserys;
    }

    /**
     * To set the nurserys for NurseryInventory table
     * 
     * @param nurserys nurserys of the NurseryInventory
     * @return this
     */
    public NurseryInventory nurserys(Nursery nursery) {
        this.nurserys = nursery;
        return this;
    }

    /**
     * To set the nurserys for NurseryInventory table
     * 
     * @param nurserys nurserys of the NurseryInventory
     */
    public void setNurserys(Nursery nursery) {
        this.nurserys = nursery;
    }

    /**
     * To Get the pickListVariety from NurseryInventory table
     * 
     * @return pickListVariety
     */
    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    /**
     * To set the pickListVariety for NurseryInventory table
     * 
     * @param pickListVariety pickListVariety of the NurseryInventory
     * @return this
     */
    public NurseryInventory pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    /**
     * To set the pickListVariety for NurseryInventory table
     * 
     * @param pickListVariety pickListVariety of the NurseryInventory
     */
    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    /**
     * To Get the pickListCategory from NurseryInventory table
     * 
     * @return pickListCategory
     */
    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    /**
     * To set the pickListCategory for NurseryInventory table
     * 
     * @param pickListCategory pickListCategory of the NurseryInventory
     * @return this
     */
    public NurseryInventory pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    /**
     * To set the pickListCategory for NurseryInventory table
     * 
     * @param pickListCategory pickListCategory of the NurseryInventory
     */
    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    /**
     * To Get the nurseryInventoryDetails from NurseryInventory table
     * 
     * @return nurseryInventoryDetails
     */
    public Set<NurseryInventoryDetails> getNurseryInventoryDetails() {
        return nurseryInventoryDetails;
    }

    /**
     * To set the nurseryInventoryDetails for NurseryInventory table
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails of the NurseryInventory
     * @return this
     */
    public NurseryInventory nurseryInventoryDetails(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDetails = nurseryInventoryDetails;
        return this;
    }

    /**
     * To set the nurseryInventoryDetails for NurseryInventory table
     * 
     * @param nurseryInventoryDetails frnurseryInventoryDetailsomDate of the NurseryInventory
     * @return this
     */
    public NurseryInventory addNurseryInventoryDetails(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDetails.add(nurseryInventoryDetails);
        nurseryInventoryDetails.setNurseryInventory(this);
        return this;
    }

    /**
     * To set the nurseryInventoryDetails for NurseryInventory table
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails of the NurseryInventory
     * @return this
     */
    public NurseryInventory removeNurseryInventoryDetails(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDetails.remove(nurseryInventoryDetails);
        nurseryInventoryDetails.setNurseryInventory(null);
        return this;
    }

    /**
     * To set the nurseryInventoryDetails for NurseryInventory table
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails of the NurseryInventory
     */
    public void setNurseryInventoryDetails(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDetails = nurseryInventoryDetails;
    }

    /**
     * To Get the quantityType from NurseryInventory table
     * 
     * @return quantityType
     */
    public PickListValue getQuantityType() {
        return quantityType;
    }

    /**
     * To set the quantityType for NurseryInventory table
     * 
     * @param quantityType quantityType of the NurseryInventory
     * @return this
     */
    public NurseryInventory quantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
        return this;
    }

    /**
     * To set the quantityType for NurseryInventory table
     * 
     * @param quantityType quantityType of the NurseryInventory
     */
    public void setQuantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
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
        NurseryInventory nurseryInventory = (NurseryInventory) o;
        if (nurseryInventory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInventory.getId());
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
        return "NurseryInventory{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", damageQuantity=" + getDamageQuantity() +
            "}";
    }
}
