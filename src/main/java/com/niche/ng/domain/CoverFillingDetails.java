/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs CoverFillingDetails  Generation and declared 
 *                        the table fields, data types for CoverFillingDetails table. 
 *  Relation for CoverFillingDetails : ManyToOne Relation
 *  ManyToOne Relation : CoverFilling, PickListValue
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
 * CoverFillingDetails Domain Class
 * 
 * CoverFillingDetails class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "cover_filling_details")
public class CoverFillingDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Column(name = "status")
    private Integer status;

    @Column(name = "description")
    private String description;

    /**
     * Relation Name : ManyToOne - coverFillingDetails
     * Table Name    : Connect the coverFillingDetails Table to CoverFilling Table
     * To get the cover details belongs to CoverFilling Table.
     */
    @ManyToOne
    @JsonIgnoreProperties("coverFillingDetails")
    private CoverFilling coverFilling;

    /**
     * Relation Name : ManyToOne - coverFillingDetails
     * Table Name    : Connect the coverFillingDetails Table to PickListValue Table
     * To get the damageType in the coverFillingDetails.
     */
    @ManyToOne
    @JsonIgnoreProperties("coverFillingDetails")
    private PickListValue damageType;

    /**
     * Relation Name : ManyToOne
     * Table Name    : Connect the coverFillingDetails Table to PickListValue Table
     * To get the cover details.
     */
    @ManyToOne
    @JsonIgnoreProperties("")
    private PickListValue coverFillingDamageDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from coverFillingDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for coverFillingDetails table
     * 
     * @param id id for the coverFillingDetails table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from coverFillingDetails table
     * 
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for coverFillingDetails table
     * 
     * @return this
     */
    public CoverFillingDetails quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for coverFillingDetails table
     * 
     * @param quantity quantity in table
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from coverFillingDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for coverFillingDetails table
     * 
     * @param date date in table
     * @return this
     */
    public CoverFillingDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for coverFillingDetails table
     * 
     * @param date date in table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from coverFillingDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for coverFillingDetails table
     * 
     * @param status status in table
     * @return this
     */
    public CoverFillingDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for coverFillingDetails table
     * 
     * @param status status in table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the description from coverFillingDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description from coverFillingDetails table
     * 
     * @param description description in table
     * @return this
     */
    public CoverFillingDetails description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To set the description from coverFillingDetails table
     * 
     * @param description description in table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To get the description from coverFillingDetails table
     * 
     * @return coverFilling 
     */
    public CoverFilling getCoverFilling() {
        return coverFilling;
    }

    /**
     * To set the coverFilling from coverFillingDetails table
     * 
     * @param coverFilling filling the cover
     * @return this
     */
    public CoverFillingDetails coverFilling(CoverFilling coverFilling) {
        this.coverFilling = coverFilling;
        return this;
    }

    /**
     * To set the coverFilling from coverFillingDetails table
     * 
     * @param coverFilling filling the cover
     */
    public void setCoverFilling(CoverFilling coverFilling) {
        this.coverFilling = coverFilling;
    }

    /**
     * To get the damageType from coverFillingDetails table
     * 
     * @return damageType
     */
    public PickListValue getDamageType() {
        return damageType;
    }

    /**
     * To set the damageType from pickListValue table.
     * 
     * @param pickListValue damage values
     * @return this
     */
    public CoverFillingDetails damageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
        return this;
    }

    /**
     * To set the damageType from pickListValue table.
     * 
     * @param pickListValue damage values
     */
    public void setDamageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
    }

    /**
     * To set the coverFillingDamageDescription from coverFillingDetails table.
     * 
     * @return coverFillingDamageDescription
     */
    public PickListValue getCoverFillingDamageDescription() {
        return coverFillingDamageDescription;
    }

    /**
     * To set the coverFillingDamageDescription from coverFillingDetails table.
     * 
     * @param pickListValue damage description
     * @return this
     */
    public CoverFillingDetails coverFillingDamageDescription(PickListValue pickListValue) {
        this.coverFillingDamageDescription = pickListValue;
        return this;
    }

    /**
     * To set the coverFillingDamageDescription from coverFillingDetails table.
     * 
     * @param pickListValue damage description
     */
    public void setCoverFillingDamageDescription(PickListValue pickListValue) {
        this.coverFillingDamageDescription = pickListValue;
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
        CoverFillingDetails coverFillingDetails = (CoverFillingDetails) o;
        if (coverFillingDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFillingDetails.getId());
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
        return "CoverFillingDetails{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
