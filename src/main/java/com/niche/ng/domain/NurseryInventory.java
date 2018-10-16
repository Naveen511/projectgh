/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/31/08
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Nursery inventory of seeds and cover
 *  Declared the table fields and data types for the damage table.
 *  Defined the following Relation for the damage table :
 *  ManyToOne Relation to PickListValue
 *  OneToMany Relation to NurseryInventoryDetails
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
 * A NurseryInventory.
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

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "damage_quantity")
    private Integer damageQuantity;

    @ManyToOne
    @JsonIgnoreProperties("nurseryInventorys")
    private Nursery nurserys;

    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryVarietys")
    private PickListValue pickListVariety;

    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryCategorys")
    private PickListValue pickListCategory;

    @ManyToOne
    @JsonIgnoreProperties("nurseryInventoryQuantityTypes")
    private PickListValue quantityType;

    @OneToMany(mappedBy = "nurseryInventory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventoryDetails> nurseryInventoryDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public NurseryInventory currentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
        return this;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getAddedQuantity() {
        return addedQuantity;
    }

    public NurseryInventory addedQuantity(Integer addedQuantity) {
        this.addedQuantity = addedQuantity;
        return this;
    }

    public void setAddedQuantity(Integer addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public Integer getConsumedQuantity() {
        return consumedQuantity;
    }

    public NurseryInventory consumedQuantity(Integer consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
        return this;
    }

    public void setConsumedQuantity(Integer consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    public String getDescription() {
        return description;
    }

    public NurseryInventory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public NurseryInventory status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    public NurseryInventory damageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
        return this;
    }

    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    public Nursery getNurserys() {
        return nurserys;
    }

    public NurseryInventory nurserys(Nursery nursery) {
        this.nurserys = nursery;
        return this;
    }

    public void setNurserys(Nursery nursery) {
        this.nurserys = nursery;
    }

    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    public NurseryInventory pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    public NurseryInventory pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    public Set<NurseryInventoryDetails> getNurseryInventoryDetails() {
        return nurseryInventoryDetails;
    }

    public NurseryInventory nurseryInventoryDetails(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDetails = nurseryInventoryDetails;
        return this;
    }

    public NurseryInventory addNurseryInventoryDetails(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDetails.add(nurseryInventoryDetails);
        nurseryInventoryDetails.setNurseryInventory(this);
        return this;
    }

    public NurseryInventory removeNurseryInventoryDetails(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDetails.remove(nurseryInventoryDetails);
        nurseryInventoryDetails.setNurseryInventory(null);
        return this;
    }

    public void setNurseryInventoryDetails(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDetails = nurseryInventoryDetails;
    }

    public PickListValue getQuantityType() {
        return quantityType;
    }

    public NurseryInventory quantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
        return this;
    }

    public void setQuantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
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
        NurseryInventory nurseryInventory = (NurseryInventory) o;
        if (nurseryInventory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInventory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
