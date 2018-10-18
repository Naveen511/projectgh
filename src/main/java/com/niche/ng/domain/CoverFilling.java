/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs CoverFilling  Generation and declared 
 *                        the table fields, data types for CoverFilling table. 
 *  Relation for CoverFilling : OneToMany Relation
 *  OneToMany Relation : CoverFillingDetails
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * CoverFilling Domain Class
 * 
 * CoverFilling class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "cover_filling")
public class CoverFilling extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_of_cover", nullable = false)
    private Integer noOfCover;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Size(max = 255, message = "Description should not exist 255 character.")
    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "damage_quantity")
    private Integer damageQuantity;

    /**
     * Relation Name : OneToMany - coverFillingDetails
     * Table Name    : Connect the CoverFilling Table.
     * To get the coverFillingDetails.
     */
    @OneToMany(mappedBy = "coverFilling", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<CoverFillingDetails> coverFillingDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from CoverFilling table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for CoverFilling table
     * 
     * @param id id for the CoverFilling table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return noOfCover
     */
    public Integer getNoOfCover() {
        return noOfCover;
    }

    /**
     * To Set the noOfCover for CoverFilling table
     * 
     * @param noOfCover noOfCover on cover filling
     * @return this
     */
    public CoverFilling noOfCover(Integer noOfCover) {
        this.noOfCover = noOfCover;
        return this;
    }

    /**
     * To Set the noOfCover for CoverFilling table
     * 
     * @param noOfCover noOfCover on cover filling
     */
    public void setNoOfCover(Integer noOfCover) {
        this.noOfCover = noOfCover;
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for CoverFilling table.
     * 
     * @param date date while cover filling
     * @return this
     */
    public CoverFilling date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for CoverFilling table.
     * 
     * @param date date while cover filling
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the description from CoverFilling table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for CoverFilling table.
     * 
     * @param description description in cover filling
     * @return this
     */
    public CoverFilling description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for CoverFilling table.
     * 
     * @param description description in cover filling
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from CoverFilling table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for CoverFilling table.
     * 
     * @param status status in cover filling
     * @return this
     */
    public CoverFilling status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for CoverFilling table.
     * 
     * @param status status in cover filling
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the damageQuantity from CoverFilling table
     * 
     * @return damageQuantity
     */
    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To Set the damageQuantity for CoverFilling table.
     * 
     * @param damageQuantity damage quantity in cover filling
     * @return this
     */
    public CoverFilling damageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
        return this;
    }

    /**
     * To Set the damageQuantity for CoverFilling table.
     * 
     * @param damageQuantity damage quantity in cover filling
     */
    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To set the cover from CoverFilling table
     * 
     * @return coverFillingDetails
     */
    public Set<CoverFillingDetails> getCoverFillingDetails() {
        return coverFillingDetails;
    }

    /**
     * To Set the coverFillingDetails for CoverFilling table.
     * 
     * @param coverFillingDetails filling details in cover filling
     * @return this
     */
    public CoverFilling coverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
        return this;
    }

    /**
     * To Set the add coverFillingDetails for CoverFilling table.
     * 
     * @param coverFillingDetails filling details in cover filling
     * @return this
     */
    public CoverFilling addCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.add(coverFillingDetails);
        coverFillingDetails.setCoverFilling(this);
        return this;
    }

    /**
     * To Set the remove coverFillingDetails for CoverFilling table.
     * 
     * @param coverFillingDetails filling details in cover filling
     * @return this
     */
    public CoverFilling removeCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.remove(coverFillingDetails);
        coverFillingDetails.setCoverFilling(null);
        return this;
    }

    /**
     * To Set the coverFillingDetails for CoverFilling table.
     * 
     * @param coverFillingDetails filling details in cover filling
     */
    public void setCoverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
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
        CoverFilling coverFilling = (CoverFilling) o;
        if (coverFilling.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFilling.getId());
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
        return "CoverFilling{" +
            "id=" + getId() +
            ", noOfCover=" + getNoOfCover() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", damageQuantity=" + getDamageQuantity() +
            "}";
    }
}
