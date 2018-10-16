/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/31/08
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOh  Generation 
 *                        and declared the table fields, data types for 
 *                        MapZonalWithOh table.
 *  Relation for MapZonalWithOh : ManyToOne Relation, OneToMany Relation
 *  ManyToOne Relation : nursery
 *  OneToMany Relation : Batch table
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
 * MotherBed Domain Class
 * 
 * MotherBed class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "mother_bed")
public class MotherBed extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_value", nullable = false)
    private String value;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - motherBeds
     * Table Name    : Connects the MotherBed Table to Nursery Table.
     * Used to point out the MotherBed values in the Nursery table.
     */
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("motherBeds")
    private Nursery nursery;

    /**
     * Relation Name : OneToMany - batchMotherBeds
     * Table Name    : Connects the MotherBed Table to Batch Table
     * Used to point out the MotherBed values in the Batch table.
     */
    @OneToMany(mappedBy = "motherBed", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batchMotherBeds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from MotherBed table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values in the MotherBed table.
     * 
     * @param id id of the MotherBed
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the value from MotherBed table
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * To set the value values in the MotherBed table.
     * 
     * @param value value of the MotherBed
     * @return this
     */
    public MotherBed value(String value) {
        this.value = value;
        return this;
    }

     /**
     * To set the value in the MotherBed table.
     * 
     * @param value value of the MotherBed
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * To Get the status from MotherBed table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the MotherBed table.
     * 
     * @param status status of the MotherBed
     * @return this
     */
    public MotherBed status(Integer status) {
        this.status = status;
        return this;
    }

     /**
     * To set the status values in the MotherBed table.
     * 
     * @param status status of the MotherBed
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the nursery from MotherBed table
     * 
     * @return nursery
     */
    public Nursery getNursery() {
        return nursery;
    }

    /**
     * To set the nursery values in the MotherBed table.
     * 
     * @param nursery nursery of the MotherBed
     * @return this
     */
    public MotherBed nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

     /**
     * To set the nursery values in the MotherBed table.
     * 
     * @param nursery nursery of the MotherBed
     */
    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    /**
     * To Get the batchMotherBeds from MotherBed table
     * 
     * @return batchMotherBeds
     */
    public Set<Batch> getBatchMotherBeds() {
        return batchMotherBeds;
    }

    /**
     * To set the batches values in the MotherBed table.
     * 
     * @param batches batches of the MotherBed
     * @return this
     */
    public MotherBed batchMotherBeds(Set<Batch> batches) {
        this.batchMotherBeds = batches;
        return this;
    }

    /**
     * To set the add batch values in the MotherBed table.
     * 
     * @param batch batch of the MotherBed
     * @return this
     */
    public MotherBed addBatchMotherBed(Batch batch) {
        this.batchMotherBeds.add(batch);
        batch.setMotherBed(this);
        return this;
    }

    /**
     * To set the remove batch values in the MotherBed table.
     * 
     * @param batch batch of the MotherBed
     * @return this
     */
    public MotherBed removeBatchMotherBed(Batch batch) {
        this.batchMotherBeds.remove(batch);
        batch.setMotherBed(null);
        return this;
    }

    /**
     * To set the batches values in the MotherBed table.
     * 
     * @param batches batches of the MotherBed
     */
    public void setBatchMotherBeds(Set<Batch> batches) {
        this.batchMotherBeds = batches;
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
        MotherBed motherBed = (MotherBed) o;
        if (motherBed.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), motherBed.getId());
    }

    /**
     * Hash code for the id
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
        return "MotherBed{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
