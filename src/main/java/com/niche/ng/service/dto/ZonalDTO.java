/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalDTO and
                            declared the table fields, data types for Zonal table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
// import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Zonal entity.
 * 
 * ZonalDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class ZonalDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Zone name cannot be blank.")
    @NotBlank(message = "Zone name cannot be blank.")
    @Size(max = 64, message = "Zone Name should not exist 64 character.")
    private String zoneName;

    private String zoneAddress;

    private Integer status;

    private Long financialYearId;

    private String financialYearBatchName;

    @NotNull(message = "Head office cannot be blank.")
    private Long operationalHeadId;

    private String operationalHeadName;

    /**
     * To Get the Id from Zonal table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the Zonal table.
     * 
     * @param id id of the Zonal
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the zoneName from Zonal table
     * 
     * @return zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * To set the zoneName values for the Zonal table.
     * 
     * @param zoneName zoneName of the Zonal
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * To Get the zoneAddress from Zonal table
     * 
     * @return zoneAddress
     */
    public String getZoneAddress() {
        return zoneAddress;
    }

    /**
     * To set the zoneAddress values for the Zonal table.
     * 
     * @param zoneAddress zoneAddress of the Zonal
     */
    public void setZoneAddress(String zoneAddress) {
        this.zoneAddress = zoneAddress;
    }

    /**
     * To Get the status from Zonal table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the Zonal table.
     * 
     * @param status status of the Zonal
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the financialYearId from Zonal table
     * 
     * @return financialYearId
     */
    public Long getFinancialYearId() {
        return financialYearId;
    }

    /**
     * To set the financialYearId values for the Zonal table.
     * 
     * @param financialYearId financialYearId of the Zonal
     */
    public void setFinancialYearId(Long financialYearSettingsId) {
        this.financialYearId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearBatchName from Zonal table
     * 
     * @return financialYearBatchName
     */
    public String getFinancialYearBatchName() {
        return financialYearBatchName;
    }

    /**
     * To set the financialYearBatchName values for the Zonal table.
     * 
     * @param financialYearBatchName financialYearBatchName of the Zonal
     */
    public void setFinancialYearBatchName(String financialYearSettingsBatchName) {
        this.financialYearBatchName = financialYearSettingsBatchName;
    }

    /**
     * To Get the operationalHeadId from Zonal table
     * 
     * @return operationalHeadId
     */
    public Long getOperationalHeadId() {
        return operationalHeadId;
    }

    /**
     * To set the operationalHeadId values for the Zonal table.
     * 
     * @param operationalHeadId operationalHeadId of the Zonal
     */
    public void setOperationalHeadId(Long operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    /**
     * To Get the operationalHeadName from Zonal table
     * 
     * @return operationalHeadName
     */
    public String getOperationalHeadName() {
        return operationalHeadName;
    }

    /**
     * To set the operationalHeadName values for the Zonal table.
     * 
     * @param operationalHeadName operationalHeadName of the Zonal
     */
    public void setOperationalHeadName(String operationalHeadName) {
        this.operationalHeadName = operationalHeadName;
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

        ZonalDTO zonalDTO = (ZonalDTO) o;
        if (zonalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonalDTO.getId());
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
        return "ZonalDTO{" +
            "id=" + getId() +
            ", zoneName='" + getZoneName() + "'" +
            ", zoneAddress='" + getZoneAddress() + "'" +
            ", status=" + getStatus() +
            ", financialYear=" + getFinancialYearId() +
            ", financialYear='" + getFinancialYearBatchName() + "'" +
            ", operationalHead=" + getOperationalHeadId() +
            ", operationalHead='" + getOperationalHeadName() + "'" +
            "}";
    }
}
