/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/31/08
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MotherBed
 *  Declared the table fields and data types for the damage table.
 *  Defined the following Relation for the damage table :
 *  ManyToOne Relation to nursery
 *  OneToMany Relation to Batch table
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
 * A MotherBed.
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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("motherBeds")
    private Nursery nursery;

    @OneToMany(mappedBy = "motherBed", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batchMotherBeds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public MotherBed value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public MotherBed status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Nursery getNursery() {
        return nursery;
    }

    public MotherBed nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    public Set<Batch> getBatchMotherBeds() {
        return batchMotherBeds;
    }

    public MotherBed batchMotherBeds(Set<Batch> batches) {
        this.batchMotherBeds = batches;
        return this;
    }

    public MotherBed addBatchMotherBed(Batch batch) {
        this.batchMotherBeds.add(batch);
        batch.setMotherBed(this);
        return this;
    }

    public MotherBed removeBatchMotherBed(Batch batch) {
        this.batchMotherBeds.remove(batch);
        batch.setMotherBed(null);
        return this;
    }

    public void setBatchMotherBeds(Set<Batch> batches) {
        this.batchMotherBeds = batches;
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
        MotherBed motherBed = (MotherBed) o;
        if (motherBed.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), motherBed.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MotherBed{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", status=" + getStatus() +
            "}";
    }
}
