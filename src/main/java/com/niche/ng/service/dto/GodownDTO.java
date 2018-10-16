/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownDTO and declared the table fields, 
                            data types for Godown table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Godown entity.
 * 
 * GodownDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class GodownDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Godown name cannot be blank.")
    @NotBlank(message = "Godown name cannot be blank.")
    private String name;

    private String address;

    private String incharge;

    private Integer status;

    private Long financialYearGodownId;

    private String financialYearGodownBatchName;

    /**
     * To Get the Id from Godown table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the Godown table.
     * 
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the name from Godown table
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * To set the name values for the Godown table.
     * 
     * @param name name of the Godown
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * To Get the address from Godown table
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * To set the address values for the Godown table.
     * 
     * @param address address of the Godown
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * To Get the incharge from Godown table
     * 
     * @return incharge
     */
    public String getIncharge() {
        return incharge;
    }

    /**
     * To set the incharge values for the Godown table.
     * 
     * @param incharge incharge of the Godown
     */
    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    /**
     * To Get the status from Godown table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the Godown table.
     * 
     * @param status status of the godown
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the financialYearGodownId from Godown table
     * 
     * @return financialYearGodownId
     */
    public Long getFinancialYearGodownId() {
        return financialYearGodownId;
    }

    /**
     * To set the financialYearGodownId values for the Godown table.
     * 
     * @param financialYearGodownId financialYearGodownId of the Godown
     */
    public void setFinancialYearGodownId(Long financialYearSettingsId) {
        this.financialYearGodownId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearGodownBatchName from Godown table
     * 
     * @return financialYearGodownBatchName
     */
    public String getFinancialYearGodownBatchName() {
        return financialYearGodownBatchName;
    }

    /**
     * To set the financialYearGodownBatchName values for the Godown table.
     * 
     * @param financialYearGodownBatchName financialYearGodownBatchName of the Godown
     */
    public void setFinancialYearGodownBatchName(String financialYearSettingsBatchName) {
        this.financialYearGodownBatchName = financialYearSettingsBatchName;
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

        GodownDTO godownDTO = (GodownDTO) o;
        if (godownDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownDTO.getId());
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
        return "GodownDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", incharge='" + getIncharge() + "'" +
            ", status=" + getStatus() +
            ", financialYearGodown=" + getFinancialYearGodownId() +
            ", financialYearGodown='" + getFinancialYearGodownBatchName() + "'" +
            "}";
    }
}
