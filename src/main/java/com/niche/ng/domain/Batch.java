/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Batch Generation.
 *  Declared the table fields and data types for the batch table.
 *  Defined the following Relation for the Batch Table :
 *  OneToMany Relation to Damage Table, Shade Area, Stock Details
 *  ManyToOne Relation to Nursery, PickList Variety, and Picklist Category,
 *       MotherBed, FinancialYearSettings
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * This Batch is implements Serializable
 * It generates the setter and getter for each  fields in table.
 * It creates the default constructor which consist of both all the
 * fields in the table and empty constructor.
 * It Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "batch")
public class Batch extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "batch_no", nullable = false) // , unique=false
    private String batchNo;

    @NotNull
    @Column(name = "batch_name", nullable = false)
    private String batchName;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @NotNull
    @Column(name = "showing_type", nullable = false)
    private Integer showingType;

    @NotNull
    @Column(name = "sowing_date", nullable = false)
    private LocalDate sowingDate;

    @Column(name = "closed_date")
    private LocalDate closedDate;

    @Column(name = "round")
    private Integer round;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : OneToMany - damages
     * It Connects the Batch Table to Damage Table
     * After Creation of the Batch, If any seeds/seedlings/saplings is damage move to Damage Table
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> damages = new HashSet<>();

    /**
     * Relation Name : OneToMany - shadeAreas
     * It Connects the Batch Table to ShadeArea Table
     * If the saplings are move to shade area. To find these saplings are coming from which batch.
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<ShadeArea> shadeAreas = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockDetails
     * It Connects the Batch Table to NurseryStockDetails Table
     * If the saplings distributed to the nursery. To find these saplings are coming from which batch.
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    /**
     * Relation Name : ManyToOne - batchs
     * It Connects the Batch Table to Nursery Table
     */
    @ManyToOne
    @JsonIgnoreProperties("batchs")
    private Nursery nursery;

    /**
     * Relation Name : ManyToOne - varietys
     * It Connects the Batch Table to PickListValue - pickListVariety Table
     */
    @ManyToOne
    @JsonIgnoreProperties("varietys")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - categorys
     * It Connects the Batch Table to PickListValue - pickListCategory Table
     */
    @ManyToOne
    @JsonIgnoreProperties("categorys")
    private PickListValue pickListCategory;

    @ManyToOne
    @JsonIgnoreProperties("batchQuantityTypes")
    private PickListValue quantityType;

    @ManyToOne
    @JsonIgnoreProperties("batchMotherBeds")
    private MotherBed motherBed;

    @ManyToOne
    @JsonIgnoreProperties("batches")
    private FinancialYearSettings financialYearBatch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * Function Name : GetId()
     * To Get the Id of the batch table
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Function Name : setId()
     * To Set the Id of the batch table
     *
     * @return id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Function Name : getBatchNo()
     * To Get the batch no from the batch table
     *
     * @return batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * Function Name : batchNo
     * To set the batch no from the batch table
     * @param batchNo : String
     * @return batchNo
     */
    public Batch batchNo(String batchNo) {
        this.batchNo = batchNo;
        return this;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchName() {
        return batchName;
    }

    public Batch batchName(String batchName) {
        this.batchName = batchName;
        return this;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Batch quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getShowingType() {
        return showingType;
    }

    public Batch showingType(Integer showingType) {
        this.showingType = showingType;
        return this;
    }

    public void setShowingType(Integer showingType) {
        this.showingType = showingType;
    }

    public LocalDate getSowingDate() {
        return sowingDate;
    }

    public Batch sowingDate(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
        return this;
    }

    public void setSowingDate(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public Batch closedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
        return this;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public Integer getRound() {
        return round;
    }

    public Batch round(Integer round) {
        this.round = round;
        return this;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getRemarks() {
        return remarks;
    }

    public Batch remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public Batch status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Damage> getDamages() {
        return damages;
    }

    public Batch damages(Set<Damage> damages) {
        this.damages = damages;
        return this;
    }

    public Batch addDamages(Damage damage) {
        this.damages.add(damage);
        damage.setBatch(this);
        return this;
    }

    public Batch removeDamages(Damage damage) {
        this.damages.remove(damage);
        damage.setBatch(null);
        return this;
    }

    public void setDamages(Set<Damage> damages) {
        this.damages = damages;
    }

    public Set<ShadeArea> getShadeAreas() {
        return shadeAreas;
    }

    public Batch shadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
        return this;
    }

    public Batch addShadeAreas(ShadeArea shadeArea) {
        this.shadeAreas.add(shadeArea);
        shadeArea.setBatch(this);
        return this;
    }

    public Batch removeShadeAreas(ShadeArea shadeArea) {
        this.shadeAreas.remove(shadeArea);
        shadeArea.setBatch(null);
        return this;
    }

    public void setShadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
    }

    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    public Batch nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    public Batch addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setBatch(this);
        return this;
    }

    public Batch removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setBatch(null);
        return this;
    }

    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    public Nursery getNursery() {
        return nursery;
    }

    public Batch nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    public Batch pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    public Batch pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    public PickListValue getQuantityType() {
        return quantityType;
    }

    public Batch quantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
        return this;
    }

    public void setQuantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
    }

    public MotherBed getMotherBed() {
        return motherBed;
    }

    public Batch motherBed(MotherBed motherBed) {
        this.motherBed = motherBed;
        return this;
    }

    public void setMotherBed(MotherBed motherBed) {
        this.motherBed = motherBed;
    }

    public FinancialYearSettings getFinancialYearBatch() {
        return financialYearBatch;
    }

    public Batch financialYearBatch(FinancialYearSettings financialYearSettings) {
        this.financialYearBatch = financialYearSettings;
        return this;
    }

    public void setFinancialYearBatch(FinancialYearSettings financialYearSettings) {
        this.financialYearBatch = financialYearSettings;
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
        Batch batch = (Batch) o;
        if (batch.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batch.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Batch{" +
            "id=" + getId() +
            ", batchNo='" + getBatchNo() + "'" +
            ", batchName='" + getBatchName() + "'" +
            ", quantity=" + getQuantity() +
            ", showingType=" + getShowingType() +
            ", sowingDate='" + getSowingDate() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            ", round=" + getRound() +
            ", remarks='" + getRemarks() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
