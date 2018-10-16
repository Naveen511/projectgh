/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryDTO and
                            declared the table fields, data types for Nursery table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Nursery entity.
 * 
 * NurseryDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class NurseryDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Sector name cannot be blank.")
    private Long sectorId;

    @NotNull(message = "Nursery name cannot be blank.")
    @NotBlank(message = "Nursery name cannot be blank.")
    private String nurseryName;

    private String nurseryAddress;

    private Integer status;

    @NotNull(message = "Nursery code cannot be blank.")
    @NotBlank(message = "Nursery code cannot be blank.")
    private String code;

    private String sectorSectorName;

    private String zonalName;

    private Long nurseryTypeId;

    private String nurseryTypePickListValue;

    private Long financialYearNurseryId;

    private String financialYearNurseryBatchName;

    /**
     * To Get the Id from Nursery table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the Nursery table.
     * 
     * @param id id value of the Nursery
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the nurseryName from Nursery table
     * 
     * @return nurseryName
     */
    public String getNurseryName() {
        return nurseryName;
    }

    /**
     * To set the nurseryName for the Nursery table.
     * 
     * @param nurseryName nurseryName of the Nursery
     */
    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    /**
     * To Get the nurseryAddress from Nursery table
     * 
     * @return nurseryAddress
     */
    public String getNurseryAddress() {
        return nurseryAddress;
    }

    /**
     * To set the nurseryAddress for the Nursery table.
     * 
     * @param nurseryAddress nurseryAddress of the Nursery
     */
    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    /**
     * To Get the status from Nursery table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * To set the status for the Nursery table.
     * 
     * @param status status of the Nursery
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the code from Nursery table
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * To set the code for the Nursery table.
     * 
     * @param code code of the Nursery
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * To Get the sectorId from Nursery table
     * 
     * @return sectorId
     */
    public Long getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId for the Nursery table.
     * 
     * @param sectorId sectorId of the Nursery
     */
    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * To Get the sectorSectorName from Nursery table
     * 
     * @return sectorSectorName
     */
    public String getSectorSectorName() {
        return sectorSectorName;
    }

    /**
     * To set the sectorSectorName for the Nursery table.
     * 
     * @param sectorSectorName sectorSectorName of the Nursery
     */
    public void setSectorSectorName(String sectorSectorName) {
        this.sectorSectorName = sectorSectorName;
    }

    /**
     * To Get the zonalName from Nursery table
     * 
     * @return zonalName
     */
    public String getZonalName() {
        return zonalName;
    }

    /**
     * To set the zonalName for the Nursery table.
     * 
     * @param zonalName zonalName of the Nursery
     */
    public void setZonalName(String zonalName) {
        this.zonalName = zonalName;
    }

    /**
     * To Get the nurseryTypeId from Nursery table
     * 
     * @return nurseryTypeId
     */
    public Long getNurseryTypeId() {
        return nurseryTypeId;
    }

    /**
     * To set the nurseryTypeId for the Nursery table.
     * 
     * @param nurseryTypeId nurseryTypeId of the Nursery
     */
    public void setNurseryTypeId(Long pickListValueId) {
        this.nurseryTypeId = pickListValueId;
    }

    /**
     * To Get the nurseryTypePickListValue from Nursery table
     * 
     * @return nurseryTypePickListValue
     */
    public String getNurseryTypePickListValue() {
        return nurseryTypePickListValue;
    }

    /**
     * To set the nurseryTypePickListValue for the Nursery table.
     * 
     * @param nurseryTypePickListValue nurseryTypePickListValue of the Nursery
     */
    public void setNurseryTypePickListValue(String pickListValuePickListValue) {
        this.nurseryTypePickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the financialYearNurseryId from Nursery table
     * 
     * @return financialYearNurseryId
     */
    public Long getFinancialYearNurseryId() {
        return financialYearNurseryId;
    }

    /**
     * To set the financialYearNurseryId for the Nursery table.
     * 
     * @param financialYearNurseryId financialYearNurseryId of the Nursery
     */
    public void setFinancialYearNurseryId(Long financialYearSettingsId) {
        this.financialYearNurseryId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearNurseryBatchName from Nursery table
     * 
     * @return financialYearNurseryBatchName
     */
    public String getFinancialYearNurseryBatchName() {
        return financialYearNurseryBatchName;
    }

    /**
     * To set the financialYearNurseryBatchName for the Nursery table.
     * 
     * @param financialYearNurseryBatchName financialYearNurseryBatchName of the Nursery
     */
    public void setFinancialYearNurseryBatchName(String financialYearSettingsBatchName) {
        this.financialYearNurseryBatchName = financialYearSettingsBatchName;
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

        NurseryDTO nurseryDTO = (NurseryDTO) o;
        if (nurseryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryDTO.getId());
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
        return "NurseryDTO{" +
            "id=" + getId() +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryAddress='" + getNurseryAddress() + "'" +
            ", status=" + getStatus() +
            ", code='" + getCode() + "'" +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            ", zonal='" + getZonalName() + "'" +
            ", nurseryType=" + getNurseryTypeId() +
            ", nurseryType='" + getNurseryTypePickListValue() + "'" +
            ", financialYearNursery=" + getFinancialYearNurseryId() +
            ", financialYearNursery='" + getFinancialYearNurseryBatchName() + "'" +
            "}";
    }
}
