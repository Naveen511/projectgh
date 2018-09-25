/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetails Generation.
 *  Declared the table fields and data types for the NurseryStockDetails table.
 *  Defined the following Relation for the NurseryStockDetails Table :
 *  ManyToOne Relation to Batch, NurseryStock, FinancialYearSettings, PickListValue
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
 * A NurseryStockDetails.
 */
@Entity
@Table(name = "nursery_stock_details")
public class NurseryStockDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private NurseryStock nurseryStock;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private Nursery itNursery;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDamageAreas")
    private PickListValue saplingDamageArea;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private FinancialYearSettings financialYearStockDetails;

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

    public NurseryStockDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public NurseryStockDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public NurseryStockDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public NurseryStockDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Batch getBatch() {
        return batch;
    }

    public NurseryStockDetails batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public NurseryStock getNurseryStock() {
        return nurseryStock;
    }

    public NurseryStockDetails nurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
        return this;
    }

    public void setNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
    }

    public Nursery getItNursery() {
        return itNursery;
    }

    public NurseryStockDetails itNursery(Nursery nursery) {
        this.itNursery = nursery;
        return this;
    }

    public void setItNursery(Nursery nursery) {
        this.itNursery = nursery;
    }

    public PickListValue getSaplingDamageArea() {
        return saplingDamageArea;
    }

    public NurseryStockDetails saplingDamageArea(PickListValue pickListValue) {
        this.saplingDamageArea = pickListValue;
        return this;
    }

    public void setSaplingDamageArea(PickListValue pickListValue) {
        this.saplingDamageArea = pickListValue;
    }

    public FinancialYearSettings getFinancialYearStockDetails() {
        return financialYearStockDetails;
    }

    public NurseryStockDetails financialYearStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearStockDetails = financialYearSettings;
        return this;
    }

    public void setFinancialYearStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearStockDetails = financialYearSettings;
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
        NurseryStockDetails nurseryStockDetails = (NurseryStockDetails) o;
        if (nurseryStockDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStockDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryStockDetails{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
