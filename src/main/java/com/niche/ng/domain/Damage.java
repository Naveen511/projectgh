/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Damage  Generation and declared 
 *                        the table fields, data types for Damage table. 
 *  Relation for Damage : ManyToOne Relation
 *  ManyToOne Relation : Batch, PickListValue, FinancialYearSettings
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
 * Damage Domain Class
 * 
 * Damage class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "damage")
public class Damage extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_of_quantity", nullable = false)
    private Long noOfQuantity;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - damages
     * Table Name    : Connects the damage Table to batch Table
     * To get the values from the batch table.
     */
    @ManyToOne
    @JsonIgnoreProperties("damages")
    @JoinColumn(name="batch_id", referencedColumnName="id")
    private Batch batch;

    /**
     * Relation Name : ManyToOne - damageDescriptions
     * Table Name    : Connects the damage Table to PickListValue Table
     * To get the damage description details belongs to damage.
     */
    @ManyToOne
    @JsonIgnoreProperties("damageDescriptions")
    private PickListValue description;

    /**
     * Relation Name : ManyToOne - pickListValueDamageAreas
     * Table Name    : Connects the damage Table to PickListValue Table
     * To get the damage description details belongs to damage area.
     */
    @ManyToOne
    @JsonIgnoreProperties("pickListValueDamageAreas")
    private PickListValue damageArea;

    /**
     * Relation Name : ManyToOne - damages
     * Table Name    : Connects the damage Table to FinancialYearSettings Table
     * To get the FinancialYearSettings belongs to damage area.
     */
    @ManyToOne
    @JsonIgnoreProperties("damages")
    private FinancialYearSettings financialYearDamage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from damage table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for damage table
     * 
     * @param id id for the damage table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the noOfQuantity from damage table.
     * 
     * @return noOfQuantity
     */
    public Long getNoOfQuantity() {
        return noOfQuantity;
    }

    /**
     * To set the noOfQuantity values for the damage table.
     * 
     * @param noOfQuantity number of quantity in damage
     * @return this
     */
    public Damage noOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
        return this;
    }

    /**
     * To set the noOfQuantity values for the damage table.
     * 
     * @param noOfQuantity number of quantity in damage
     */
    public void setNoOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    /**
     * To Get the noOfQuantity from damage table.
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the damage table.
     * 
     * @param date damage date
     * @return this
     */
    public Damage date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To set the date values for the damage table.
     * 
     * @param date damage date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from damage table.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the damage table.
     * 
     * @param status status in damage
     * @return this
     */
    public Damage status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status values for the damage table.
     * 
     * @param status status in damage
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the batch from damage table.
     * 
     * @return batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * To set the batch values for the damage table.
     * 
     * @param batch batch in damage
     * @return this
     */
    public Damage batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    /**
     * To set the batch values for the damage table.
     * 
     * @param batch batch in damage
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * To Get the description from damage table.
     * 
     * @return description
     */
    public PickListValue getDescription() {
        return description;
    }

    /**
     * To set the description values for the damage table.
     * 
     * @param pickListValue pickListValue in damage
     * @return this
     */
    public Damage description(PickListValue pickListValue) {
        this.description = pickListValue;
        return this;
    }

    /**
     * To set the description values for the damage table.
     * 
     * @param pickListValue pickListValue in damage
     */
    public void setDescription(PickListValue pickListValue) {
        this.description = pickListValue;
    }

    /**
     * To Get the damageArea from damage table.
     * 
     * @return damageArea
     */
    public PickListValue getDamageArea() {
        return damageArea;
    }

    /**
     * To set the damageArea values for the damage table.
     * 
     * @param pickListValue pickListValue in damage
     * @return this
     */
    public Damage damageArea(PickListValue pickListValue) {
        this.damageArea = pickListValue;
        return this;
    }

    /**
     * To set the damageArea values for the damage table.
     * 
     * @param pickListValue pickListValue in damage
     */
    public void setDamageArea(PickListValue pickListValue) {
        this.damageArea = pickListValue;
    }

    /**
     * To Get the financialYearDamage from damage table.
     * 
     * @return financialYearDamage
     */
    public FinancialYearSettings getFinancialYearDamage() {
        return financialYearDamage;
    }

    /**
     * To set the financialYear values for the damage table.
     * 
     * @param financialYearSettings financial Year Settings in damage
     * @return this
     */
    public Damage financialYearDamage(FinancialYearSettings financialYearSettings) {
        this.financialYearDamage = financialYearSettings;
        return this;
    }

    /**
     * To set the financialYear values for the damage table.
     * 
     * @param financialYearSettings financial Year Settings in damage
     */
    public void setFinancialYearDamage(FinancialYearSettings financialYearSettings) {
        this.financialYearDamage = financialYearSettings;
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
        Damage damage = (Damage) o;
        if (damage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), damage.getId());
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
        return "Damage{" +
            "id=" + getId() +
            ", noOfQuantity=" + getNoOfQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
