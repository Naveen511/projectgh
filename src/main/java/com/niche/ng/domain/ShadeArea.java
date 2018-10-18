/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeArea Generation.
 *  Declared the table fields and data types for the ShadeArea table.
 *  Defined the following Relation for the ShadeArea Table :
 *  ManyToOne Relation  : Batch, FinancialYearSettings
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
 * ShadeArea Domain Class
 * 
 * ShadeArea class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "shade_area")
public class ShadeArea extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_of_seedlings", nullable = false)
    private Long noOfSeedlings;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Column(name = "status")
    private Integer status;

    @Column(name = "damage")
    private Integer damage;

    @Column(name = "saplings")
    private Integer saplings;

    @Column(name = "round")
    private Integer round;

    /**
     * Relation Name : ManyToOne - batch
     * Table Name    : Connect the ShadeArea Table to Batch Table
     * To get the Batch.
     */
    @ManyToOne
    @JsonIgnoreProperties("shadeAreas")
    @JoinColumn(name="batch_id", referencedColumnName="id")
    private Batch batch;

    /**
     * Relation Name : ManyToOne - financialYearShadeArea
     * Table Name    : Connect the ShadeArea Table to FinancialYearSettings Table
     * To get the financialYearSettings.
     */
    @ManyToOne
    @JsonIgnoreProperties("shadeAreas")
    private FinancialYearSettings financialYearShadeArea;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from shadeArea table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for shadeArea table
     *
     * @param id the id in shadeArea table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the noOfSeedlings from shadeArea table
     * 
     * @return noOfSeedlings
     */
    public Long getNoOfSeedlings() {
        return noOfSeedlings;
    }

    /**
     * To Set the noOfSeedlings for shadeArea table
     *
     * @param noOfSeedlings the noOfSeedlings in shadeArea table
     * @return this
     */
    public ShadeArea noOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
        return this;
    }

    /**
     * To Set the noOfSeedlings for shadeArea table
     *
     * @param noOfSeedlings the noOfSeedlings in shadeArea table
     */
    public void setNoOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
    }

    /**
     * To Get the date from shadeArea table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for shadeArea table
     *
     * @param date the date in shadeArea table
     * @return this
     */
    public ShadeArea date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for shadeArea table
     *
     * @param date the date in shadeArea table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from shadeArea table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for shadeArea table
     *
     * @param status the status in shadeArea table
     * @return this
     */
    public ShadeArea status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for shadeArea table
     *
     * @param status the status in shadeArea table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the damage from shadeArea table
     * 
     * @return damage
     */
    public Integer getDamage() {
        return damage;
    }

    /**
     * To Set the damage for shadeArea table
     *
     * @param damage the damage in shadeArea table
     * @return this
     */
    public ShadeArea damage(Integer damage) {
        this.damage = damage;
        return this;
    }

    /**
     * To Set the damage for shadeArea table
     *
     * @param damage the damage in shadeArea table
     */
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    /**
     * To Get the saplings from shadeArea table
     * 
     * @return saplings
     */
    public Integer getSaplings() {
        return saplings;
    }

    /**
     * To Set the saplings for shadeArea table
     *
     * @param saplings the saplings in shadeArea table
     * @return this
     */
    public ShadeArea saplings(Integer saplings) {
        this.saplings = saplings;
        return this;
    }

    /**
     * To Set the saplings for shadeArea table
     *
     * @param saplings the saplings in shadeArea table
     */
    public void setSaplings(Integer saplings) {
        this.saplings = saplings;
    }

    /**
     * To Get the round from shadeArea table
     * 
     * @return round
     */
    public Integer getRound() {
        return round;
    }

    /**
     * To Set the round for shadeArea table
     *
     * @param round the round in shadeArea table
     * @return this
     */
    public ShadeArea round(Integer round) {
        this.round = round;
        return this;
    }

    /**
     * To Set the round for shadeArea table
     *
     * @param round the round in shadeArea table
     */
    public void setRound(Integer round) {
        this.round = round;
    }

    /**
     * To Get the batch from shadeArea table
     * 
     * @return batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * To Set the batch for shadeArea table
     *
     * @param batch the batch in shadeArea table
     * @return this
     */
    public ShadeArea batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    /**
     * To Set the batch for shadeArea table
     *
     * @param batch the batch in shadeArea table
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * To Get the financialYearShadeArea from shadeArea table
     * 
     * @return financialYearShadeArea
     */
    public FinancialYearSettings getFinancialYearShadeArea() {
        return financialYearShadeArea;
    }

    /**
     * To Set the financialYearShadeArea for shadeArea table
     *
     * @param financialYearSettings the financialYearSettings in shadeArea table
     * @return this
     */
    public ShadeArea financialYearShadeArea(FinancialYearSettings financialYearSettings) {
        this.financialYearShadeArea = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearSetting for shadeArea table
     *
     * @param financialYearSettings the financialYearSettings in shadeArea table
     */
    public void setFinancialYearShadeArea(FinancialYearSettings financialYearSettings) {
        this.financialYearShadeArea = financialYearSettings;
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
        ShadeArea shadeArea = (ShadeArea) o;
        if (shadeArea.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shadeArea.getId());
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
        return "ShadeArea{" +
            "id=" + getId() +
            ", noOfSeedlings=" + getNoOfSeedlings() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", damage=" + getDamage() +
            ", saplings=" + getSaplings() +
            ", round=" + getRound() +
            "}";
    }
}
