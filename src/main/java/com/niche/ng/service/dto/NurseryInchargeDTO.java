/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInchargeDTO and
                            declared the table fields, data types for NurseryIncharge table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryIncharge entity.
 * 
 * NurseryInchargeDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class NurseryInchargeDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    @Max(value = 10)
    private Integer status;

    private Long nurseryId;

    private String nurseryNurseryName;

    /**
     * To Get the Id from NurseryIncharge table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryIncharge table.
     * 
     * @param id id value of the NurseryIncharge
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
     * To set the fromDate values for the NurseryIncharge table.
     * 
     * @param fromDate fromDate value of the NurseryIncharge
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
     * To set the toDate values for the NurseryIncharge table.
     * 
     * @param toDate toDate value of the NurseryIncharge
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
     * To set the description values for the NurseryIncharge table.
     * 
     * @param description description value of the NurseryIncharge
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
     * To set the status values for the NurseryIncharge table.
     * 
     * @param status status value of the NurseryIncharge
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the nurseryId from NurseryIncharge table
     * 
     * @return nurseryId
     */
    public Long getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the NurseryIncharge table.
     * 
     * @param nurseryId nurseryId value of the NurseryIncharge
     */
    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }
    
    /**
     * To Get the nurseryNurseryName from NurseryIncharge table
     * 
     * @return nurseryNurseryName
     */
    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    /**
     * To set the nurseryNurseryName values for the NurseryIncharge table.
     * 
     * @param nurseryNurseryName nurseryNurseryName value of the NurseryIncharge
     */
    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

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

        NurseryInchargeDTO nurseryInchargeDTO = (NurseryInchargeDTO) o;
        if (nurseryInchargeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryInchargeDTO.getId());
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
        return "NurseryInchargeDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            "}";
    }
}
