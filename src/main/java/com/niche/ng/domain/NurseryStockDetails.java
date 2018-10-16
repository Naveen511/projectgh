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
 *  ManyToOne Relation  : Batch, NurseryStock, Nursery, FinancialYearSettings, 
 *                        PickListValue
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
 * NurseryStockDetails Domain Class
 * 
 * NurseryStockDetails class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    @Column(name = "it_status")
    private Integer itStatus;

    /**
     * Relation Name : ManyToOne - batch
     * Table Name    : Connects the NurseryStockDetails Table to Batch Table
     * To get the batch.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private Batch batch;

    /**
     * Relation Name : ManyToOne - nurseryStock
     * Table Name    : Connects the NurseryStockDetails Table to NurseryStock Table
     * To get the nurseryStock.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private NurseryStock nurseryStock;

    /**
     * Relation Name : ManyToOne - itNursery
     * Table Name    : Connects the NurseryStockDetails Table to Nursery Table
     * To get the itNursery.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private Nursery itNursery;

    /**
     * Relation Name : ManyToOne - saplingDamageArea
     * Table Name    : Connects the NurseryStockDetails Table to PickListValue Table
     * To get the saplingDamageArea.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDamageAreas")
    private PickListValue saplingDamageArea;

    /**
     * Relation Name : ManyToOne - financialYearStockDetails
     * Table Name    : Connects the NurseryStockDetails Table to 
     *                 FinancialYearSettings Table
     * To get the financialYearStockDetails.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockDetails")
    private FinancialYearSettings financialYearStockDetails;

    /**
     * Relation Name : ManyToOne - fromNurseryStockDetails
     * Table Name    : Connects the NurseryStockDetails Table to Nursery Table
     * To get the from nursery Id.
     */
    @ManyToOne
    @JsonIgnoreProperties("fromNurseryStockDetails")
    private Nursery fromNurseryStockDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from nurseryStockDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for nurseryStockDetails table
     *
     * @param id the id in nurseryStockDetails table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the date from nurseryStockDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for nurseryStockDetails table
     *
     * @param date the date in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for nurseryStockDetails table
     *
     * @param date the date in nurseryStockDetails table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the quantity from nurseryStockDetails table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for nurseryStockDetails table
     *
     * @param quantity the quantity in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for nurseryStockDetails table
     *
     * @param quantity the quantity in nurseryStockDetails table
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the description from nurseryStockDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for nurseryStockDetails table
     *
     * @param description the description in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for nurseryStockDetails table
     *
     * @param description the description in nurseryStockDetails table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from nurseryStockDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for nurseryStockDetails table
     *
     * @param status the status in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for nurseryStockDetails table
     *
     * @param status the status in nurseryStockDetails table
     */
    public void setStatus(Integer status) {
        this.status = status;

    }

    /**
     * To Get the itStatus from nurseryStockDetails table
     * 
     * @return itStatus
     */
    public Integer getItStatus() {
        return itStatus;
    }

    /**
     * To Set the itStatus for nurseryStockDetails table
     *
     * @param itStatus the itStatus in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails itStatus(Integer itStatus) {
        this.itStatus = itStatus;
        return this;
    }

    /**
     * To Set the itStatus for nurseryStockDetails table
     *
     * @param itStatus the itStatus in nurseryStockDetails table
     */
    public void setItStatus(Integer itStatus) {
        this.itStatus = itStatus;
    }


    /**
     * To Get the batch from nurseryStockDetails table
     * 
     * @return batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * To Set the batch for nurseryStockDetails table
     *
     * @param batch the batch in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    /**
     * To Set the batch for nurseryStockDetails table
     *
     * @param batch the batch in nurseryStockDetails table
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * To Get the nurseryStock from nurseryStockDetails table
     * 
     * @return nurseryStock
     */
    public NurseryStock getNurseryStock() {
        return nurseryStock;
    }

    /**
     * To Set the nurseryStock for nurseryStockDetails table
     *
     * @param nurseryStock the nurseryStock in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails nurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
        return this;
    }

    /**
     * To Set the nurseryStock for nurseryStockDetails table
     *
     * @param nurseryStock the nurseryStock in nurseryStockDetails table
     */
    public void setNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
    }

    /**
     * To Get the itNursery from nurseryStockDetails table
     * 
     * @return itNursery
     */
    public Nursery getItNursery() {
        return itNursery;
    }

    /**
     * To Set the itNursery for nurseryStockDetails table
     *
     * @param nursery the nursery in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails itNursery(Nursery nursery) {
        this.itNursery = nursery;
        return this;
    }

    /**
     * To Set the itNursery for nurseryStockDetails table
     *
     * @param nursery the nursery in nurseryStockDetails table
     */
    public void setItNursery(Nursery nursery) {
        this.itNursery = nursery;
    }

    /**
     * To Get the saplingDamageArea from nurseryStockDetails table
     * 
     * @return saplingDamageArea
     */
    public PickListValue getSaplingDamageArea() {
        return saplingDamageArea;
    }

    /**
     * To Set the saplingDamageArea for nurseryStockDetails table
     *
     * @param pickListValue the pickListValue in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails saplingDamageArea(PickListValue pickListValue) {
        this.saplingDamageArea = pickListValue;
        return this;
    }

    /**
     * To Set the saplingDamageArea for nurseryStockDetails table
     *
     * @param pickListValue the pickListValue in nurseryStockDetails table
     */
    public void setSaplingDamageArea(PickListValue pickListValue) {
        this.saplingDamageArea = pickListValue;
    }

    /**
     * To Get the financialYearStockDetails from nurseryStockDetails table
     * 
     * @return financialYearStockDetails
     */
    public FinancialYearSettings getFinancialYearStockDetails() {
        return financialYearStockDetails;
    }

    /**
     * To Set the financialYearStockDetails for nurseryStockDetails table
     *
     * @param financialYearSettings the financialYearSettings in nurseryStockDetails table
     * @return this
     */
    public NurseryStockDetails financialYearStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearStockDetails = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearStockDetails for nurseryStockDetails table
     *
     * @param financialYearSettings the financialYearSettings in nurseryStockDetails table
     */
    public void setFinancialYearStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearStockDetails = financialYearSettings;
    }
    
    /**
     * To Get the fromNurseryStockDetails from nurseryStockDetails table
     * 
     * @return fromNurseryStockDetails
     */
    public Nursery getFromNurseryStockDetails() {
        return fromNurseryStockDetails;
    }

    /**
     * To Get the fromNurseryStockDetails from nurseryStockDetails table
     * 
     * @return fromNurseryStockDetails
     */
    public NurseryStockDetails fromNurseryStockDetails(Nursery nursery) {
        this.fromNurseryStockDetails = nursery;
        return this;
    }

    /**
     * To Set the fromNurseryStockDetails for nurseryStockDetails table
     *
     * @param fromNurseryStockDetails the fromNurseryStockDetails in nurseryStockDetails table
     */
    public void setFromNurseryStockDetails(Nursery nursery) {
        this.fromNurseryStockDetails = nursery;
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
        NurseryStockDetails nurseryStockDetails = (NurseryStockDetails) o;
        if (nurseryStockDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStockDetails.getId());
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
        return "NurseryStockDetails{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", itStatus=" + getItStatus() +
            "}";
    }
}
