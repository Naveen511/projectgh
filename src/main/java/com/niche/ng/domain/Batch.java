/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Batch Generation and declared the table 
 *                        fields, data types for batch table. 
 *  Relation for Batch : OneToMany Relation and ManyToOne Relation
 *  OneToMany Relation : Damage Table, Shade Area, Stock Details
 *  ManyToOne Relation : Nursery, PickList Variety, and Picklist Category,
 *                       MotherBed, FinancialYearSettings
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
 * Batch Domain Class
 * 
 * Batch class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    @Column(name = "no_of_kg")
    private String noOfKg;

    /**
     * Relation Name : OneToMany - damages
     * Table Name    : Connects the Batch Table to Damage Table
     * After Creation of the Batch, If any seeds/seedlings/saplings is damaged 
     * then it is move to Damage Table
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> damages = new HashSet<>();

    /**
     * Relation Name : OneToMany - shadeAreas
     * Table Name    : Connects the Batch Table to ShadeArea Table
     * Used to find out the seedlings are belongs to which batch, after moving the 
     * seedlings to shade area.
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<ShadeArea> shadeAreas = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockDetails
     * Table Name    : Connects the Batch Table to NurseryStockDetails Table
     * Used to find out the saplings are belongs to which batch, after the saplings 
     * distributed to the nursery.
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    /**
     * Relation Name : ManyToOne - batchs
     * Table Name    : Connects the Batch Table to Nursery Table
     * To get the batch details belongs to which nursery.
     */
    @ManyToOne
    @JsonIgnoreProperties("batchs")
    private Nursery nursery;

    /**
     * Relation Name : ManyToOne - varietys
     * Table Name    : Connects the Batch Table to PickListValue Table
     * To get the list of Varieties form the picklist table.
     */
    @ManyToOne
    @JsonIgnoreProperties("varietys")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - categorys
     * Table Name    : Connects the Batch Table to PickListValue Table
     * To get the category for the particular variety from the picklistvalue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("categorys")
    private PickListValue pickListCategory;

    /**
     * Relation Name : ManyToOne - batchQuantityTypes
     * Table Name    : Connects the Batch Table to PickListValue Table
     * To get the quantity from the picklist table.
     */
    @ManyToOne
    @JsonIgnoreProperties("batchQuantityTypes")
    private PickListValue quantityType;

    /**
     * Relation Name : ManyToOne - batchMotherBeds
     * Table Name    : Connects the Batch Table to motherBed Table
     * To get the motherbed value from the motherbed table.
     */
    @ManyToOne
    @JsonIgnoreProperties("batchMotherBeds")
    private MotherBed motherBed;

    /**
     * Relation Name : ManyToOne - batches
     * Table Name    : Connects the Batch Table to financialyear Table
     * To get the financial year value from the financialyear table.
     */
    @ManyToOne
    @JsonIgnoreProperties("batches")
    private FinancialYearSettings financialYearBatch;

    /**
     * Relation Name : ManyToOne - batchQuantities
     * Table Name    : Connects the Batch Table to BatchQuantity Table
     * Quantity of the batch in BatchQuantity table.
     */
    @OneToMany(mappedBy = "batch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<BatchQuantity> batchQuantities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id of the batch table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id of the batch table
     *
     * @param id id for the batch table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the batch no from the batch table
     *
     * @return batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * To set the batch no from the batch table
     * 
     * @param batchNo : String
     * @return batchNo
     */
    public Batch batchNo(String batchNo) {
        this.batchNo = batchNo;
        return this;
    }

    /**
     * To set the batch no from the batch table
     * 
     * @param batchNo batch number of a particular batch
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * To Get the batch name from the batch table
     * 
     * @return batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * To set the batch name from the batch table
     * 
     * @param batchName batch name of a particular batch
     * @return this
     */
    public Batch batchName(String batchName) {
        this.batchName = batchName;
        return this;
    }

    /**
     * To set the batch name from the batch table
     * 
     * @param batchName
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * To get the quantity from the batch table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the batch quantity from the batch table
     */
    public Batch quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To set the batch quantity from the batch table
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To get the showingType from the batch table
     * 
     * @return showingType type of sown
     */
    public Integer getShowingType() {
        return showingType;
    }

    /**
     * To set the sowing type  from the batch table
     * 
     * @param showingType type of sown
     * @return this
     */
    public Batch showingType(Integer showingType) {
        this.showingType = showingType;
        return this;
    }

    /**
     * To set the sowing type from the batch table.
     */
    public void setShowingType(Integer showingType) {
        this.showingType = showingType;
    }

    /**
     * To get the sowingDate from the batch table
     * 
     * @return sowingDate
     */
    public LocalDate getSowingDate() {
        return sowingDate;
    }

    /**
     * To set the sowing date from the batch table.
     * 
     * @param sowingDate date sown
     * @return this
     */
    public Batch sowingDate(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
        return this;
    }

    /**
     * To set the sowing date from the batch table.
     * 
     * @param sowingDate date sown 
     */
    public void setSowingDate(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    /**
     * To get the closedDate from the batch table
     * 
     * @return closedDate
     */
    public LocalDate getClosedDate() {
        return closedDate;
    }

    /**
     * To set the closed date from the batch table.
     * 
     * @param closedDate date of closing the batch
     * @return this
     */
    public Batch closedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
        return this;
    }

    /**
     * To set the closed date from the batch table.
     * 
     * @param closedDate date of closing the batch
     */
    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    /**
     * To get the closedDate from the batch table
     * 
     * @return round
     */
    public Integer getRound() {
        return round;
    }

    /**
     * To set the round for the batch table.
     * 
     * @param round round in batch
     * @return this
     */
    public Batch round(Integer round) {
        this.round = round;
        return this;
    }

    /**
     * To set the round for the batch table.
     * 
     * @param round
     */
    public void setRound(Integer round) {
        this.round = round;
    }

    /**
     * To get the remarks from the batch table
     * 
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * To set the remarks for the batch table.
     * 
     * @param remarks remarks in batch
     * @return this
     */
    public Batch remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    /**
     * To set the remarks for the batch table.
     * 
     * @param remarks remarks in batch
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * To get the status from the batch table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status for the batch table.
     * 
     * @param status status in batch
     * @return this
     */
    public Batch status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status for the batch table.
     * 
     * @param status status in batch
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To get the noOfKg from the batch table
     * 
     * @return noOfKg
     */
    public String getNoOfKg() {
        return noOfKg;
    }

    /**
     * To set the noOfKg for the batch table.
     * 
     * @param noOfKg noOfKg in batch
     * @return noOfKg
     */
    public Batch noOfKg(String noOfKg) {
        this.noOfKg = noOfKg;
        return this;
    }

    /**
     * To set the noOfKg for the batch table.
     * 
     * @param noOfKg noOfKg in batch
     */
    public void setNoOfKg(String noOfKg) {
        this.noOfKg = noOfKg;
    }

    /**
     * To get the damages from the batch table
     * 
     * @return damages
     */
    public Set<Damage> getDamages() {
        return damages;
    }

    /**
     * To set the damages for the batch table.
     * 
     * @return this
     */
    public Batch damages(Set<Damage> damages) {
        this.damages = damages;
        return this;
    }

    /**
     * To set the add damages for the batch table.
     * 
     * @param damage damage in batch
     * @return this
     */
    public Batch addDamages(Damage damage) {
        this.damages.add(damage);
        damage.setBatch(this);
        return this;
    }

    /**
     * To set the remove damages for the batch table.
     * 
     * @param damage damage in batch
     * @return this
     */
    public Batch removeDamages(Damage damage) {
        this.damages.remove(damage);
        damage.setBatch(null);
        return this;
    }

    /**
     * To set the damages for the batch table.
     * 
     * @param damages damage in batch
     */
    public void setDamages(Set<Damage> damages) {
        this.damages = damages;
    }

    /**
     * To get the shadeAreas from the batch table
     * 
     * @return shadeAreas
     */
    public Set<ShadeArea> getShadeAreas() {
        return shadeAreas;
    }

    /**
     * To set the shadeAreas for the batch table.
     * 
     * @param shadeAreas shade area in batch
     * @return this
     */
    public Batch shadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
        return this;
    }

    /**
     * To set the addShadeAreas for the batch table.
     * 
     * @param shadeArea shade area in batch
     * @return this
     */
    public Batch addShadeAreas(ShadeArea shadeArea) {
        this.shadeAreas.add(shadeArea);
        shadeArea.setBatch(this);
        return this;
    }

    /**
     * To set the removeShadeAreas for the batch table.
     * 
     * @param shadeArea remove shade area
     * @return this
     */
    public Batch removeShadeAreas(ShadeArea shadeArea) {
        this.shadeAreas.remove(shadeArea);
        shadeArea.setBatch(null);
        return this;
    }

    /**
     * To set the ShadeAreas for the batch table.
     * 
     * @param shadeAreas shade area in batch
     */
    public void setShadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
    }

    /**
     * To get the nurseryStockDetails from the batch table
     * 
     * @return nurseryStockDetails
     */
    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    /**
     * To set the nurseryStockDetails for the batch table.
     * 
     * @return this
     */
    public Batch nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    /**
     * To set the nurseryStockDetails for the batch table.
     * 
     * @param nurseryStockDetails add stock details in nursery
     * @return this
     */
    public Batch addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setBatch(this);
        return this;
    }

    /**
     * To set the removeNurseryStockDetails for the batch table.
     * 
     * @param nurseryStockDetails remove stock details in nursery
     * @return this
     */
    public Batch removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setBatch(null);
        return this;
    }

    /**
     * To set the NurseryStockDetails for the batch table.
     * 
     * @param nurseryStockDetails stock details in nursery
     */
    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    /**
     * To get the nursery from the batch table
     * 
     * @return nursery
     */
    public Nursery getNursery() {
        return nursery;
    }

    /**
     * To set the nursery for the batch table.
     * 
     * @param nursery nursery
     * @return this
     */
    public Batch nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    /**
     * To set the nursery for the batch table.
     * 
     * @param nursery nursery
     */
    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    /**
     * To set the pickListVariety for the batch table.
     * 
     * @return pickListVariety variety to choose
     */
    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    /**
     * To set the pickListVariety for the batch table.
     * 
     * @param pickListValue pickListValue in variety
     * @return this
     */
    public Batch pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    /**
     * To set the pickListVariety for the batch table.
     * 
     * @param pickListValue pickListValue in variety
     */
    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    /**
     * To get the pickListCategory from the batch table
     * 
     * @return pickListCategory
     */
    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    /**
     * To set the pickListCategory for the batch table.
     * 
     * @param pickListValue pickListValue for category
     * @return this
     */
    public Batch pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    /**
     * To set the pickListCategory for the batch table.
     * 
     * @param pickListValue pickListValue for category
     */
    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    /**
     * To get the quantityType from the batch table
     * 
     * @return quantityType
     */
    public PickListValue getQuantityType() {
        return quantityType;
    }

    /**
     * To set the quantityType for the batch table.
     * 
     * @param pickListValue quantity type
     * @return this
     */
    public Batch quantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
        return this;
    }

    /**
     * To set the quantityType for the batch table.
     * 
     * @param pickListValue quantity type
     */
    public void setQuantityType(PickListValue pickListValue) {
        this.quantityType = pickListValue;
    }

    /**
     * To get the motherBed for the batch table.
     * 
     * @return motherBed
     */
    public MotherBed getMotherBed() {
        return motherBed;
    }

    /**
     * To set the motherBed for the batch table.
     * 
     * @param motherBed motherbed for batch
     * @return this
     */
    public Batch motherBed(MotherBed motherBed) {
        this.motherBed = motherBed;
        return this;
    }

    /**
     * To set the motherBed for the batch table.
     * 
     * @param motherBed
     */
    public void setMotherBed(MotherBed motherBed) {
        this.motherBed = motherBed;
    }

    /**
     * To get the financialYearBatch from the batch table
     * 
     * @return financialYearBatch
     */
    public FinancialYearSettings getFinancialYearBatch() {
        return financialYearBatch;
    }

    /**
     * To set the financialYearBatch for the batch table.
     * 
     * @param financialYearSettings financial year of creation
     * @return this
     */
    public Batch financialYearBatch(FinancialYearSettings financialYearSettings) {
        this.financialYearBatch = financialYearSettings;
        return this;
    }

    /**
     * To set the financialYearBatch for the batch table.
     * 
     * @param financialYearSettings financial year of creation
     */
    public void setFinancialYearBatch(FinancialYearSettings financialYearSettings) {
        this.financialYearBatch = financialYearSettings;
    }

    /**
     * To get the batchQuantities from the batch table.
     * 
     * @return batchQuantities
     */
    public Set<BatchQuantity> getBatchQuantities() {
        return batchQuantities;
    }

    /**
     * To set the batchQuantities for the batch table.
     * 
     * @param batchQuantities
     * @return this
     */
    public Batch batchQuantities(Set<BatchQuantity> batchQuantities) {
        this.batchQuantities = batchQuantities;
        return this;
    }

    /**
     * To set the add batchQuantities for the batch table.
     * @param batchQuantity
     * @return this
     */
    public Batch addBatchQuantity(BatchQuantity batchQuantity) {
        this.batchQuantities.add(batchQuantity);
        batchQuantity.setBatch(this);
        return this;
    }

    /**
     * To set the remove batchQuantities for the batch table.
     * 
     * @return this
     */
    public Batch removeBatchQuantity(BatchQuantity batchQuantity) {
        this.batchQuantities.remove(batchQuantity);
        batchQuantity.setBatch(null);
        return this;
    }

    /**
     * To set the batchQuantities for the batch table.
     * @param batchQuantities
     */
    public void setBatchQuantities(Set<BatchQuantity> batchQuantities) {
        this.batchQuantities = batchQuantities;
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
        Batch batch = (Batch) o;
        if (batch.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batch.getId());
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
            ", noOfKg='" + getNoOfKg() + "'" +
            "}";
    }
}
