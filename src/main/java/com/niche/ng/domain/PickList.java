/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickList Generation.
 *  Declared the table fields and data types for the PickList table.
 *  Defined the following Relation for the PickList Table :
 *  OneToMany Relation to PickListValue Table
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PickList.
 */
@Entity
@Table(name = "pick_list")
public class PickList extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pick_list_name", nullable = false)
    private String pickListName;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "pickList", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PickListValue> pickListValues = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickListName() {
        return pickListName;
    }

    public PickList pickListName(String pickListName) {
        this.pickListName = pickListName;
        return this;
    }

    public void setPickListName(String pickListName) {
        this.pickListName = pickListName;
    }

    public Integer getStatus() {
        return status;
    }

    public PickList status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<PickListValue> getPickListValues() {
        return pickListValues;
    }

    public PickList pickListValues(Set<PickListValue> pickListValues) {
        this.pickListValues = pickListValues;
        return this;
    }

    public PickList addPickListValues(PickListValue pickListValue) {
        this.pickListValues.add(pickListValue);
        pickListValue.setPickList(this);
        return this;
    }

    public PickList removePickListValues(PickListValue pickListValue) {
        this.pickListValues.remove(pickListValue);
        pickListValue.setPickList(null);
        return this;
    }

    public void setPickListValues(Set<PickListValue> pickListValues) {
        this.pickListValues = pickListValues;
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
        PickList pickList = (PickList) o;
        if (pickList.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickList.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PickList{" +
            "id=" + getId() +
            ", pickListName='" + getPickListName() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
