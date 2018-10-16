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
 *  ManyToOne Relation to Batch, FinancialYearSettings
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
 * A ShadeArea.
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

    @ManyToOne
    @JsonIgnoreProperties("shadeAreas")
    @JoinColumn(name="batch_id", referencedColumnName="id")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("shadeAreas")
    private FinancialYearSettings financialYearShadeArea;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfSeedlings() {
        return noOfSeedlings;
    }

    public ShadeArea noOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
        return this;
    }

    public void setNoOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
    }

    public LocalDate getDate() {
        return date;
    }

    public ShadeArea date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public ShadeArea status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDamage() {
        return damage;
    }

    public ShadeArea damage(Integer damage) {
        this.damage = damage;
        return this;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getSaplings() {
        return saplings;
    }

    public ShadeArea saplings(Integer saplings) {
        this.saplings = saplings;
        return this;
    }

    public void setSaplings(Integer saplings) {
        this.saplings = saplings;
    }
    
    public Batch getBatch() {
        return batch;
    }

    public ShadeArea batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public FinancialYearSettings getFinancialYearShadeArea() {
        return financialYearShadeArea;
    }

    public ShadeArea financialYearShadeArea(FinancialYearSettings financialYearSettings) {
        this.financialYearShadeArea = financialYearSettings;
        return this;
    }

    public void setFinancialYearShadeArea(FinancialYearSettings financialYearSettings) {
        this.financialYearShadeArea = financialYearSettings;
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
        ShadeArea shadeArea = (ShadeArea) o;
        if (shadeArea.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shadeArea.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShadeArea{" +
            "id=" + getId() +
            ", noOfSeedlings=" + getNoOfSeedlings() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", damage=" + getDamage() +
            ", saplings=" + getSaplings() +
            "}";
    }
}
