/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/10/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Common settings  Generation and declared 
 *                        the table fields, data types for Common settings table. 
 *
 *******************************************************************************/
package com.niche.ng.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * CommonSettings Domain Class
 * 
 * CommonSettings class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "common_settings")
public class CommonSettings extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "days_to_close_batch")
    private Integer daysToCloseBatch;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from CommonSettings table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for CommonSettings table
     * 
     * @param id id of the CommonSettings
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the daysToCloseBatch for CommonSettings table
     * 
     * @return daysToCloseBatch
     */
    public Integer getDaysToCloseBatch() {
        return daysToCloseBatch;
    }

    /**
     * To set the daysToCloseBatch for CommonSettings table
     * 
     * @param daysToCloseBatch close the batch
     * @return this
     */
    public CommonSettings daysToCloseBatch(Integer daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
        return this;
    }

    /**
     * To set the daysToCloseBatch for CommonSettings table
     * 
     * @param daysToCloseBatch
     */
    public void setDaysToCloseBatch(Integer daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
    }

    /**
     * To Set the status for CommonSettings table
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for CommonSettings table
     *
     * @param status status in table
     * @return this
     */
    public CommonSettings status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for CommonSettings table
     *
     * @param status status in table
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        CommonSettings commonSettings = (CommonSettings) o;
        if (commonSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commonSettings.getId());
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
        return "CommonSettings{" +
            "id=" + getId() +
            ", daysToCloseBatch=" + getDaysToCloseBatch() +
            ", status=" + getStatus() +
            "}";
    }
}
