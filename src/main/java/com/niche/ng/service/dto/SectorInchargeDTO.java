/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeDTO and
                            declared the table fields, data types for SectorIncharge table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SectorIncharge entity.
 * 
 * SectorInchargeDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class SectorInchargeDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    @Max(value = 10)
    private Integer status;

    private Long sectorId;

    private String sectorSectorName;

    /**
     * To Get the Id from SectorIncharge table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the SectorIncharge table.
     * 
     * @param id id of the SectorIncharge
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from SectorIncharge table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the SectorIncharge table.
     * 
     * @param fromDate fromDate of the SectorIncharge
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from SectorIncharge table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the SectorIncharge table.
     * 
     * @param toDate toDate of the SectorIncharge
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from SectorIncharge table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the SectorIncharge table.
     * 
     * @param description description of the SectorIncharge
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from SectorIncharge table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the SectorIncharge table.
     * 
     * @param status status of the SectorIncharge
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the sectorId from SectorIncharge table
     * 
     * @return sectorId
     */
    public Long getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId values for the SectorIncharge table.
     * 
     * @param sectorId sectorId of the SectorIncharge
     */
    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * To Get the sectorSectorName from SectorIncharge table
     * 
     * @return sectorSectorName
     */
    public String getSectorSectorName() {
        return sectorSectorName;
    }

    /**
     * To set the sectorSectorName values for the SectorIncharge table.
     * 
     * @param sectorSectorName sectorSectorName of the SectorIncharge
     */
    public void setSectorSectorName(String sectorSectorName) {
        this.sectorSectorName = sectorSectorName;
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

        SectorInchargeDTO sectorInchargeDTO = (SectorInchargeDTO) o;
        if (sectorInchargeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sectorInchargeDTO.getId());
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
        return "SectorInchargeDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            "}";
    }
}
