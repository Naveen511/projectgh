/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs zonalIncharge Generation and declared 
 *                        the table fields, data types for zonalIncharge table. 
 *  Relation in ZonalIncharge : ManyToOne Relation
 *  ManyToOne Relation : Zonal
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * ZonalIncharge Domain Class
 * 
 * ZonalIncharge class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "zonal_incharge")
public class ZonalIncharge extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - zonal
     * Table Name    : Connects the ZonalIncharge Table to Zonal Table
     * To get the zonal.
     */
    @ManyToOne
    @JsonIgnoreProperties("zonalIncharges")
    private Zonal zonal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from zonal incharge table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for zonal incharge table
     *
     * @param id the id in zonal incharge table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from zonal incharge table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To Set the fromDate for zonal incharge table
     *
     * @param fromDate the fromDate in zonal incharge table
     * @return this
     */
    public ZonalIncharge fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * To Set the fromDate for zonal incharge table
     *
     * @param fromDate the fromDate in zonal incharge table
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from zonal incharge table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To Set the toDate for zonal incharge table
     *
     * @param toDate the toDate in zonal incharge table
     * @return this
     */
    public ZonalIncharge toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * To Set the toDate for zonal incharge table
     *
     * @return toDate
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from zonal incharge table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for zonal incharge table
     *
     * @param description the description in zonal incharge table
     * @return this
     */
    public ZonalIncharge description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for zonal incharge table
     *
     * @param description the description in zonal incharge table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from zonal incharge table
     * 
     * @param status the status in zonal incharge table
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for zonal incharge table
     *
     * @param status the status in zonal incharge table
     * @return this
     */
    public ZonalIncharge status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for zonal incharge table
     *
     * @param status the status in zonal incharge table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonal from zonal incharge table
     * 
     * @return zonal
     */
    public Zonal getZonal() {
        return zonal;
    }

    /**
     * To Set the zonal for zonal incharge table
     *
     * @param zonal the zonal in zonal incharge table
     * @return this
     */
    public ZonalIncharge zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

    /**
     * To Set the zonal for zonal incharge table
     *
     * @param zonal the zonal in zonal incharge table
     */
    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
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
        ZonalIncharge zonalIncharge = (ZonalIncharge) o;
        if (zonalIncharge.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonalIncharge.getId());
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
        return "ZonalIncharge{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
