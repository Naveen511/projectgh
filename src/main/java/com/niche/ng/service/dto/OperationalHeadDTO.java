/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHeadDTO and
                            declared the table fields, data types for OperationalHead table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OperationalHead entity.
 * 
 * OperationalHeadDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class OperationalHeadDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Name cannot be blank.")
    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 64, message = "Name should not exist 64 character.")
    private String name;

    private String description;

    private Integer status;

    /**
     * To Get the Id from OperationalHead table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the OperationalHead table.
     * 
     * @param id id of the OperationalHead
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the name from OperationalHead table
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * To set the name values for the OperationalHead table.
     * 
     * @param name name of the OperationalHead
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * To Get the description from OperationalHead table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the OperationalHead table.
     * 
     * @param description description of the OperationalHead
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from OperationalHead table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the OperationalHead table.
     * 
     * @param status status of the OperationalHead
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

        OperationalHeadDTO operationalHeadDTO = (OperationalHeadDTO) o;
        if (operationalHeadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), operationalHeadDTO.getId());
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
        return "OperationalHeadDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
