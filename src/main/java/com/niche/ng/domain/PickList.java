/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/31/08
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description     : This file performs PickList Generation
                                    and declared the table fields, data types for PickList table.
 *  Relation for PickList: OneToMany Relation and ManyToOne Relation
 *  OneToMany Relation   : PickList Value
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
 * A PickList Domain Class
 * 
 * PickList class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    @Column(name = "display_label_name")
    private String displayLabelName;

    /**
     * Relation Name : OneToMany - pickListValues
     * Table Name    : Connects the PickList Table to PickListValue Table
     * Pick list is mapped to the PickListValue table
     */
    @OneToMany(mappedBy = "pickList", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PickListValue> pickListValues = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from PickList table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for PickList table
     *
     * @param id id of the PickList Table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the pickListName from PickList table
     * 
     * @return pickListName
     */
    public String getPickListName() {
        return pickListName;
    }

    /**
     * To set the pickListName for PickList table
     * 
     * @param pickListName pickListName of the PickList
     * @return this
     */
    public PickList pickListName(String pickListName) {
        this.pickListName = pickListName;
        return this;
    }

    /**
     * To Set the pickListName for PickList table
     *
     * @param pickListName pickListName of the PickList Table
     */
    public void setPickListName(String pickListName) {
        this.pickListName = pickListName;
    }

    /**
     * To Get the status from PickList table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status for PickList table
     * 
     * @param status status of the PickList
     * @return this
     */
    public PickList status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for PickList table
     *
     * @param status status of the PickList Table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the displayLabelName from PickList table
     * 
     * @return displayLabelName
     */
    public String getDisplayLabelName() {
        return displayLabelName;
    }

    /**
     * To set the displayLabelName for PickList table
     * 
     * @param displayLabelName displayLabelName of the PickList
     * @return this
     */
    public PickList displayLabelName(String displayLabelName) {
        this.displayLabelName = displayLabelName;
        return this;
    }

    /**
     * To Set the displayLabelName for PickList table
     *
     * @param displayLabelName displayLabelName of the PickList Table
     */
    public void setDisplayLabelName(String displayLabelName) {
        this.displayLabelName = displayLabelName;
    }

    /**
     * To Get the pickListValues from PickList table
     * 
     * @return pickListValues
     */
    public Set<PickListValue> getPickListValues() {
        return pickListValues;
    }

    /**
     * To set the pickListValues for PickList table
     * 
     * @param pickListValues pickListValues of the PickList
     * @return this
     */
    public PickList pickListValues(Set<PickListValue> pickListValues) {
        this.pickListValues = pickListValues;
        return this;
    }

    /**
     * To set the pickListValues for PickList table
     * 
     * @param pickListValues pickListValues of the PickList
     * @return this
     */
    public PickList addPickListValues(PickListValue pickListValue) {
        this.pickListValues.add(pickListValue);
        pickListValue.setPickList(this);
        return this;
    }

    /**
     * To set the pickListValues for PickList table
     * 
     * @param pickListValues pickListValues of the PickList
     * @return this
     */
    public PickList removePickListValues(PickListValue pickListValue) {
        this.pickListValues.remove(pickListValue);
        pickListValue.setPickList(null);
        return this;
    }

    /**
     * To Set the pickListValues for PickList table
     *
     * @param pickListValues pickListValues of the PickList Table
     */
    public void setPickListValues(Set<PickListValue> pickListValues) {
        this.pickListValues = pickListValues;
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
        PickList pickList = (PickList) o;
        if (pickList.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickList.getId());
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
        return "PickList{" +
            "id=" + getId() +
            ", pickListName='" + getPickListName() + "'" +
            ", status=" + getStatus() +
            ", displayLabelName='" + getDisplayLabelName() + "'" +
            "}";
    }
}
