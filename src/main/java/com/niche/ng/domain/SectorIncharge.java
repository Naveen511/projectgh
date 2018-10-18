/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs sectorIncharge Generation and declared 
 *                        the table fields, data types for sectorIncharge table. 
 *  Relation in SectorIncharge : ManyToOne Relation
 *  ManyToOne Relation : Sector
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
 * SectorIncharge Domain Class
 * 
 * SectorIncharge class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "sector_incharge")
public class SectorIncharge extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "description")
    private String description;

    @Max(value = 10)
    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - sector
     * Table Name    : Connect the SectorIncharge Table to Sector Table
     * To get the sector.
     */
    @ManyToOne
    @JsonIgnoreProperties("incharges")
    private Sector sector;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from sector incharge table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for sector incharge table
     *
     * @param id the id in sector incharge table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from sector incharge table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To Set the fromDate for sector incharge table
     *
     * @param fromDate the fromDate in sector incharge table
     * @return this
     */
    public SectorIncharge fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * To Set the fromDate for sector incharge table
     *
     * @param fromDate the fromDate in sector incharge table
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from sector incharge table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To Set the toDate for sector incharge table
     *
     * @param toDate the toDate in sector incharge table
     * @return this
     */
    public SectorIncharge toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * To Set the toDate for sector incharge table
     *
     * @param toDate the toDate in sector incharge table
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from sector incharge table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for sector incharge table
     *
     * @param description the description in sector incharge table
     * @return this
     */
    public SectorIncharge description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for sector incharge table
     *
     * @param description the description in sector incharge table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from sector incharge table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for sector incharge table
     *
     * @param status the status in sector incharge table
     * @return this
     */
    public SectorIncharge status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for sector incharge table
     *
     * @param status the status in sector incharge table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the sector from sector incharge table
     * 
     * @return sector
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * To Set the sector for sector incharge table
     *
     * @param sector the sector in sector incharge table
     * @return this
     */
    public SectorIncharge sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    /**
     * To Set the sector for sector incharge table
     *
     * @param sector the sector in sector incharge table
     */
    public void setSector(Sector sector) {
        this.sector = sector;
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
        SectorIncharge sectorIncharge = (SectorIncharge) o;
        if (sectorIncharge.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sectorIncharge.getId());
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
        return "SectorIncharge{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
