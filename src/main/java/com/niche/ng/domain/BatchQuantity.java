/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Batch quantity Generation and declared 
 *                        the table fields, data types for Batch quantity table. 
 *  Relation for Batch quantity : ManyToOne Relation
 *  ManyToOne Relation : Batch
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
 * BatchQuantity Domain Class
 * 
 * BatchQuantity class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "batch_quantity")
public class BatchQuantity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "status")
    private Integer status;

     /**
     * Relation Name : ManyToOne - batch
     * Table Name    : Connect the BatchQuantity Table to Batch Table
     * To get the batch quantity.
     */
    @ManyToOne
    @JsonIgnoreProperties("batch")
    private Batch batch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from batch quantity table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for batch quantity table
     *
     * @param id id for the batchquantity table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from batch quantity table
     * 
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for batch quantity table
     *
     * @param quantity quantity in batch quantity table
     * @return this
     */
    public BatchQuantity quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for batch quantity table
     *
     * @param quantity quantity in batch quantity table
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from batch quantity table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for batch quantity table
     *
     * @param date date in batch quantity table
     * @return this
     */
    public BatchQuantity date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for batch quantity table
     *
     * @param date date in batch quantity table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the remarks from batch quantity table
     * 
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * To Set the remarks for batch quantity table
     *
     * @param remarks remarks in batch quantity table
     */
    public BatchQuantity remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    /**
     * To Set the remarks for batch quantity table
     *
     * @param remarks remarks in batch quantity table
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * To Get the status from batch quantity table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for batch quantity table
     * 
     * @param status status in batch quantity table
     * @return this
     */
    public BatchQuantity status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for batch quantity table
     * 
     * @param status status in batch quantity table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the batch from batch quantity table
     * 
     * @return batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * To Set the batch for batch quantity table
     * 
     * @param batch batch in batch quantity table
     */
    public BatchQuantity batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    /**
     * To Set the batch for batch quantity table
     * 
     * @param batch batch in batch quantity table
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
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
        BatchQuantity batchQuantity = (BatchQuantity) o;
        if (batchQuantity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batchQuantity.getId());
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
        return "BatchQuantity{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
