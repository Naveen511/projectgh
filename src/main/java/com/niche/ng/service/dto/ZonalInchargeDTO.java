/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalInchargeDTO and
                            declared the table fields, data types for ZonalIncharge table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ZonalIncharge entity.
 * 
 * ZonalInchargeDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class ZonalInchargeDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    private Integer status;

    private Long zonalId;

    private String zonalZoneName;

    /**
     * To Get the Id from ZonalIncharge table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the ZonalIncharge table.
     * 
     * @param id id of the ZonalIncharge
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from ZonalIncharge table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values for the ZonalIncharge table.
     * 
     * @param fromDate fromDate of the ZonalIncharge
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from ZonalIncharge table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the ZonalIncharge table.
     * 
     * @param toDate toDate of the ZonalIncharge
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from ZonalIncharge table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the ZonalIncharge table.
     * 
     * @param description description of the ZonalIncharge
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from ZonalIncharge table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the ZonalIncharge table.
     * 
     * @param status status of the ZonalIncharge
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from ZonalIncharge table
     * 
     * @return zonalId
     */
    public Long getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the ZonalIncharge table.
     * 
     * @param zonalIdid zonalId of the ZonalIncharge
     */
    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the zonalZoneName from ZonalIncharge table
     * 
     * @return zonalZoneName
     */
    public String getZonalZoneName() {
        return zonalZoneName;
    }

    /**
     * To set the zonalZoneName values for the ZonalIncharge table.
     * 
     * @param zonalZoneName zonalZoneName of the ZonalIncharge
     */
    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
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

        ZonalInchargeDTO zonalInchargeDTO = (ZonalInchargeDTO) o;
        if (zonalInchargeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonalInchargeDTO.getId());
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
        return "ZonalInchargeDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            "}";
    }
}
