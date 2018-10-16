/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Nursery Inchange Generation and declared 
 *                        the table fields, data types for NurseryIncharge table. 
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
 * NurseryIncharge Domain Class
 * 
 * NurseryIncharge class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */

@Entity
@Table(name = "nursery_incharge")
public class NurseryIncharge extends AbstractAuditingEntity implements Serializable {

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
     * Relation Name : ManyToOne - incharges
     * Table Name    : Connects the NurseryIncharge Table to Nursery Table
     * To get the list of incharges form the nursery table.
     */
    @ManyToOne
    @JsonIgnoreProperties("incharges")
    private Nursery nursery;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from NurseryIncharge table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for NurseryIncharge table
     *
     * @param id id of the NurseryIncharge
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from NurseryIncharge table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate for NurseryIncharge table
     * 
     * @param fromDate fromDate of the NurseryIncharge
     * @return this
     */
    public NurseryIncharge fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * To set the fromDate for NurseryIncharge table
     * 
     * @param fromDate fromDate of the NurseryIncharge
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from NurseryIncharge table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate for NurseryIncharge table
     * 
     * @param toDate toDate of the NurseryIncharge
     * @return this
     */
    public NurseryIncharge toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * To set the toDate for NurseryIncharge table
     * 
     * @param toDate toDate of the NurseryIncharge
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from NurseryIncharge table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description for NurseryIncharge table
     * 
     * @param description description of the NurseryIncharge
     * @return this
     */
    public NurseryIncharge description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To set the description for NurseryIncharge table
     * 
     * @param description description of the NurseryIncharge
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryIncharge table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status for NurseryIncharge table
     * 
     * @param status status of the NurseryIncharge
     * @return this
     */
    public NurseryIncharge status(Integer status) {
        this.status = status;
        return this;
    }
    
    /**
     * To set the status for NurseryIncharge table
     * 
     * @param status status of the NurseryIncharge
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the nursery from NurseryIncharge table
     * 
     * @return nursery
     */
    public Nursery getNursery() {
        return nursery;
    }

    /**
     * To set the nursery for NurseryIncharge table
     * 
     * @param nursery nursery of the NurseryIncharge
     * @return this
     */
    public NurseryIncharge nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    /**
     * To set the nursery for NurseryIncharge table
     * 
     * @param nursery nursery of the NurseryIncharge
     */
    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
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
        NurseryIncharge nurseryIncharge = (NurseryIncharge) o;
        if (nurseryIncharge.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryIncharge.getId());
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
        return "NurseryIncharge{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
