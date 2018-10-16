/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListDTO and
                            declared the table fields, data types for PickList table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PickList entity.
 * 
 * PickListDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class PickListDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Pick list name cannot be blank.")
    @NotBlank(message = "Pick list name cannot be blank.")
    private String pickListName;

    private Integer status;

    private String displayLabelName;

    /**
     * To Get the Id from PickList table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the PickList table.
     * 
     * @param id id of the PickList
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the pickListName from PickList table
     * 
     * @return pickListName
     */
    public String getPickListName() {
        return pickListName;
    }

    /**
     * To set the pickListName values for the PickList table.
     * 
     * @param pickListName pickListName of the PickList
     */
    public void setPickListName(String pickListName) {
        this.pickListName = pickListName;
    }

    /**
     * To Get the status from PickList table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the PickList table.
     * 
     * @param status status of the PickList
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the displayLabelName from PickList table
     * 
     * @return displayLabelName
     */
    public String getDisplayLabelName() {
        return displayLabelName;
    }

    /**
     * To set the displayLabelName values for the PickList table.
     * 
     * @param displayLabelName displayLabelName of the PickList
     */
    public void setDisplayLabelName(String displayLabelName) {
        this.displayLabelName = displayLabelName;
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

        PickListDTO pickListDTO = (PickListDTO) o;
        if (pickListDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickListDTO.getId());
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
        return "PickListDTO{" +
            "id=" + getId() +
            ", pickListName='" + getPickListName() + "'" +
            ", status=" + getStatus() +
            ", displayLabelName='" + getDisplayLabelName() + "'" +
            "}";
    }
}
