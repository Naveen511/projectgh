/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Damage Generation.
 *  Declared the table fields and data types for the damage table.
 *  Defined the following Relation for the damage table :
 *  ManyToOne Relation to Batch, PickListValue, FinancialYearSettings
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
 * A Damage.
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

    @ManyToOne
    @JsonIgnoreProperties("damages")
    @JoinColumn(name="batch_id", referencedColumnName="id")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("damageDescriptions")
    private PickListValue description;

    @ManyToOne
    @JsonIgnoreProperties("pickListValueDamageAreas")
    private PickListValue damageArea;

    @ManyToOne
    @JsonIgnoreProperties("damages")
    private FinancialYearSettings financialYearDamage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoOfQuantity() {
        return noOfQuantity;
    }

    public Damage noOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
        return this;
    }

    public void setNoOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public Damage date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public Damage status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Batch getBatch() {
        return batch;
    }

    public Damage batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public PickListValue getDescription() {
        return description;
    }

    public Damage description(PickListValue pickListValue) {
        this.description = pickListValue;
        return this;
    }

    public void setDescription(PickListValue pickListValue) {
        this.description = pickListValue;
    }
    public PickListValue getDamageArea() {
        return damageArea;
    }

    public Damage damageArea(PickListValue pickListValue) {
        this.damageArea = pickListValue;
        return this;
    }

    public void setDamageArea(PickListValue pickListValue) {
        this.damageArea = pickListValue;
    }

    public FinancialYearSettings getFinancialYearDamage() {
        return financialYearDamage;
    }

    public Damage financialYearDamage(FinancialYearSettings financialYearSettings) {
        this.financialYearDamage = financialYearSettings;
        return this;
    }

    public void setFinancialYearDamage(FinancialYearSettings financialYearSettings) {
        this.financialYearDamage = financialYearSettings;
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
        Damage damage = (Damage) o;
        if (damage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), damage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
