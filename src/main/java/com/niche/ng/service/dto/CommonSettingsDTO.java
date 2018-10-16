/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsDTO and
                            declared the table fields, data types for CommonSettings table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CommonSettings entity.
 * 
 * CommonSettingsDTO class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */

public class CommonSettingsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Integer daysToCloseBatch;

    private Integer status;

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
     */
    public void setStatus(Integer status) {
        this.status = status;
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

        CommonSettingsDTO commonSettingsDTO = (CommonSettingsDTO) o;
        if (commonSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commonSettingsDTO.getId());
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
        return "CommonSettingsDTO{" +
            "id=" + getId() +
            ", daysToCloseBatch=" + getDaysToCloseBatch() +
            ", status=" + getStatus() +
            "}";
    }
}
